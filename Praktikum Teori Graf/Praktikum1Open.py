import random
import matplotlib.pyplot as plt

# Mengecek apakah langkah valid (di dalam papan dan belum dikunjungi)
def is_valid_move(x, y, board):
    return 0 <= x < 8 and 0 <= y < 8 and board[x][y] == -1

# Mendapatkan langkah-langkah valid dari posisi saat ini
def get_valid_moves(x, y, board):
    moves = []
    move_x = [2, 1, -1, -2, -2, -1, 1, 2]
    move_y = [1, 2, 2, 1, -1, -2, -2, -1]

    for i in range(8):
        next_x = x + move_x[i]
        next_y = y + move_y[i]
        if is_valid_move(next_x, next_y, board):
            count = 0
            for j in range(8):
                test_x = next_x + move_x[j]
                test_y = next_y + move_y[j]
                if is_valid_move(test_x, test_y, board):
                    count += 1
            moves.append((next_x, next_y, count))

    # Urutkan langkah berdasarkan aksesibilitas (ascending), kemudian randomize
    moves.sort(key=lambda x: (x[2], random.random()))
    return moves

# Mengecek apakah tur bersifat "closed"
def is_closed_tour(board, start_x, start_y):
    move_x = [2, 1, -1, -2, -2, -1, 1, 2]
    move_y = [1, 2, 2, 1, -1, -2, -2, -1]

    for i in range(8):
        next_x = start_x + move_x[i]
        next_y = start_y + move_y[i]
        if is_valid_move(next_x, next_y, board) and board[next_x][next_y] == 0:
            return True
    return False

# Algoritma utama untuk mencari "open tour" kuda
def knight_tour(start_x, start_y):
    board = [[-1 for _ in range(8)] for _ in range(8)]
    board[start_x][start_y] = 0

    move_x = [2, 1, -1, -2, -2, -1, 1, 2]
    move_y = [1, 2, 2, 1, -1, -2, -2, -1]

    if not knight_tour_util(start_x, start_y, 1, board, move_x, move_y):
        return None
    return board

# Fungsi utilitas untuk rekursi
def knight_tour_util(x, y, move_num, board, move_x, move_y):
    if move_num == 64:
        return True

    moves = get_valid_moves(x, y, board)
    for move in moves:
        next_x, next_y, _ = move
        board[next_x][next_y] = move_num
        if knight_tour_util(next_x, next_y, move_num + 1, board, move_x, move_y):
            return True
        board[next_x][next_y] = -1

    return False

arr = []  # Menyimpan langkah untuk visualisasi

def print_board(board):
    for row in board:
        print(row)
    for row in board:
        for i in row:
            arr.append(i)

# Input titik awal dari pengguna
start_x = int(input("Masukkan koordinat x starting point (0-7): "))
start_y = int(input("Masukkan koordinat y starting point (0-7): "))

# Cari dan cetak satu tur "open" berbeda dari starting point
count_open_tours = 0
current_tour = 1

while count_open_tours < 1:
    print(f"\nKnight's Tour Open:")
    current_board = knight_tour(start_x, start_y)
    if current_board is not None and not is_closed_tour(current_board, start_x, start_y):
        print_board(current_board)
        count_open_tours += 1
    else:
        print(f"Tur #{current_tour} tidak bersifat open.")
    current_tour += 1

print(arr)

tour = []

# Menyusun urutan langkah untuk visualisasi
for i in range(0, 64):
    index = arr.index(i)
    tour.append([index//8, index%8])

print(tour)

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

plt.title('Open Tour')
plt.show()