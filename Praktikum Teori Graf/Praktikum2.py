import matplotlib.pyplot as plt

# Kelas TreeNode merepresentasikan sebuah node dalam tree
class TreeNode:
    def __init__(self, value):
        self.value = value  # Nilai elemen node
        self.children = []  # Daftar anak node

# Fungsi untuk membangun tree dari array input
def construct_tree(arr):
    root = TreeNode(None)  # Membuat root dengan nilai None
    construct_tree_helper(root, arr, 0, float('-inf'))  # Memulai tree dari nilai -∞
    return root

# Fungsi rekursif untuk menambahkan anak pada node sesuai aturan LMIS
def construct_tree_helper(node, arr, index, prev_value):
    if index == len(arr):  # Basis rekursi, ketika mencapai akhir array
        return

    # Menambahkan elemen sebagai anak jika elemen lebih besar dari prev_value
    for i in range(index, len(arr)):
        if arr[i] > prev_value:
            child = TreeNode(arr[i])  # Membuat node baru
            node.children.append(child)  # Menambahkan node ke daftar anak
            construct_tree_helper(child, arr, i + 1, arr[i])  # Rekursi untuk elemen berikutnya

# Fungsi untuk menemukan semua jalur terpanjang dari root ke leaf
def find_all_longest_paths(root):
    if not root.children:  # Jika node adalah leaf
        return [[root.value]]  # Mengembalikan jalur berupa nilai node

    all_paths = []  # Daftar semua jalur
    max_length = 0  # Panjang maksimum jalur

    # Iterasi melalui setiap anak node
    for child in root.children:
        child_paths = find_all_longest_paths(child)  # Rekursi pada anak
        for path in child_paths:
            current_path = [root.value] + path  # Membentuk jalur
            if len(current_path) > max_length:  # Jika jalur lebih panjang dari maksimum sebelumnya
                max_length = len(current_path)
                all_paths = [current_path]
            elif len(current_path) == max_length:  # Jika sama panjang, tambahkan ke daftar
                all_paths.append(current_path)

    return all_paths

# Fungsi untuk visualisasi tree menggunakan matplotlib
def plot_tree(root):
    fig, ax = plt.subplots(figsize=(15, 10))  # Membuat figure
    plot_tree_helper(ax, root, 0, 0, 150, 50)  # Memulai visualisasi dari root
    ax.axis('off')  # Menyembunyikan sumbu
    plt.show()

# Fungsi pembantu rekursif untuk menggambar tree
def plot_tree_helper(ax, node, x, y, dx, dy):
    if node is not None:
        # Menampilkan nilai node
        ax.text(x + dx / 2, y + dy / 2, str(node.value), ha='center', va='center', bbox=dict(facecolor='white', edgecolor='black'))

        if node.children:  # Jika node memiliki anak
            next_dx = dx / len(node.children)  # Menentukan jarak horizontal antar anak
            next_x = x
            next_y = y - 3 * dy  # Menentukan posisi vertikal anak
            for child in node.children:
                next_x += next_dx  # Memperbarui posisi x
                ax.plot([x + dx / 2, next_x + next_dx / 2], [y + dy / 2, next_y + dy], color='black', marker='o')  # Menggambar garis
                plot_tree_helper(ax, child, next_x, next_y, next_dx, dy)  # Rekursi untuk anak

# Fungsi utama
def main():
    input_array = [4, 1, 13, 7, 0, 2, 8, 11, 3]  # Array input

    # Membangun tree
    root = construct_tree(input_array)
    
    # Menemukan semua LMIS
    all_longest_paths = find_all_longest_paths(root)

    # Menampilkan hasil
    print("Input Array:", input_array)
    print("All Longest Increasing Subsequences:")
    for path in all_longest_paths:
        print(path[1:])  # Menghilangkan nilai root None

    # Visualisasi tree
    plot_tree(root)

main()