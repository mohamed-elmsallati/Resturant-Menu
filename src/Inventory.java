public class Inventory {

    private String name;

    private int WeeklyInventory;
    private int MonthlyInventory;
    private int YearlyInventory;

    private int maxWeekly = 30;
    private int maxMonthly = 120;
    private int maxYearly = 1440;

    public Inventory(String name){
        this.name = name;
        WeeklyInventory = maxWeekly;
        MonthlyInventory = maxMonthly;
        YearlyInventory = maxYearly;
    }

    public Inventory(String name, int Weekly, int Monthly, int Yearly){
        this.name =  name;
        this.WeeklyInventory = Weekly;
        this.MonthlyInventory = Monthly;
        this.YearlyInventory = Yearly;
        maxWeekly = Weekly;
        maxMonthly = Monthly;
        maxYearly = Yearly;
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
    
    public void Restuck(String choice){
        switch (choice) {
            case "Weekly":
                int Weeklyremaining = maxWeekly - WeeklyInventory;
                WeeklyInventory = maxWeekly;
                MonthlyInventory -= Weeklyremaining;
                break;
            
            case "Monthly":
                int Monthlyremaining = maxMonthly - MonthlyInventory;
                MonthlyInventory = maxMonthly;
                YearlyInventory -= Monthlyremaining;
                break;
            
            case "Yearly":
                YearlyInventory = maxYearly;
                break;
        
            default:
                System.out.println("invalid input!");
        }
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