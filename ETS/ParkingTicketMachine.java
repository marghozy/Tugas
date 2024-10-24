class ParkingTicketMachine {
    // Atribut
    private int balance;         // Jumlah uang yang dimasukkan oleh pengguna
    private int ticketPrice;     // Harga tiket parkir per jam
    private int ticketTime;      // Jumlah waktu parkir yang dibeli (dalam jam)

    // Konstruktor untuk menginisialisasi harga tiket
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
            System.out.println("Uang yang dimasukkan tidak valid.");
        }
    }

    // Metode untuk mengeluarkan tiket jika saldo cukup
    public void issueTicket() {
        if (balance >= ticketPrice) {
            ticketTime = balance / ticketPrice;  // Hitung jumlah jam parkir yang dibeli
            balance = balance % ticketPrice;     // Sisa uang setelah pembelian tiket
            System.out.println("Tiket dikeluarkan untuk " + ticketTime + " jam parkir.");
        } else {
            System.out.println("Saldo tidak cukup untuk membeli tiket.");
        }
    }

    // Metode untuk mengembalikan jumlah jam parkir yang telah dibeli
    public int getTimePurchased() {
        return ticketTime;
    }

    // Metode untuk menampilkan saldo saat ini
    public int getBalance() {
        return balance;
    }
}
