import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private String name;

    private int WeeklyInventory;
    private int MonthlyInventory;
    private int YearlyInventory;

    private int maxWeekly = 30;
    private int maxMonthly = 100;
    private int maxYearly = 500;

    private static List<Inventory> inventories = new ArrayList<>();
    
    public Inventory(String name){
        this.name = name;
        WeeklyInventory = maxWeekly;
        MonthlyInventory = maxMonthly;
        YearlyInventory = maxYearly;
        inventories.add(this);
    }

    public Inventory(String name, int Weekly, int Monthly, int Yearly){
        this.name =  name;
        this.WeeklyInventory = Weekly;
        this.MonthlyInventory = Monthly;
        this.YearlyInventory = Yearly;
        maxWeekly = Weekly;
        maxMonthly = Monthly;
        maxYearly = Yearly;
        inventories.add(this);
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    
    public void withdraw(int quantity){
        WeeklyInventory -= quantity;
    }
    
    public void deposit(int quantity){
        WeeklyInventory += quantity;
    }

    public void restock(int choice){
        switch (choice) {
            case 1:
                int Weeklyremaining = maxWeekly - WeeklyInventory;
                WeeklyInventory = maxWeekly;
                MonthlyInventory -= Weeklyremaining;
                break;
            
            case 2:
                int Monthlyremaining = maxMonthly - MonthlyInventory;
                MonthlyInventory = maxMonthly;
                YearlyInventory -= Monthlyremaining;
                break;

            case 3:
                YearlyInventory = maxYearly;
                break;
        
            default:
                System.out.println("invalid input!");
        }
    }

    public static Inventory getInventoryByName(String name) {
        for (Inventory inv : inventories) {
            if (inv.getName().equalsIgnoreCase(name)) {
                return inv;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return this.name + " WeeklyInventory: " + WeeklyInventory + " MonthlyInventory:"  + MonthlyInventory + " YearlyInventory:" + YearlyInventory;
    }

    public int getWeeklyInventory() {
        return WeeklyInventory;
    }
    public int getMonthlyInventory() {
        return MonthlyInventory;
    }
    public int getYearlyInventory() {
        return YearlyInventory;
    }

}