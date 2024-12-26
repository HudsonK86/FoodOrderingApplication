import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.List; 

public class Menu {
    private List<MenuItem> menuItems;

    public Menu() {
        this.menuItems = new ArrayList<>();
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void addMenuItem(String vendorID) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nAdd Item to Menu");
        
        String name;
        while (true) {
            System.out.print("Enter item name: ");
            name = scanner.nextLine();
            if (name.isEmpty()) {
                System.out.println("Item name cannot be empty.");
            } else {
                break;
            }
            
        }
        
        double price;
        while (true) {
            System.out.print("Enter item price: ");
            try {
                price = Double.parseDouble(scanner.nextLine());
                if (price <= 0) {
                    System.out.println("Price must be a positive value.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid price format.");
            }
        }
        
        System.out.println("Select item category:");
        System.out.println("1. Food");
        System.out.println("2. Drink");
        System.out.print("Enter your choice (1 or 2): ");
        int categoryChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String category;
        if (categoryChoice == 1) {
            category = "Food";
        } else if (categoryChoice == 2) {
            category = "Drink";
        } else {
            System.out.println("Invalid choice. Please select 1 for Food or 2 for Drink.");
            return;
        }

        MenuItem newItem = new MenuItem(FileHandler.generateUniqueItemID(), name, price, category);
        menuItems.add(newItem);

        // Save the new menu item to the file
        FileHandler.addMenuItemToFile(vendorID, newItem);

        System.out.println("Item ID:"+newItem.getItemID()+" added successfully.");
    }
}
