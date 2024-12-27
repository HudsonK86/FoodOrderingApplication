import java.util.Scanner;

public class Vendor extends User {
    private double revenue;
    private Menu menu;

    // Constructor for Vendor
    public Vendor(String name, String userID, String password, double revenue) {
        super(name, userID, password, "Vendor"); // Calls the parent class (User) constructor
        this.revenue = revenue;
        this.menu = new Menu(); // Initialize menu
    }

    // Getter for revenue
    public double getRevenue() {
        return revenue;
    }

    // Setter for revenue
    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    // Vendor Menu
    public void vendorMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nVendor Menu:");
            System.out.println("1. View menu");
            System.out.println("2. Add item to menu");
            System.out.println("3. Update item in menu");
            System.out.println("4. Delete item from menu");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewMenu();
                    break;
                case 2:
                    addItemToMenu();
                    break;
                case 3:
                    updateItemInMenu();
                    break;
                case 4:
                    deleteItemFromMenu();
                    break;
                case 5:
                    logout();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    public void viewMenu() {
        menu.viewMenu(this.getUserID());
    }

    public void addItemToMenu() {
        menu.addMenuItem(this.getUserID());
    }

    public void updateItemInMenu() {
        menu.updateMenuItem(this.getUserID());
    }

    public void deleteItemFromMenu() {
        menu.deleteMenuItem(this.getUserID());
    }
}
