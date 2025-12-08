import socket
import argparse
import random
import string
import threading
from rsa import import_public_key
from des import (
    des_encrypt_ecb, des_decrypt_ecb,
    des_encrypt_cbc, des_decrypt_cbc
)

def generate_random_8():
    return ''.join(random.choices(string.ascii_letters + string.digits, k=8))

def recv_loop(sock, key, iv, mode, label):
    while True:
        try:
            data = sock.recv(8192)
        except OSError:
            break
        if not data:
            print(f"\n[{label}] Connection closed by server.")
            break
        txt = data.decode(errors='ignore')
        if txt.lower().strip() == "exit":
            print(f"\n[{label}] Peer requested exit.")
            break
        if txt == "__PEER_DISCONNECTED__":
            print(f"\n[{label}] Peer disconnected.")
            continue
        try:
            if mode == "cbc":
                plain = des_decrypt_cbc(txt, key, iv)
            else:
                plain = des_decrypt_ecb(txt, key)
            print(f"\n{label} (received): {plain}")
        except Exception as e:
            print(f"\n{label} (receive) decrypt error: {e}")

def start_client(host, port, key, iv, mode):
    label = "Client2"
    print("=== CLIENT 2 ===")

    if key is None:
        key = input("Masukkan DES KEY (8 char) atau Enter untuk random: ")
        if key == "":
            key = generate_random_8()
    key = (key + "0" * 8)[:8]
    print("DES KEY =", key)

    if iv is None:
        iv = input("Masukkan DES IV (8 char) atau Enter untuk random: ")
        if iv == "":
            iv = generate_random_8()
    iv = (iv + "0" * 8)[:8]
    print("DES IV  =", iv)

    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((host, port))
    print(f"\nConnected to server {host}:{port} (mode: {mode.upper()})")

    pub_bytes = s.recv(2048)
    public_key = import_public_key(pub_bytes)
    e, n = public_key
    print("\nReceived RSA Public Key from server.")

    session = (key.encode() + iv.encode())
    session = (session + b'\x00'*16)[:16]
    session_int = int.from_bytes(session, "big")
    encrypted_session_int = pow(session_int, e, n)
    s.sendall(str(encrypted_session_int).encode())
    print("Encrypted DES session dikirim ke server.")
    print("=== CHAT START ===\n")

    r_thread = threading.Thread(target=recv_loop, args=(s, key, iv, mode, "Peer"), daemon=True)
    r_thread.start()

    try:
        while True:
            msg = input("Client2 > ")
            if msg.lower() == "exit":
                try:
                    s.sendall(b"exit")
                except Exception:
                    pass
                break
            try:
                if mode == "cbc":
                    cipher = des_encrypt_cbc(msg, key, iv)
                else:
                    cipher = des_encrypt_ecb(msg, key)
                s.sendall(cipher.encode())
            except Exception as e:
                print("Send error:", e)
                break
    finally:
        try:
            s.close()
        except Exception:
            pass
        print("\nClient2 disconnected.")

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--host", default="127.0.0.1")
    parser.add_argument("--port", type=int, default=65433)
    parser.add_argument("--key", default=None)
    parser.add_argument("--iv", default=None)
    parser.add_argument("--mode", choices=["ecb", "cbc"], default="ecb")
    args = parser.parse_args()
    start_client(args.host, args.port, args.key, args.iv, args.mode)