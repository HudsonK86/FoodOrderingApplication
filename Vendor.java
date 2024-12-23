import java.util.List;
import java.util.ArrayList;

public class Vendor extends User {
    private List<Menu> menus = new ArrayList<>();

    public Vendor(String name, String userID, String password) {
        super(name, userID, password);
    }

    public void addMenu(Menu menu) {
        menus.add(menu);
        System.out.println("Menu added: " + menu.getDetails());
    }

    public void acceptOrder(Order order) {
        System.out.println("Order accepted: " + order.getMenu().getDetails());
    }

    public void declineOrder(Order order, Customer customer) {
        customer.getWallet().topUp(order.getTotalAmount());
        System.out.println("Order declined. Refund issued.");
    }

    @Override
    public void login() {
        System.out.println("Vendor logged in.");
    }
}


// just putting another Vendor class by commenting

/*
import java.util.List;
import java.util.ArrayList;

public class Vendor extends User {
    private List<FoodItem> menuItems; // Composition with FoodItem

    public Vendor(String username, String password, String name, String email) {
        super(username, password, name, email, "Vendor");
        this.menuItems = new ArrayList<>();
    }

    public void addItem(FoodItem item) {
        menuItems.add(item);
    }

    public void updateItem(FoodItem item) {
        // Update logic
    }

    public void removeItem(FoodItem item) {
        menuItems.remove(item);
    }

    public void acceptOrder(Order order) {
        // Accept order logic
    }

    public void declineOrder(Order order) {
        // Decline order logic
    }

    public void updateOrderStatus(Order order, String status) {
        // Update order status
    }

    public List<Order> getOrderHistory() {
        // Return vendor's order history
        return new ArrayList<>();
    }

    public double calculateRevenue() {
        // Calculate and return vendor's revenue
        return 0.0;
    }

    // Other methods as required
}

 */