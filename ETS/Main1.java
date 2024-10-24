public class Main {
    public static void main(String[] args) {
        Kucing kucingku = new Kucing("Boo", 3, "Anggora");
        kucingku.tidur(); // Metode dari kelas induk
        kucingku.makan(); // Metode dari kelas turunan
    }
}
