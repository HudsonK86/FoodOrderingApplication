public class Review
{
    private Customer customer;
    private int rating; // 1 to 5 stars
    private String comments;

    public Review(Customer customer, int rating, String comments)
    {
        this.customer = customer;
        this.rating = rating;
        this.comments = comments;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public int getRating()
    {
        return rating;
    }

    public void setRating(int rating)
    {
        this.rating = rating;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

}
