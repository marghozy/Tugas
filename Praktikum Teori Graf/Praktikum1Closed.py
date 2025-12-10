import random
import matplotlib.pyplot as plt

# Representasi posisi kuda pada papan catur
class Cell:
    def __init__(self, x, y):
        self.x = x  # Koordinat x pada papan catur
        self.y = y  # Koordinat y pada papan catur

N = 8  # Ukuran papan catur 8x8

# Array pergerakan kuda dalam catur
cx = [1, 1, 2, 2, -1, -1, -2, -2]
cy = [2, -2, 1, -1, 2, -2, 1, -1]

# Mengecek apakah koordinat berada dalam batas papan catur
def limits(x, y):
    return ((x >= 0 and y >= 0) and (x < N and y < N))

# Mengecek apakah kotak belum dikunjungi
def isempty(a, x, y):
    return (limits(x, y)) and (a[y * N + x] < 0)

# Menghitung jumlah langkah valid dari posisi saat ini
def getDegree(a, x, y):
    count = 0
    for i in range(N):
        if isempty(a, (x + cx[i]), (y + cy[i])):
            count += 1
    return count

# Menentukan langkah berikutnya berdasarkan algoritma Warnsdorff
def nextMove(a, cell):
    min_deg_idx = -1  # Indeks langkah dengan degree minimum
    c = 0
    min_deg = (N + 1)  # Nilai maksimum awal untuk degree
    nx = 0
    ny = 0

    start = random.randint(0, 1000) % N  # Memulai dari langkah acak
    for count in range(0, N):
        i = (start + count) % N  # Iterasi semua kemungkinan langkah
        nx = cell.x + cx[i]
        ny = cell.y + cy[i]
        c = getDegree(a, nx, ny)
        if ((isempty(a, nx, ny)) and c < min_deg):
            min_deg_idx = i
            min_deg = c

    if (min_deg_idx == -1):
        return None  # Tidak ada langkah yang mungkin

    # Perbarui posisi kuda dan matriks langkah
    nx = cell.x + cx[min_deg_idx]
    ny = cell.y + cy[min_deg_idx]
    a[ny * N + nx] = a[(cell.y) * N + (cell.x)] + 1
    cell.x = nx
    cell.y = ny

    return cell

arr = []  # Menyimpan urutan langkah untuk visualisasi

def printA(a):
    for i in range(N):
        for j in range(N):
            print("%d\t" % a[j * N + i], end="")  # Cetak urutan langkah
            arr.append(a[j * N + i])
        print()

# Mengecek apakah langkah terakhir dapat kembali ke awal (closed tour)
def neighbour(x, y, xx, yy):
    for i in range(N):
        if ((x + cx[i]) == xx) and ((y + cy[i]) == yy):
            return True
    return False

# Mencari solusi closed tour
def findClosedTour(start_x, start_y):
    a = [-1] * N * N  # Matriks untuk menandai langkah, -1 = belum dikunjungi
    cell = Cell(start_x, start_y)  # Inisialisasi posisi awal kuda
    a[cell.y * N + cell.x] = 1  # Tandai langkah pertama

    ret = None
    for i in range(N * N - 1):
        ret = nextMove(a, cell)  # Cari langkah berikutnya
        if ret is None:
            return False  # Tidak ada solusi

    if ret is not None and not neighbour(ret.x, ret.y, start_x, start_y):
        return False  # Tidak memenuhi closed tour
    printA(a)  # Cetak matriks langkah
    return True

# Input titik awal dari pengguna
start_x = int(input("Enter the starting X coordinate (0-7): "))
start_y = int(input("Enter the starting Y coordinate (0-7): "))

if 0 <= start_x < N and 0 <= start_y < N:
    while not findClosedTour(start_x, start_y):
        pass  # Ulangi hingga solusi ditemukan
else:
    print("Invalid starting point. Coordinates must be in the range 0-7.")

tour = []

# Menyusun urutan langkah untuk visualisasi
for i in range(1, 65):
    index = arr.index(i)
    tour.append([index//8, index%8])

print(tour)

# Visualisasi menggunakan matplotlib
lx = list()
ly = list()
print(tour)
for i in tour:
    lx.append(0 + i[1] + 0.5)  # Koordinat x untuk visualisasi
    ly.append(8 - (i[0] + 0.5))  # Koordinat y untuk visualisasi

y = [0, 8, 8, 0, 0]
x = [0, 0, 8, 8, 0]
plt.plot(x, y, linewidth=2, color='black')  # Gambar batas papan catur

# Gambar grid papan catur
for i in range (1, 8):
    hx = [i, i]
    hy = [0, 8]
    plt.plot(hx, hy, linewidth=2, color='black')
    plt.plot(hy, hx, linewidth=2, color='black')

# Tandai posisi awal dan akhir
plt.scatter(lx[0], ly[0], color="darkblue", marker='o')
plt.scatter(lx[63], ly[63], color="darkblue", marker='x')

# Gambar jalur perjalanan
for i in lx:
    plt.plot(lx, ly, linewidth=1, color='red')

plt.xlabel('Hor')
plt.ylabel('Ver')

plt.xticks([])
plt.yticks([])

plt.title('Closed Tour')
plt.show()