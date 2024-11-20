public class EconomyTicket extends Ticket {

    public EconomyTicket(double baseFare) {
        super(baseFare);
    }

    @Override
    public double calculateFare() {
        return baseFare * 0.9; // Diskon 10%
    }

    @Override
    public String getFacilities() {
        return "Bagasi 20kg, minuman ringan";
    }
}
