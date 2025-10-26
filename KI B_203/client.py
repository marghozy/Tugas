import socket
from DES import des_encrypt, des_decrypt

HOST = '127.0.0.1'
PORT = 65432

key = b'abcdefgh'
mode = 'ECB'

client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect((HOST, PORT))
print(f"[CLIENT] Terhubung ke server {HOST}:{PORT}\n")

while True:
    # --- Client mengirim pesan ---
    msg = input("[CLIENT] Masukkan pesan: ").encode()
    if msg.lower() == b'quit':
        break

    cipher = des_encrypt(msg, key, mode)
    print(f"[CLIENT] Ciphertext dikirim (hex): {cipher.hex().upper()}")
    client.sendall(cipher)

    # --- Terima balasan dari server ---
    data = client.recv(1024)
    if not data:
        break

    print(f"[CLIENT] Ciphertext balasan diterima (hex): {data.hex().upper()}")
    plaintext = des_decrypt(data, key, mode)
    print(f"[CLIENT] Balasan dekripsi: {plaintext.decode(errors='ignore')}\n")

client.close()
print("[CLIENT] Koneksi ditutup.")