public class Main {
    public static void main(String[] args) {
        // Membuat mesin tiket parkir dengan harga Rp5.000 per jam
        ParkingTicketMachine machine = new ParkingTicketMachine(5000);

        // Menambahkan uang sebesar Rp10.000
        machine.insertMoney(10000);

        // Mengeluarkan tiket parkir
        machine.issueTicket(); // Tiket dikeluarkan untuk 2 jam parkir

        // Menampilkan waktu parkir yang dibeli
        System.out.println("Waktu parkir yang dibeli: " + machine.getTimePurchased() + " jam.");
    }
}
