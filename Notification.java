public class Notification
{
    private String message;
    private String recipient; // Could be Vendor, Customer, or DeliveryRunner

    public Notification(String message, String recipient) 
    {
        this.message = message;
        this.recipient = recipient;
    }

    public void send()
    {
        // Logic to send notification
    }
}
