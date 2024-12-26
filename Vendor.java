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
                    
                    break;
                case 2:
                    addItemToMenu();
                    break;
                case 3:
                    
                    break;
                case 4:
                    
                    break;
                case 5:
                    User.logout();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    public void addItemToMenu() {
        menu.addMenuItem(this.getUserID());
    }

}
