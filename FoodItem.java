public class FoodItem
{
    private String name;
    private double price;
    private String description;
    private Vendor vendor;

    public FoodItem(String name, double price, String description, Vendor vendor)
    {
        this.name = name;
        this.price = price;
        this.description = description;
        this.vendor = vendor;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Vendor getVendor()
    {
        return vendor;
    }

    public void setVendor(Vendor vendor)
    {
        this.vendor = vendor;
    }
    
}
