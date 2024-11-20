public class BusinessTicket extends Ticket {

    public BusinessTicket(double baseFare) {
        super(baseFare);
    }

    @Override
    public double calculateFare() {
        return baseFare * 1.25; // Tambah 25%
    }

    @Override
    public String getFacilities() {
        return "Bagasi 30kg, makanan, akses lounge bisnis";
    }
}
