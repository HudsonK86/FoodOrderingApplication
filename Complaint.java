public class Complaint
{
    private Customer customer;
    private String complaintMessage;

    public Complaint(Customer customer, String complaintMessage)
    {
        this.customer = customer;
        this.complaintMessage = complaintMessage;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public String getComplaintMessage()
    {
        return complaintMessage;
    }

    public void setComplaintMessage(String complaintMessage)
    {
        this.complaintMessage = complaintMessage;
    }
    
}
