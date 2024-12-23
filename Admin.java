import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    private List<Customer> customers = new ArrayList<>();
    private List<Vendor> vendors = new ArrayList<>();

    public Administrator(String name, String userID, String password) {
        super(name, userID, password);
    }

    public void registerCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("Customer registered: " + customer.getName());
    }

    public void registerVendor(Vendor vendor) {
        vendors.add(vendor);
        System.out.println("Vendor registered: " + vendor.getName());
    }

    public void updateWallet(Customer customer, double amount) {
        customer.getWallet().topUp(amount);
        System.out.println("Wallet updated for customer: " + customer.getName());
    }

    @Override
    public void login() {
        System.out.println("Administrator logged in.");
    }

    
}

