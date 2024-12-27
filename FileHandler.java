import java.io.*;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;


public class FileHandler {
    private static final String MENU_FILE = "menu.txt";
    private static final String USER_FILE = "user.txt";
    private static final String LOG_FILE = "log.txt";
    private static final String ORDER_FILE = "order.txt";
    private static final String TRANSACTION_FILE = "transaction.txt";
    private static final String DELIVERY_FILE = "delivery.txt";
    private static final String RECEIPT_FILE = "receipt.txt";
    
    // Validate user credentials by checking against the file
    public static User validateUser(String userID, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",\\s*");

                String name = userDetails[0];
                String id = userDetails[1];
                String pass = userDetails[2];
                String role = userDetails[3];

                if (id.equals(userID) && pass.equals(password)) {
                    switch (role) {
                        case "Customer":
                            if (userDetails.length == 5) {
                                double balance = Double.parseDouble(userDetails[4]);
                                return new Customer(name, id, pass, balance);
                            }
                            break;
                        case "Administrator":
                            return new Administrator(name, id, pass);
                        case "Vendor":
                            if (userDetails.length == 5) {
                                double revenue = Double.parseDouble(userDetails[4]);
                                return new Vendor(name, id, pass, revenue);
                            }
                            break;
                        case "Delivery":
                            if (userDetails.length == 5) {
                                double earnings = Double.parseDouble(userDetails[4]);
                                return new Delivery(name, id, pass, earnings);
                            }
                            break;
                        case "Manager":
                            return new Manager(name, id, pass);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading user file: " + e.getMessage());
        }
        return null; // Return null if no match is found
    }

    // Log user activity (login/logout) to a file
    public static void logActivity(String userID, String action) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            writer.write("[" + timestamp + "]," + userID + "," + action);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }

    // Method to get all logs for a specific user
    public static List<String> getUserLogActivities(String userID) {
        List<String> userLogs = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
    
            while ((line = reader.readLine()) != null) {
                // Check if the log entry corresponds to the given userID
                if (line.contains("," + userID + ",")) {
                    userLogs.add(line); // Add matching log entry to the list
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading log file: " + e.getMessage());
        }
    
        return userLogs; // Return the list of logs for the user
    }
    
    // Method to write a new user to the file
    public static void writeUserToFile(String userID) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            writer.write(userID);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing user to file: " + e.getMessage());
        }
    }

    // Method to check if a User ID already exists in the file
    public static boolean isUserIDExist(String userID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",\\s*");
                if (userDetails.length > 1 && userDetails[1].equals(userID)) {
                    return true;  // User ID already exists
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading user file: " + e.getMessage());
        }
        return false;  // User ID doesn't exist
    }

    // Method to update a user in the file
    public static boolean updateUserInFile(String userIDToUpdate, String newPassword) {
        try {
            File inputFile = new File(USER_FILE);
            File tempFile = new File("temp.txt");
    
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
    
            String line;
            boolean found = false;
    
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",\\s*");
                if (userDetails.length > 1 && userDetails[1].equals(userIDToUpdate)) {
                    found = true;
                    userDetails[2] = newPassword; // Update the password field
                    writer.write(String.join(",", userDetails)); // Join the updated details
                } else {
                    writer.write(line); // Write the original line
                }
                writer.newLine();
            }
    
            reader.close();
            writer.close();
    
            if (found) {
                if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                    System.out.println("Error replacing the original file.");
                    return false;
                }
            } else {
                tempFile.delete();
            }
    
            return found;
        } catch (IOException e) {
            System.out.println("Error updating account: " + e.getMessage());
            return false;
        }
    }
    
    // Method to top up a customer's wallet
    public static boolean topUpWallet(String customerID, double topUpAmount) {
        try {
            File inputFile = new File(USER_FILE);
            File tempFile = new File("temp.txt");
    
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
    
            String line;
            boolean found = false;
    
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",\\s*");
                if (userDetails[1].equals(customerID) && userDetails[3].equals("Customer")) {
                    found = true;
    
                    double currentBalance = Double.parseDouble(userDetails[4]);
                    double newBalance = currentBalance + topUpAmount;
    
                    String updatedLine = String.join(",", userDetails[0], userDetails[1], userDetails[2], userDetails[3], String.valueOf(newBalance));
                    writer.write(updatedLine);
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }
    
            reader.close();
            writer.close();
    
            if (found) {
                inputFile.delete();
                tempFile.renameTo(inputFile);
                return true;
            } else {
                tempFile.delete();
                return false;
            }
    
        } catch (IOException e) {
            System.out.println("Error topping up wallet: " + e.getMessage());
            return false;
        }
    }

    // Method to check if a given User ID is a Customer
    public static boolean isCustomer(String customerID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",\\s*");
                if (userDetails[1].equals(customerID) && userDetails[3].equals("Customer")) {
                    return true; // Valid Customer ID
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return false; // Not a Customer
    }
    
    // Method to get the details of a user from the file
    public static String getUserDetails(String userID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData[1].equals(userID)) { // Check if the userID matches
                    return line;  // Return the whole user data line (name, userID, password, role, balance)
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Return null if the user is not found
    }
    
    // Method to load all menu items from the file
    public static List<MenuItem> loadMenuData() {
        List<MenuItem> menuItems = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(MENU_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String vendorID = parts[0];
                    int itemID = Integer.parseInt(parts[1]);
                    String category = parts[2];
                    String name = parts[3];
                    double price = Double.parseDouble(parts[4]);
                    menuItems.add(new MenuItem(vendorID, itemID, name, price, category));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading menu file: " + e.getMessage());
        }
        return menuItems;
    }

    // Method to save all menu items to the file
    public static void saveMenuData(List<MenuItem> menuItems) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MENU_FILE))) {
            for (MenuItem item : menuItems) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to menu file: " + e.getMessage());
        }
    }
   
    // Updates an existing menu item in the data source.
    public static void updateMenuItemInFile(String vendorID, MenuItem updatedItem) {
        List<MenuItem> menuItems = loadMenuData();

        // Find and update the matching menu item
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem item = menuItems.get(i);
            if (item.getItemID() == updatedItem.getItemID() && item.getVendorID().equals(vendorID)) {
                menuItems.set(i, updatedItem);
                break;
            }
        }

        // Save updated list to file
        saveMenuData(menuItems);
    }

    // Method to read menu items by vendor from the file
    public static List<MenuItem> readMenuItemsByVendor(String vendorID) {
        List<MenuItem> allMenuItems = loadMenuData();
            return allMenuItems.stream()
                                .filter(item -> item.getVendorID().equals(vendorID))
                                .collect(Collectors.toList());
    }
        
    // Retrieves a specific menu item by ItemID for a vendor.
    public static MenuItem getMenuItemByID(String vendorID, int itemID) {
        List<MenuItem> menuItems = loadMenuData();
        for (MenuItem item : menuItems) {
            System.out.println("Checking item: " + item);
            if (item.getItemID() == itemID && item.getVendorID().equals(vendorID)) {
                System.out.println("Found item to delete: " + item);
                return item;
            }
        }
        return null;
    }

    // Deletes a specific menu item from the data source.
    public static void deleteMenuItemFromFile(String vendorID, MenuItem itemToDelete) {
        List<MenuItem> menuItems = loadMenuData();
        
        // Debugging: Show all items before deletion attempt
        System.out.println("Attempting to delete item: " + itemToDelete);
        
        // Remove the matching item
        boolean removed = menuItems.removeIf(item -> {
            boolean matches = item.getItemID() == itemToDelete.getItemID() && item.getVendorID().equals(vendorID);
            if (matches) {
                System.out.println("Matching item found: " + item); // Debugging
            }
            return matches;
        });
    
        if (removed) {
            // Save updated list to file
            saveMenuData(menuItems);
            System.out.println("Item ID: " + itemToDelete.getItemID() + " deleted successfully.");
        } else {
            System.out.println("Item not found for deletion.");
        }
    }
    
    // Method to generate a unique Item ID
    public static int generateUniqueItemID() {
        Set<Integer> existingItemIDs = new HashSet<>();
        
        // Load all menu items using the loadMenuData method
        List<MenuItem> menuItems = loadMenuData();

        // Add all existing ItemIDs from the loaded menu items to the set
        for (MenuItem item : menuItems) {
            existingItemIDs.add(item.getItemID());
        }

        // Generate a unique ID between 100 and 999
        Random random = new Random();
        int newItemID;
        do {
            newItemID = 100 + random.nextInt(900); // Generate a number between 100 and 999
        } while (existingItemIDs.contains(newItemID)); // Ensure the ID does not already exist

        return newItemID;
    }
}

