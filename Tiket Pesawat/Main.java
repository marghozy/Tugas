public class Main {
    public static void main(String[] args) {
        double baseFare = 1000;

        Ticket economyTicket = new EconomyTicket(baseFare);
        System.out.println("Economy Ticket Fare: " + economyTicket.calculateFare());
        System.out.println("Facilities: " + economyTicket.getFacilities());

        Ticket businessTicket = new BusinessTicket(baseFare);
        System.out.println("\nBusiness Ticket Fare: " + businessTicket.calculateFare());
        System.out.println("Facilities: " + businessTicket.getFacilities());

        Ticket firstClassTicket = new FirstClassTicket(baseFare);
        System.out.println("\nFirst Class Ticket Fare: " + firstClassTicket.calculateFare());
        System.out.println("Facilities: " + firstClassTicket.getFacilities());
    }
}
