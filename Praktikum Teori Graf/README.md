- **Nama** : Ammar Ghozy Tanumijaya
- **NRP** : 5025231203

# 🐴 Knight's Tour & LMIS Project
Repositori ini berisi tiga program praktikum Teori Graf:
1. **Closed Knight’s Tour**  
2. **Open Knight’s Tour**  
3. **Longest Monotonically Increasing Subsequences (LMIS)**  

Semua program dapat dijalankan menggunakan **Google Colab**, **Python**, atau **VSCode**.

---

## 📌 1. Closed Knight’s Tour (`Praktikum1Closed.py`)
Program ini mencari **Closed Tour**, yaitu perjalanan kuda pada papan 8x8 yang:
- Mengunjungi setiap kotak tepat satu kali  
- Langkah terakhir dapat kembali ke langkah awal  

### **Cara Penggunaan**
1. Jalankan file `Praktikum1Closed.py`
2. Masukkan koordinat awal kuda:  
  Enter the starting X coordinate (0-7):
  Enter the starting Y coordinate (0-7):
3. Program akan melakukan pencarian sampai menemukan Closed Tour valid.

### **Output**
- Matriks 8x8 berisi urutan langkah (1–64)
- Visualisasi jalur kuda menggunakan matplotlib

---

## 📌 2. Open Knight’s Tour (`Praktikum1Open.py`)
Program ini mencari **Open Tour**, yaitu perjalanan kuda pada papan 8x8 yang:
- Mengunjungi semua kotak
- **Tidak harus kembali ke kotak awal**

### **Cara Penggunaan**
1. Jalankan file `Praktikum1Open.py`
2. Masukkan koordinat awal:
  Masukkan koordinat x starting point (0-7):
  Masukkan koordinat y starting point (0-7):
3. Program akan mencoba berbagai rute sampai menemukan tur yang **open**.

### **Output**
- Matriks langkah dari 0–63
- Visualisasi jalur open tour (garis merah tanpa kembali ke start)

---

## 📌 3. LMIS Tree (`Praktikum2.py`)
Program ini mencari **Longest Monotonically Increasing Subsequences** (LMIS) dari sebuah array.
Pembangunan dilakukan menggunakan struktur **tree rekursif**.

### **Cara Penggunaan**
1. Jalankan `Praktikum2.py` (tanpa input)
2. Program otomatis:
- Membangun tree LMIS
- Mencari semua jalur yang paling panjang

### **Output**
- Menampilkan semua LMIS dalam bentuk list  
- Visualisasi tree menggunakan matplotlib  

---
