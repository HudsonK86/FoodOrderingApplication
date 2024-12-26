public class Customer extends User {
    private double walletBalance;

    // Constructor
    public Customer(String name, String userID, String password, double walletBalance) {
        super(name, userID, password, "Customer");
        this.walletBalance = walletBalance;
    }

    // Getter and Setter methods for walletBalance
    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

    
}
