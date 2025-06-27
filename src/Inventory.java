import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private String name;

    private int DailyInventory;
    private int WeeklyInventory;
    private int MonthlyInventory;
    private int YearlyInventory;

    private int maxDaily = 10;
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

    public Inventory(String name, int Daily, int Weekly, int Monthly, int Yearly){
        this.name =  name;
        this.DailyInventory = Daily;
        this.WeeklyInventory = Weekly;
        this.MonthlyInventory = Monthly;
        this.YearlyInventory = Yearly;

        maxDaily = Daily;
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
        DailyInventory -= quantity;
    }
    
    public void deposit(int quantity){
        DailyInventory += quantity;
    }

    public void restock(int choice){
        switch (choice) {
            case 1:
                int Dailyremaining = maxDaily - DailyInventory;
                DailyInventory = maxDaily;
                WeeklyInventory -= Dailyremaining;
                break;
            
            case 2:
                int Weeklyremaining = maxWeekly - WeeklyInventory;
                WeeklyInventory = maxWeekly;
                MonthlyInventory -= Weeklyremaining;
                break;

            case 3:
                int Monthlyremaining = maxMonthly - MonthlyInventory;
                MonthlyInventory = maxMonthly;
                YearlyInventory -= Monthlyremaining;
                break;

            case 4:
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

    public int getDailyInventory() {
        return DailyInventory;
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