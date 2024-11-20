public class FirstClassTicket extends Ticket {

    public FirstClassTicket(double baseFare) {
        super(baseFare);
    }

    @Override
    public double calculateFare() {
        return baseFare * 1.5; // Tambah 50%
    }

    @Override
    public String getFacilities() {
        return "Bagasi 40kg, makanan gourmet, akses lounge VIP, layanan pribadi";
    }
}
