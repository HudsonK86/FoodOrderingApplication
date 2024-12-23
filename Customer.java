import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private Wallet wallet;
    private List<Order> orderHistory = new ArrayList<>();

    public Customer(String name, String userID, String password) {
        super(name, userID, password);
        this.wallet = new Wallet();
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void placeOrder(Menu menu, String orderType, double deliveryFee) {
        if (wallet.getBalance() < menu.getPrice() + deliveryFee) {
            System.out.println("Insufficient balance.");
            return;
        }
        Order order = new Order(menu, orderType, deliveryFee);
        orderHistory.add(order);
        wallet.deduct(menu.getPrice() + deliveryFee);
        System.out.println("Order placed: " + menu.getDetails());
    }

    @Override
    public void login() {
        System.out.println("Customer logged in.");
    }
}

// just putting another Customer class by commenting

/*
import java.util.List;
import java.util.ArrayList;

public class Customer extends User {
    private CreditWallet wallet; // Aggregation with CreditWallet
    private List<Order> orderHistory; // Aggregation with Order

    public Customer(String username, String password, String name, String email) {
        super(username, password, name, email, "Customer");
        this.wallet = new CreditWallet();
        this.orderHistory = new ArrayList<>();
    }

    public void placeOrder(Order order) {
        // Place order logic
    }

    public void cancelOrder(Order order) {
        // Cancel order logic
    }

    public void provideReview(Order order, Review review) {
        // Provide a review for the order
    }

    public void reloadCredit(double amount, Administrator admin) {
        // Reload credit via admin
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    // Other methods as required
}

 */