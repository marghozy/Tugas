public class ParkingTicketMachine {
    private int ticketPrice; // Harga tiket parkir per jam
    private int balance;     // Jumlah uang yang dimasukkan oleh pengguna
    private int total;       // Total uang yang terkumpul di mesin
    private int ticketTime;  // Jumlah jam parkir yang dibeli

    // Konstruktor untuk menginisialisasi harga tiket per jam
    public ParkingTicketMachine(int ticketPrice) {
        this.ticketPrice = ticketPrice;
        this.balance = 0;
        this.total = 0;
        this.ticketTime = 0;
    }

    // Metode untuk mendapatkan harga tiket per jam
    public int getTicketPrice() {
        return ticketPrice;
    }

    // Metode untuk mendapatkan saldo yang dimasukkan oleh pengguna
    public int getBalance() {
        return balance;
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

    // Metode untuk mengeluarkan tiket parkir jika saldo cukup
    public void issueTicket() {
        if (balance >= ticketPrice) {
            ticketTime = balance / ticketPrice;  // Hitung jumlah jam parkir yang dibeli
            total += ticketPrice * ticketTime;   // Tambahkan ke total uang yang terkumpul
            balance = balance % ticketPrice;     // Sisa saldo setelah pembelian tiket
            System.out.println("########################################");
            System.out.println("####### Tiket Parkir Dikeluarkan #######");
            System.out.println("### Waktu parkir: " + ticketTime + " jam");
            System.out.println("########################################");
            System.out.println("### Sisa saldo: Rp" + balance "#########");
        } else {
            System.out.println("Saldo tidak cukup. Masukkan Rp" + (ticketPrice - balance) + " lagi.");
        }
    }

    // Metode untuk mendapatkan jumlah waktu parkir yang telah dibeli
    public int getTimePurchased() {
        return ticketTime;
    }
}
