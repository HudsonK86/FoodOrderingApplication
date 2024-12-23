public class Transaction
{
    private String transactionId;
    private Customer customer;
    private double amount;
    private String type; // "Payment", "Refund"
    private String status; // "Success", "Failed"

    public Transaction(String transactionId, Customer customer, double amount, String type, String status)
    {
        this.transactionId = transactionId;
        this.customer = customer;
        this.amount = amount;
        this.type = type;
        this.status = status;
    }

    public String getTransactionId()
    {
        return transactionId;
    }

    public void setTransactionId(String transactionId)
    {
        this.transactionId = transactionId;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
    
}
