public class Main {
    public static void main(String[] args) {
        Rental rental = new Rental();

        rental.tambahKendaraan(new Mobil("Toyota", "Avanza", 2020, 4));
        rental.tambahKendaraan(new Motor("Yamaha", "NMAX", 2021, 2));
        rental.tambahKendaraan(new Sepeda("Polygon", "Xtrada", 2019, "Gunung"));

        rental.tampilkanKendaraan();

        rental.daftarPenyewa("Ali", new Mobil("Toyota", "Avanza", 2020, 4));
        rental.daftarPenyewa("Budi", new Sepeda("Polygon", "Xtrada", 2019, "Gunung"));

        rental.tampilkanPenyewa();
    }
}
