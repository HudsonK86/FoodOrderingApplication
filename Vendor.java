public class Vendor extends User {
    private double revenue;

    // Constructor for Vendor
    public Vendor(String name, String userID, String password, double revenue) {
        super(name, userID, password, "Vendor"); // Calls the parent class (User) constructor
        this.revenue = revenue;
    }

    // Getter for revenue
    public double getRevenue() {
        return revenue;
    }

    // Setter for revenue
    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

}
