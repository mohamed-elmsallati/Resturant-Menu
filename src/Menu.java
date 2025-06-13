import java.util.ArrayList;
import java.util.List;

public class Menu {
    private static final Object[][] MENU_DATA = {
        {"Soft Drinks", new String[]{"Coke", "Pepsi", "Sprite"}, new double[]{1.5, 1.5, 1.5}},
        {"Pizza", new String[]{"Margherita", "Pepperoni", "Veggie"}, new double[]{8.0, 9.0, 8.5}},
        {"Sandwiches", new String[]{"Chicken Sandwich", "Veggie Sandwich", "Burger"}, new double[]{6.0, 5.5, 7.0}},
        {"Meals", new String[]{"Chicken Meal", "Beef Meal", "Veggie Meal"}, new double[]{9.5, 9.9, 8.0}},
        {"Juices", new String[]{"Orange Juice", "Apple Juice", "Grape Juice"}, new double[]{2.0, 2.0, 2.5}},
        {"Salads", new String[]{"Caesar Salad", "Greek Salad", "Garden Salad"}, new double[]{5.0, 5.5, 4.5}}
    };
    private List<MenuItem> items;

    public Menu() {
        items = new ArrayList<>();
        for (Object[] entry : MENU_DATA) {
            String category = (String) entry[0];
            String[] names = (String[]) entry[1];
            double[] prices = (double[]) entry[2];
            for (int i = 0; i < names.length; i++) {
                items.add(new MenuItem(names[i], prices[i], category));
            }
        }
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void displayNames(){
        for (MenuItem item : items) {
            System.out.println(item.getName());
        }
    }

    public void displayItems() {
        String currentCategory = "";
        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            if (!item.getCategory().equals(currentCategory)) {
                currentCategory = item.getCategory();
                System.out.println("\n=== " + currentCategory + " ===");
            }
            System.out.printf("%2d. %-20s $%.2f Inventory: %s%n", i, item.getName(), item.getPrice(), item.getItemInventory().getWeeklyInventory());
        }
    }
}
