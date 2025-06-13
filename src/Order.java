import java.util.ArrayList;

public class Order {
    private int orderId;
    private ArrayList<MenuItem> items;

    public Order(int orderId) {
        this.orderId = orderId;
        this.items = new ArrayList<>();
    }

    public int getOrderId() {
        return orderId;
    }
    
    public ArrayList<MenuItem> getItems() {
        return items;
    }
    public void addItem(MenuItem item) {
        items.add(item);
    }
    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    
    public void mergeDuplicateItems() {
        ArrayList<MenuItem> mergedItems = new ArrayList<>();
        for (MenuItem item : items) {
            boolean found = false;
            for (MenuItem mergedItem : mergedItems) {
                if (mergedItem.getName().equals(item.getName())) {
                    mergedItem.setQuantity(mergedItem.getQuantity() + item.getQuantity());
                    found = true;
                    break;
                }
            }
            if (!found) {
                MenuItem newItem = new MenuItem(item.getName(), item.getPrice(), item.getCategory(), item.getQuantity());
                mergedItems.add(newItem);
            }
        }
        items = mergedItems;
    }
}