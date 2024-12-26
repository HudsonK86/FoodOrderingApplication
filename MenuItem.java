public class MenuItem {
    private int itemID;
    private String name;
    private double price;
    private String category; // Either "Food" or "Drink"

    // Constructor
    public MenuItem(int itemID, String name, double price, String category) {
        this.itemID = itemID;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    // Getters and Setters
    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return itemID + "," + category + "," + name + "," + price;
    }
}
