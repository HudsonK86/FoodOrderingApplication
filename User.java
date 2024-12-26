import java.io.*;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class User {
    private String name;
    private String userID;
    private String password;
    private String role; // Vendor, Customer, Delivery, Administrator, Manager
    private static User loggedInUser = null; // Track currently logged-in user

    // Constructor
    public User(String name, String userID, String password, String role) {
        this.name = name;
        this.userID = userID;
        this.password = password;
        this.role = role;
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    // Login method
    public static User login(String userFilePath) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter User ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        // Validate credentials
        User user = FileHandler.validateUser(userFilePath, userID, password);
        if (user != null) {
            loggedInUser = user; // Set the logged-in user
            FileHandler.logActivity(userID, "logged-in", "activity_log.txt"); // Log login activity
            System.out.println("Login successful! Welcome, " + user.getName() + ".");
            return user; // Return the logged-in user
        } else {
            System.out.println("Invalid User ID or Password. Please try again.");
            return null; // Return null if login fails
        }
    }

    // Logout method
    public static void logout() {
        System.out.println("User " + loggedInUser.getName() + " has been logged out.");
        FileHandler.logActivity(loggedInUser.getUserID(), "logged-out", "activity_log.txt"); // Log logout activity
        loggedInUser = null;
    }

}

