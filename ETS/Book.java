class Book {
    // Atribut kelas
    String judul;
    String penulis;
    int tahunTerbit;
    String namaPenerbit;
    String kategoriBuku;

    // Konstruktor untuk menginisialisasi atribut
    public Book(String judul, String penulis, int tahunTerbit, String namaPenerbit, String kategoriBuku) {
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
        this.namaPenerbit = namaPenerbit;
        this.kategoriBuku = kategoriBuku;
    }

    // Metode untuk mencetak detail buku
    public void printDetails() {
        System.out.println("Judul Buku: " + judul);
        System.out.println("Penulis: " + penulis);
        System.out.println("Tahun Terbit: " + tahunTerbit);
        System.out.println("Penerbit: " + namaPenerbit);
        System.out.println("Kategori: " + kategoriBuku);
    }
}
