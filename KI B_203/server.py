import socket
from DES import des_encrypt, des_decrypt

HOST = '127.0.0.1'
PORT = 65432

key = b'abcdefgh'
mode = 'ECB'

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind((HOST, PORT))
server.listen(1)
print(f"[SERVER] Menunggu koneksi di {HOST}:{PORT}...")

conn, addr = server.accept()
print(f"[SERVER] Terhubung dengan {addr}\n")

while True:
    # --- Terima ciphertext dari client ---
    data = conn.recv(1024)
    if not data:
        break

    print(f"[SERVER] Ciphertext diterima (hex): {data.hex().upper()}")
    plaintext = des_decrypt(data, key, mode)
    print(f"[SERVER] Hasil dekripsi: {plaintext.decode(errors='ignore')}")

    # --- Server membalas ---
    reply = input("[SERVER] Masukkan balasan: ").encode()
    if reply.lower() == b'quit':
        break

    cipher_reply = des_encrypt(reply, key, mode)
    print(f"[SERVER] Ciphertext balasan (hex): {cipher_reply.hex().upper()}\n")
    conn.sendall(cipher_reply)

conn.close()
print("[SERVER] Koneksi ditutup.")