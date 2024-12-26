import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userFilePath = "user.txt"; // Path to your user data file
        User loggedInUser = null; // Track the logged-in user

        while (true) {
            System.out.println("Welcome to the Food Ordering System");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Log in process
                     loggedInUser = User.login(userFilePath);
                    if (loggedInUser != null) {
                        // If the user is an administrator, show the admin menu
                        if (loggedInUser instanceof Administrator) {
                            Administrator admin = (Administrator) loggedInUser;
                            admin.adminMenu(userFilePath);
                        } else {
                            System.out.println("Welcome, " + loggedInUser.getName());
                            // Show user-specific menu here (if any)
                        }
                    }
                    break;

                case 2:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
