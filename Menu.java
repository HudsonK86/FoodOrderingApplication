import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private List<MenuItem> menuItems;

    public Menu() {
        this.menuItems = new ArrayList<>();
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    // View menu
    public void viewMenu(String vendorID) {
        System.out.println("\n--- Vendor Menu ---");
        List<MenuItem> vendorMenuItems = FileHandler.readMenuItemsByVendor(vendorID);
    
        if (vendorMenuItems.isEmpty()) {
            System.out.println("No items in your menu.");
        } else {
            System.out.printf("%-10s %-10s %-20s %-10s\n", "ItemID", "Category", "Name", "Price");
            System.out.println("----------------------------------------------");
            for (MenuItem item : vendorMenuItems) {
                System.out.printf("%-10d %-10s %-20s %-10.2f\n", item.getItemID(), item.getCategory(), item.getName(), item.getPrice());
            }
        }
    }
    
    // Add a new menu item
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

        int itemID = FileHandler.generateUniqueItemID();
        MenuItem newItem = new MenuItem(vendorID, itemID, name, price, category);

        // Load existing menu data
        List<MenuItem> menuItems = FileHandler.loadMenuData();

        // Add new item to list
        menuItems.add(newItem);

        // Save updated list to file
        FileHandler.saveMenuData(menuItems);

        System.out.println("Item ID: " + newItem.getItemID() + " added successfully.");
    }
            
    // Update an existing menu item
    public void updateMenuItem(String vendorID) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Update Menu Item ---");

        // View vendor's menu
        viewMenu(vendorID);

        System.out.print("Enter the Item ID of the item you want to update: ");
        int itemID = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        MenuItem itemToUpdate = FileHandler.getMenuItemByID(vendorID, itemID);
        if (itemToUpdate == null) {
            System.out.println("Item not found.");
            return;
        }

        System.out.print("Enter new name (leave empty to keep current): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            itemToUpdate.setName(newName);
        }

        System.out.print("Enter new price (leave empty to keep current): ");
        String priceInput = scanner.nextLine();
        if (!priceInput.isEmpty()) {
            try {
                double newPrice = Double.parseDouble(priceInput);
                if (newPrice > 0) {
                    itemToUpdate.setPrice(newPrice);
                } else {
                    System.out.println("Invalid price. Keeping current.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid price format. Keeping current.");
            }
        }

        System.out.println("Select new category (leave empty to keep current):");
        System.out.println("1. Food");
        System.out.println("2. Drink");
        System.out.print("Enter your choice (1 or 2): ");
        String categoryInput = scanner.nextLine();
        if (!categoryInput.isEmpty()) {
            if (categoryInput.equals("1")) {
                itemToUpdate.setCategory("Food");
            } else if (categoryInput.equals("2")) {
                itemToUpdate.setCategory("Drink");
            } else {
                System.out.println("Invalid choice. Keeping current.");
            }
        }

        // Update the item in the file
        FileHandler.updateMenuItemInFile(vendorID, itemToUpdate);
        System.out.println("Item updated successfully.");
    }

    public void deleteMenuItem(String vendorID) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Delete Menu Item ---");
    
        // View vendor's menu
        viewMenu(vendorID);
    
        System.out.print("Enter the Item ID of the item you want to delete: ");
        int itemID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
    
        MenuItem itemToDelete = FileHandler.getMenuItemByID(vendorID, itemID);
        if (itemToDelete == null) {
            System.out.println("Item not found.");
            return;
        }
        
        // Remove the item from the file
        FileHandler.deleteMenuItemFromFile(vendorID, itemToDelete);
        System.out.println("Item deleted successfully.");
    }
}
