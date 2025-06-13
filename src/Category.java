public class Category {
    private String name;
    private String[] items;

    public Category(String name, String[] items) {
        this.name = name;
        this.items = items;
    }
    public void addItem(String item) {
        String[] newItems = new String[items.length + 1];
        System.arraycopy(items, 0, newItems, 0, items.length);
        newItems[items.length] = item;
        items = newItems;
    }
    public String getName() {
        return name;
    }
    public String[] getItems() {
        return items;
    }
}