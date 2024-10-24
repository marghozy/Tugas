import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FoodOrder order = new FoodOrder();
        boolean exit = false;

        while (!exit) {
            System.out.println("==== Menu ====");
            System.out.println("1. Tambah Item");
            System.out.println("2. Hapus Item");
            System.out.println("3. Lihat Detail Pesanan");
            System.out.println("4. Bayar Pesanan");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Masukkan nama item: ");
                    String itemName = scanner.nextLine();
                    System.out.print("Masukkan harga item: Rp");
                    double itemPrice = scanner.nextDouble();
                    scanner.nextLine();
                    MenuItem newItem = new MenuItem(itemName, itemPrice);
                    order.addItem(newItem);
                    break;
                case 2:
                    System.out.print("Masukkan nama item yang ingin dihapus: ");
                    String removeItemName = scanner.nextLine();
                    order.removeItem(removeItemName);
                    break;
                case 3:
                    order.getOrderDetails();
                    break;
                case 4:
                    order.markAsPaid();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Terima kasih telah menggunakan aplikasi.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    break;
            }
        }

        scanner.close();
    }
}
