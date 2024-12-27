import java.io.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Administrator extends User {

    // Constructor
    public Administrator(String name, String userID, String password) {
        super(name, userID, password, "Administrator");
    }

    // Administrator Menu
    public void adminMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nAdministrator Menu:");
            System.out.println("1. Register User");
            System.out.println("2. Change Password");
            System.out.println("3. Top Up Wallet");
            System.out.println("4. View User Details");
            System.out.println("5. View User Logs"); 
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    changePassword();
                    break;
                case 3:
                    topUpWallet();
                    break;
                case 4:
                    viewUserDetails(); // View user details option
                    break;
                case 5:
                    viewLogActivities(); // View user logs option
                    break;
                case 6:
                    logout();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // Register a new account
    public void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nRegister User");

        // Name validation
        String name;
        while (true) {
            System.out.print("Enter Name: ");
            name = scanner.nextLine();
            if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {  // Check if name contains only letters and spaces
                System.out.println("Invalid name. Name can't be empty and should not contain special characters or numbers.");
            } else {
                break;
            }
        }

        // User ID validation
        String userID;
        while (true) {
            System.out.print("Enter User ID: ");
            userID = scanner.nextLine();
            if (userID.isEmpty() || userID.contains(" ") || FileHandler.isUserIDExist(userID)) {
                System.out.println("Invalid User ID. It can't be empty, can't contain spaces, and must be unique.");
            } else {
                break;
            }
        }

        // Password validation
        String password;
        while (true) {
            System.out.print("Enter Password: ");
            password = scanner.nextLine();
            if (password.isEmpty()) {
                System.out.println("Password can't be empty.");
            } else {
                break;
            }
        }

        // Role selection validation
        String role = "";
        while (true) {
            System.out.println("Select Role:");
            System.out.println("1. Customer");
            System.out.println("2. Vendor");
            System.out.println("3. Delivery");
            System.out.println("4. Manager");
            System.out.print("Enter option (1/2/3/4): ");
            int roleChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (roleChoice == 1) {
                role = "Customer";
                break;
            } else if (roleChoice == 2) {
                role = "Vendor";
                break;
            } else if (roleChoice == 3) {
                role = "Delivery";
                break;
            } else if (roleChoice == 4) {
                role = "Manager";
                break;
            } else {
                System.out.println("Invalid choice. Please select 1, 2, 3 or 4.");
            }
        }

        // Additional info based on role
        String additionalInfo = "";
        if (role.equals("Customer")) {
            System.out.print("Enter Initial Wallet Balance: ");
            additionalInfo = String.valueOf(scanner.nextDouble());
        } else if (role.equals("Vendor") || role.equals("Delivery")) {
            additionalInfo = "0.00";  // Set initial balance or revenue to 0.00
        }

        // Construct user data line
        String userData = name + "," + userID + "," + password + "," + role;
        if (!role.equals("Manager")) {
            userData += "," + additionalInfo;
        }

        // Write user information to file
        FileHandler.writeUserToFile(userData);
        System.out.println("User registered successfully!");
    }

    // Update an existing account
    public void changePassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter User ID to Change Password: ");
        String userIDToUpdate = scanner.nextLine();
    
        if ("Administrator".equalsIgnoreCase(userIDToUpdate)) {
            System.out.println("Cannot change the password of the Administrator account.");
            return;
        }
    
        if (!FileHandler.isUserIDExist(userIDToUpdate)) {
            System.out.println("User ID not found!");
            return;
        }
    
        String password;
        while (true) {
            System.out.print("Enter Password: ");
            password = scanner.nextLine();
            if (password.isEmpty()) {
                System.out.println("Password can't be empty.");
            } else {
                break;
            }
        }
    
        FileHandler.updateUserInFile(userIDToUpdate, password);
        System.out.println("Password updated successfully!");
    }
    
    // Top up wallet for a customer
    public void topUpWallet() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Enter Customer User ID to Top Up: ");
        String customerID = scanner.nextLine();
    
        // Validate if it's a valid Customer ID
        if (!FileHandler.isCustomer(customerID)) {
            System.out.println("Invalid User ID or not a Customer!");
            return;
        }
    
        double topUpAmount;
        while (true) {
            System.out.print("Enter Amount to Top Up: ");
            topUpAmount = scanner.nextDouble();
            if (topUpAmount <= 0) {
                System.out.println("Amount must be positive. Please try again.");
            } else {
                break;
            }
        }
    
        boolean result = FileHandler.topUpWallet(customerID, topUpAmount);
        if (result) {
            System.out.println("Wallet topped up successfully!");
        } else {
            System.out.println("Error occurred while topping up the wallet!");
        }
    }
    
    // View user details
    public void viewUserDetails() {
        Scanner scanner = new Scanner(System.in);
    
        // Prompt the administrator to enter a User ID to view details
        System.out.print("Enter User ID to View Details: ");
        String userIDToView = scanner.nextLine();
    
        // Check if the user exists
        if (!FileHandler.isUserIDExist(userIDToView)) {
            System.out.println("User ID not found!");
            return;
        }
    
        // Fetch user details from the file
        String userDetails = FileHandler.getUserDetails(userIDToView);
    
        // If user details are found, display them
        if (userDetails != null) {
            System.out.println("\nUser Details:");
            String[] userData = userDetails.split(",");  // Split data assuming comma-separated format
            
            String name = userData[0];
            String userID = userData[1];
            String password = userData[2];
            String role = userData[3];
    
            // Display common information
            System.out.println("Name: " + name);
            System.out.println("ID: " + userID);
            System.out.println("Role: " + role);
            
            // Display additional information based on the role
            String balance = userData[4];
            if (role.equals("Customer")) {
                System.out.println("Balance: " + balance);
            } else if (role.equals("Vendor")) {
                System.out.println("Revenue: " + balance);
            } else if (role.equals("Delivery")) {
                System.out.println("Earning: " +balance);
            }
        } else {
            System.out.println("No details found for this user.");
        }
    }
    
    // Method to display logs for a specific user
    public void viewLogActivities() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter User ID to view activities: ");
        String userID = scanner.nextLine();
    
        // Check if the user ID exists in the user file
        if (!FileHandler.isUserIDExist(userID)) {
            System.out.println("User ID not found!");
            return; // Exit if the user doesn't exist
        }
    
        // Fetch logs using FileHandler method
        List<String> userLogs = FileHandler.getUserLogActivities(userID);
    
        if (userLogs.isEmpty()) {
            System.out.println("No activities found for User ID: " + userID);
        } else {
            System.out.println("Activity Log for User ID: " + userID);
            System.out.println("-----------------------------------");
            for (String log : userLogs) {
                System.out.println(log);
            }
        }
    }    
}
