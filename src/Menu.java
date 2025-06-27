import java.util.ArrayList;
import java.util.List;

public class Menu {
    // data structure to hold menu items  category | names | prices | Daily | Weekly | Monthly | Yearly
    private static final Object[][] MENU_DATA = {
        {"Soft Drinks", new String[]{"Coke", "Pepsi", "Sprite"}, new double[]{1.5, 1.5, 1.5}, new int[]{80,80,80} ,new int[]{180,180,180}, new int[]{360,360,360}, new int[]{4500,4500,4500}},
        {"Pizza", new String[]{"Margherita", "Pepperoni", "Veggie"}, new double[]{8.0, 9.0, 8.5}, new int[]{70,70,70} ,new int[]{150,150,150}, new int[]{225,225,225}, new int[]{2812,2812,2812}},
        {"Sandwiches", new String[]{"Chicken Sandwich", "Veggie Sandwich", "Burger"}, new double[]{6.0, 5.5, 7.0}, new int[]{100,100,100}, new int[]{170,170,170}, new int[]{315,315,315}, new int[]{3937,3937,3937}},
        {"Meals", new String[]{"Chicken Meal", "Beef Meal", "Veggie Meal"}, new double[]{9.5, 9.9, 8.0}, new int[]{50,50,50}, new int[]{80,80,80}, new int[]{180,180,180}, new int[]{2250,2250,2250}},
        {"Juices", new String[]{"Orange Juice", "Apple Juice", "Grape Juice"}, new double[]{2.0, 2.0, 2.5}, new int[]{80,80,80}, new int[]{180,180,180}, new int[]{360,360,360}, new int[]{4500,4500,4500}},
        {"Salads", new String[]{"Caesar Salad", "Greek Salad", "Garden Salad"}, new double[]{5.0, 5.5, 4.5}, new int[]{90,90,90},new int[]{150,150,150}, new int[]{225,225,225}, new int[]{2812,2812,2812}}
    };

    private List<MenuItem> items;

    public Menu() {
        items = new ArrayList<>();
        for (Object[] entry : MENU_DATA) {
            String category = (String) entry[0];
            String[] names = (String[]) entry[1];
            double[] prices = (double[]) entry[2];
            int[] Daily = (int[]) entry[3];
            int[] Weekly = (int[]) entry[4];
            int[] Monthly =  (int[]) entry[5];
            int[] Yearly  =  (int[]) entry[6];
            for (int i = 0; i < names.length; i++) {
                items.add(new MenuItem(names[i], prices[i], category,Daily[i], Weekly[i], Monthly[i], Yearly[i]));
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

    public void displayItems(List<Inventory> inventories) {
    System.out.println("--------------------------------------------------------------------------");
    System.out.printf("%-4s %-20s %-15s %-10s %-10s\n", "No.", "Item Name", "Category", "Price", "In Stock");
    System.out.println("--------------------------------------------------------------------------");
    int index = 1;
    for (MenuItem item : items) {
        int stock = 0;
        for (Inventory inv : inventories) {
            if (inv.getName().equalsIgnoreCase(item.getName())) {
                stock = inv.getDailyInventory();
                break;
            }
        }
        System.out.printf("%-4d %-20s %-15s $%-10.2f %-10d\n", index++, item.getName(), item.getCategory(), item.getPrice(), stock);
    }
    System.out.println("--------------------------------------------------------------------------");
    }
}
