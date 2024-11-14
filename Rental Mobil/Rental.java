import java.util.ArrayList;
import java.util.List;

public class Rental {
    private List<Kendaraan> kendaraanTersedia;
    private List<String> daftarPenyewa;

    public Rental() {
        kendaraanTersedia = new ArrayList<>();
        daftarPenyewa = new ArrayList<>();
    }

    public void tambahKendaraan(Kendaraan kendaraan) {
        kendaraanTersedia.add(kendaraan);
    }

    public void daftarPenyewa(String nama, Kendaraan kendaraan) {
        daftarPenyewa.add("Nama: " + nama + ", Kendaraan: " + kendaraan.getInfo());
    }

    public void tampilkanKendaraan() {
        System.out.println("Daftar Kendaraan Tersedia:");
        for (Kendaraan k : kendaraanTersedia) {
            System.out.println(k.getInfo());
        }
    }

    public void tampilkanPenyewa() {
        System.out.println("\nDaftar Penyewa:");
        for (String penyewa : daftarPenyewa) {
            System.out.println(penyewa);
        }
    }
}
