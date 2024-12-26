import java.io.*;
import java.util.*;

public class Menu {
    private List<MenuItem> menuItems;
    private static final String MENU_FILE = "menu.txt";

    // Constructor
    public Menu() {
        menuItems = new ArrayList<>();
        loadMenu(); // Load menu from file if it exists
    }

    // Load menu from file
    public void loadMenu() {
        try (BufferedReader reader = new BufferedReader(new FileReader(MENU_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 3) {
                    String category = parts[0];
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    menuItems.add(new MenuItem(name, price, category));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading menu: " + e.getMessage());
        }
    }

    // Save menu to file
    public void saveMenu() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MENU_FILE))) {
            for (MenuItem item : menuItems) {
                writer.write(item.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving menu: " + e.getMessage());
        }
    }

    // Add menu item
    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
        saveMenu();
    }

    // Update menu item
    public void updateMenuItem(int index, MenuItem newItem) {
        if (index >= 0 && index < menuItems.size()) {
            menuItems.set(index, newItem);
            saveMenu();
        }
    }

    // Delete menu item
    public void deleteMenuItem(int index) {
        if (index >= 0 && index < menuItems.size()) {
            menuItems.remove(index);
            saveMenu();
        }
    }

    // Display menu
    public void displayMenu() {
        System.out.println("Menu:");
        for (MenuItem item : menuItems) {
            System.out.println(item);
        }
    }
}
