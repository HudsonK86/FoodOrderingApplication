public class Receipt
{
    private String receiptId;
    private Transaction transaction;
    private String message; // E.g., "Order completed"

    public Receipt(String receiptId, Transaction transaction, String message)
    {
        this.receiptId = receiptId;
        this.transaction = transaction;
        this.message = message;
    }

    public void sendToCustomer(Customer customer)
    {
        // Logic to send receipt to customer
    }
}
