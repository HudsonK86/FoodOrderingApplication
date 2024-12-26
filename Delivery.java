public class Delivery extends User {
    private double earnings; // Earnings of the delivery person

    // Constructor for Delivery
    public Delivery(String name, String userID, String password, double earnings) {
        super(name, userID, password, "Delivery"); // Calls the parent class (User) constructor
        this.earnings = earnings;
    }

    // Getter for earnings
    public double getEarnings() {
        return earnings;
    }

    // Setter for earnings
    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }

}
