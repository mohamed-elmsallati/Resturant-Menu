public class MenuItem {
    private String name;
    private double price;
    private String category;
    private int quantity;
    private int Weekly;
    private int Monthly;
    private int Yearly;

    public MenuItem() {
        this.name = "";
        this.price = 0.0;
        this.category = "";
        this.quantity = 0; 
    }
    
    public MenuItem(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
    
    public MenuItem(String name, double price, String category, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public MenuItem(String name, double price, String category, int Weekly, int Monthly, int Yearly) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.Weekly = Weekly;
        this.Monthly = Monthly;
        this.Yearly = Yearly;
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

    public int getWeekly(){
        return Weekly;
    }

    public int getMonthly(){
        return Monthly;
    }

    public int getYearly(){
        return Yearly;
    }

    public double getTotal(){
        return this.quantity * this.price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + " (" + category + ") - $" + String.format("%.2f", price) + " Inventory:" +  ": ";
    }
}