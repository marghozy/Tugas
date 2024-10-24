public class ParkingTicketMachine {
    // Atribut
    private int balance;      // Jumlah nominal uang yang dimasukkan pengguna
    private int ticketPrice;  // Harga tiket parkir per jam
    private int ticketTime;   // Jumlah waktu parkir yang dibeli dalam jam

    // Konstruktor untuk menginisialisasi harga tiket per jam
    public ParkingTicketMachine(int ticketPrice) {
        this.ticketPrice = ticketPrice;
        this.balance = 0;
        this.ticketTime = 0;
    }

    // Metode untuk menambahkan uang ke mesin
    public void insertMoney(int amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Uang berhasil ditambahkan: Rp" + amount);
        } else {
            System.out.println("Masukkan jumlah yang valid!");
        }
    }

    // Metode untuk mengeluarkan tiket jika saldo cukup
    public void issueTicket() {
        if (balance >= ticketPrice) {
            ticketTime = balance / ticketPrice;  // Hitung jumlah jam parkir yang dibeli
            balance = balance % ticketPrice;     // Sisa saldo setelah pembelian tiket
            System.out.println("Tiket parkir dikeluarkan untuk " + ticketTime + " jam parkir.");
        } else {
            System.out.println("Saldo tidak cukup. Masukkan Rp" + (ticketPrice - balance) + " lagi.");
        }
    }

    // Metode untuk mengembalikan jumlah jam parkir yang telah dibeli
    public int getTimePurchased() {
        return ticketTime;
    }
}
