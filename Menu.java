import java.util.List;
import java.util.ArrayList;

public class Menu
{
    private List<FoodItem> items;

    public Menu()
    {
        this.items = new ArrayList<>();
    }

    public void addFoodItem(FoodItem item)
    {
        items.add(item);
    }

    public void removeFoodItem(FoodItem item)
    {
        items.remove(item);
    }

    // Other methods as required
}

// btw FoodItem is the previous Menu but with more attributes now,
// this current Menu is gonna use FoodItem as array I guess