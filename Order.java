/* public class Order {
    private Menu menu;
    private String orderType;
    private double deliveryFee;

    public Order(Menu menu, String orderType, double deliveryFee) {
        this.menu = menu;
        this.orderType = orderType;
        this.deliveryFee = deliveryFee;
    }

    public Menu getMenu() {
        return menu;
    }

    public double getTotalAmount() {
        return menu.getPrice() + deliveryFee;
    }
} */

// I made comment your previous Order class just in case

import java.util.List;
import java.util.ArrayList;

public class Order {
    private String orderId;
    private Customer customer;
    private Vendor vendor;
    private List<FoodItem> items;
    private double totalPrice;
    private String status; // "Pending", "Accepted", "Cancelled"
    private String deliveryType; // "Dine-in", "Takeaway", "Delivery"
    private DeliveryTask deliveryTask; // Aggregation with DeliveryTask (if delivery selected)

    public Order(String orderId, Customer customer, Vendor vendor) {
        this.orderId = orderId;
        this.customer = customer;
        this.vendor = vendor;
        this.items = new ArrayList<>();
    }

    public void addFoodItem(FoodItem item) {
        items.add(item);
    }

    public double calculateTotalPrice() {
        // Calculate total price for the order
        return 0.0;
    }

    // Other methods as required
}

