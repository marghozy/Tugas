import java.util.ArrayList;

public class FoodOrder {
    private ArrayList<MenuItem> menuItems;
    private double totalPrice;
    private boolean isPaid;

    public FoodOrder() {
        this.menuItems = new ArrayList<>();
        this.totalPrice = 0.0;
        this.isPaid = false;
    }

    public void addItem(MenuItem item) {
        menuItems.add(item);
        totalPrice += item.getPrice();
        System.out.println(item.getName() + " ditambahkan dengan harga: Rp" + item.getPrice());
    }

    public void removeItem(String itemName) {
        boolean found = false;
        for (MenuItem item : menuItems) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                menuItems.remove(item);
                totalPrice -= item.getPrice();
                found = true;
                System.out.println(itemName + " dihapus dari pesanan.");
                break;
            }
        }
        if (!found) {
            System.out.println("Item tidak ditemukan dalam pesanan.");
        }
    }

    public double calculateTotalPrice() {
        return totalPrice;
    }

    public void markAsPaid() {
        if (!isPaid) {
            isPaid = true;
            System.out.println("Pesanan telah dibayar.");
        } else {
            System.out.println("Pesanan sudah dibayar sebelumnya.");
        }
    }

    public void getOrderDetails() {
        System.out.println("\n--- Detail Pesanan ---");
        System.out.println("Daftar Menu:");
        for (MenuItem item : menuItems) {
            System.out.println("- " + item.getName() + ": Rp" + item.getPrice());
        }
        System.out.println("Total Harga: Rp" + totalPrice);
        System.out.println("Status Pembayaran: " + (isPaid ? "Sudah Dibayar" : "Belum Dibayar"));
        System.out.println("----------------------\n");
    }
}
