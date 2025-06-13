public class MenuItem {
    private String name;
    private double price;
    private String category;
    private int quantity;
    private Inventory itemInventory;

    public MenuItem() {
        this.itemInventory = new Inventory(name);
    }
    
    public MenuItem(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.itemInventory = new Inventory(name);
    }
    
    public MenuItem(String name, double price, String category, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.itemInventory = new Inventory(name);
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity(){
        return quantity;
    }

    public double getTotal(){
        return this.quantity * this.price;
    }

    public Inventory getItemInventory() {
        return itemInventory;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + " (" + category + ") - $" + String.format("%.2f", price) + " Inventory:" + itemInventory.toString() +  ": ";
    }
}