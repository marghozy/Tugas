import java.util.Scanner;

public class ParkingTicketMachine {
    private int balance;
    private int ticketPrice;
    private int ticketTime;

    public ParkingTicketMachine(int ticketPrice) {
        this.ticketPrice = ticketPrice;
        this.balance = 0;
        this.ticketTime = 0;
    }

    public void insertMoney(int amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Uang berhasil ditambahkan: Rp" + amount);
        } else {
            System.out.println("Masukkan jumlah yang valid!");
        }
    }

    public void issueTicket() {
        if (balance >= ticketPrice) {
            ticketTime = balance / ticketPrice;
            balance = balance % ticketPrice;
            System.out.println("Tiket parkir dikeluarkan untuk " + ticketTime + " jam parkir.");
        } else {
            System.out.println("Saldo tidak cukup. Masukkan Rp" + (ticketPrice - balance) + " lagi.");
        }
    }

    public int getTimePurchased() {
        return ticketTime;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Selamat datang di Mesin Tiket Parkir!");
        System.out.print("Masukkan harga tiket parkir per jam: ");
        int ticketPrice = scanner.nextInt();

        ParkingTicketMachine machine = new ParkingTicketMachine(ticketPrice);

        boolean running = true;

        while (running) {
            System.out.println("\n=== Menu Mesin Tiket Parkir ===");
            System.out.println("1. Masukkan uang");
            System.out.println("2. Cetak tiket");
            System.out.println("3. Lihat waktu parkir yang dibeli");
            System.out.println("4. Keluar");
            System.out.print("Pilih opsi: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Masukkan jumlah uang: ");
                    int amount = scanner.nextInt();
                    machine.insertMoney(amount);
                    break;
                case 2:
                    machine.issueTicket();
                    break;
                case 3:
                    System.out.println("Waktu parkir yang telah dibeli: " + machine.getTimePurchased() + " jam.");
                    break;
                case 4:
                    running = false;
                    System.out.println("Terima kasih telah menggunakan Mesin Tiket Parkir.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih antara 1-4.");
            }
        }
        scanner.close();
    }
}
