// Kelas Induk
class Hewan {
    String nama;
    int umur;

    public Hewan(String nama, int umur) {
        this.nama = nama;
        this.umur = umur;
    }

    public void makan() {
        System.out.println(nama + " sedang tidur.");
    }
}
