import socket
import threading
import argparse
from rsa import generate_keys, export_public_key

def recv_all_line(conn):
    """Receive a single chunk (used for session int)."""
    data = conn.recv(8192)
    if not data:
        return None
    return data

def handle_relay(src_sock, dst_sock, label_src, label_dst, stop_event):
    """Relay data from src_sock to dst_sock until src disconnects or sends 'exit'."""
    try:
        while not stop_event.is_set():
            try:
                data = src_sock.recv(8192)
            except OSError:
                data = None

            if not data:
                print(f"[{label_src}] Disconnected (socket closed).")
                try:
                    dst_sock.sendall(b"__PEER_DISCONNECTED__")
                except Exception:
                    pass
                stop_event.set()
                break

            try:
                txt = data.decode(errors='ignore').strip()
            except Exception:
                txt = ""

            if txt.lower() == "exit":
                print(f"[{label_src}] Sent exit.")
                try:
                    dst_sock.sendall(b"exit")
                except Exception:
                    pass
                stop_event.set()
                break

            try:
                dst_sock.sendall(data)
                print(f"[{label_src}] -> {label_dst}  (forwarded {len(data)} bytes)")
            except Exception as e:
                print(f"[{label_src}] Forward error: {e}")
                stop_event.set()
                break
    finally:
        try:
            src_sock.shutdown(socket.SHUT_RD)
        except Exception:
            pass

def session_loop(server_sock, private_key, public_key, mode):
    """Accept two clients, exchange public key, receive sessions, then relay until done.
       After the pair finishes, return to accept a new pair (server stays alive)."""
    e, n = public_key
    d, npriv = private_key

    while True:
        print("\nWaiting for client A to connect...")
        conn_a, addr_a = server_sock.accept()
        print(f"[ClientA {addr_a}] connected")
        conn_a.sendall(export_public_key(public_key))
        print(f"[ClientA {addr_a}] public key sent")

        print("Waiting for client B to connect...")
        conn_b, addr_b = server_sock.accept()
        print(f"[ClientB {addr_b}] connected")
        conn_b.sendall(export_public_key(public_key))
        print(f"[ClientB {addr_b}] public key sent")

        data_a = recv_all_line(conn_a)
        if data_a is None:
            print("[ClientA] disconnected before session setup")
            try:
                conn_a.close()
                conn_b.close()
            except Exception:
                pass
            continue
        try:
            enc_int_a = int(data_a.decode().strip())
            dec_int_a = pow(enc_int_a, d, n)
            session_a = dec_int_a.to_bytes(16, "big")
            key_a = session_a[:8]
            iv_a = session_a[8:16]
            print(f"[ClientA {addr_a}] session received.")
        except Exception as e:
            print(f"[ClientA] session parse/decrypt error: {e}")
            conn_a.close(); conn_b.close(); continue

        data_b = recv_all_line(conn_b)
        if data_b is None:
            print("[ClientB] disconnected before session setup")
            try:
                conn_a.close()
                conn_b.close()
            except Exception:
                pass
            continue
        try:
            enc_int_b = int(data_b.decode().strip())
            dec_int_b = pow(enc_int_b, d, n)
            session_b = dec_int_b.to_bytes(16, "big")
            key_b = session_b[:8]
            iv_b = session_b[8:16]
            print(f"[ClientB {addr_b}] session received.")
        except Exception as e:
            print(f"[ClientB] session parse/decrypt error: {e}")
            conn_a.close(); conn_b.close(); continue

        print(f"[ClientA] DES_KEY (hex): {key_a.hex().upper()}  IV (hex): {iv_a.hex().upper()}")
        print(f"[ClientB] DES_KEY (hex): {key_b.hex().upper()}  IV (hex): {iv_b.hex().upper()}")
        print("\nSessions established. Starting blind relay between clients.\n")

        stop_event = threading.Event()
        t1 = threading.Thread(target=handle_relay, args=(conn_a, conn_b, f"ClientA {addr_a}", f"ClientB {addr_b}", stop_event), daemon=True)
        t2 = threading.Thread(target=handle_relay, args=(conn_b, conn_a, f"ClientB {addr_b}", f"ClientA {addr_a}", stop_event), daemon=True)
        t1.start()
        t2.start()

        stop_event.wait()
        t1.join(timeout=1.0)
        t2.join(timeout=1.0)

        try:
            conn_a.close()
        except Exception:
            pass
        try:
            conn_b.close()
        except Exception:
            pass

        print("Pair session ended. Server remains running and will accept next pair.\n")

def main(host, port, mode):
    print("Generating RSA key pair...")
    public_key, private_key = generate_keys(bits=256)
    e, n = public_key
    d, npriv = private_key

    print("RSA Keys Generated:")
    print(f"  Public Key (e, n):")
    print(f"    e = {e}")
    print(f"    n = {n}")
    print("  Private Key available on server (kept secret)\n")

    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    server.bind((host, port))
    server.listen(5)
    print(f"Server relay listening on {host}:{port}  (DES mode: {mode.upper()})")

    try:
        session_loop(server, private_key, public_key, mode)
    except KeyboardInterrupt:
        print("\nServer shutting down (keyboard interrupt).")
    finally:
        try:
            server.close()
        except Exception:
            pass
        print("Server stopped.")

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="RSA-DES Relay Server (2-client pairing).")
    parser.add_argument("--host", default="0.0.0.0")
    parser.add_argument("--port", type=int, default=65433)
    parser.add_argument("--mode", choices=["ecb", "cbc"], default="ecb")
    args = parser.parse_args()
    main(args.host, args.port, args.mode)