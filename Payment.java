public class Payment
{
    private double amount;
    private Customer customer;
    private String paymentMethod; // e.g., "Credit", "Cash"

    public Payment(double amount, Customer customer, String paymentMethod)
    {
        this.amount = amount;
        this.customer = customer;
        this.paymentMethod = paymentMethod;
    }

    public boolean processPayment()
    {
        // Process payment logic
        return false; // Placeholder for processing logic
    }
}
