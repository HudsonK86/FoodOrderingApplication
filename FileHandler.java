import java.io.*;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;


public class FileHandler {
    private static final String MENU_FILE = "menu.txt";
    private static final String USER_FILE = "user.txt";
    private static final String LOG_FILE = "log.txt";
    private static final String ORDER_FILE = "order.txt";
    private static final String TRANSACTION_FILE = "transaction.txt";
    private static final String DELIVERY_FILE = "delivery.txt";
    private static final String RECEIPT_FILE = "receipt.txt";
    
    // Validate user credentials by checking against the file
    public static User validateUser(String userFilePath, String userID, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(userFilePath))) {
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
    public static void logActivity(String userID, String action, String logFilePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            writer.write("[" + timestamp + "]," + userID + "," + action);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }

    // Method to get all logs for a specific user
    public static List<String> getUserLogActivities(String logFilePath, String userID) {
        List<String> userLogs = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
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
    public static void writeUserToFile(String userFilePath, String userID) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFilePath, true))) {
            writer.write(userID);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing user to file: " + e.getMessage());
        }
    }

    // Method to check if a User ID already exists in the file
    public static boolean isUserIDExist(String userFilePath, String userID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(userFilePath))) {
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
    public static boolean updateUserInFile(String userFilePath, String userIDToUpdate, String newPassword) {
        try {
            File inputFile = new File(userFilePath);
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
    public static boolean topUpWallet(String userFilePath, String customerID, double topUpAmount) {
        try {
            File inputFile = new File(userFilePath);
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
    public static boolean isCustomer(String userFilePath, String customerID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(userFilePath))) {
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
    public static String getUserDetails(String userFilePath, String userID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(userFilePath))) {
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
    
}

