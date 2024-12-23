import java.util.List;
import java.util.ArrayList;

public class OrderHistory
{
    private List<Order> orders;

    public OrderHistory()
    {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order)
    {
        orders.add(order);
    }

    public List<Order> getOrders()
    {
        return orders;
    }
}
