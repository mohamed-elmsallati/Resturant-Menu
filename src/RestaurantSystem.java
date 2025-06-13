import java.util.ArrayList;
import java.util.Scanner;

public class RestaurantSystem {
    private ArrayList<Order> orders;
    private static int orderCounter = 0; 
    private static final Scanner sharedScanner = new Scanner(System.in);
    private Menu menu = new Menu() ;


    public RestaurantSystem() {
        this.orders = new ArrayList<>();
        // this.inventory = new Inventory();
    }

    private Scanner getScanner() {
        return sharedScanner;
    }

    private Menu getMenu(){
        return menu;
    }

    private static void displayMainMenu() {
        System.out.println("Please select an option from the menu below:");
        System.out.println("1. New Order");
        System.out.println("2. Modify Order");
        System.out.println("3. Cancel Order");
        System.out.println("4. View Order");
        System.out.println("5. Invoice");
        System.out.println("6. Inventory");
        System.out.println("7. Exit");
        System.out.println("Please enter your choice (1-7): ");
    }

   
    public void run() {
        boolean running = true;

        while (running) {
            while (true) {
            boolean removed = false;
            for (int i = 0; i < orders.size(); i++) {
                if (orders.get(i).getItems().isEmpty()) {
                    orders.remove(i);
                    removed = true;
                    break; 
                }
                else {
                    orders.get(i).mergeDuplicateItems(); 
                }
            }

            if (!removed) {
            break;
                }

            }

            displayMainMenu();
            System.out.print("Input :");
            int choice = getScanner().nextInt();

            switch (choice) {
                case 1:
                    newOrder();
                    break;
                case 2:
                    modifyOrder();
                    break;
                case 3:
                    cancelOrder();
                    break;
                case 4:
                    viewOrder();
                    break;
                case 5:
                    generateInvoice();
                    break;
                case 6:
                    System.out.println("Enter Action: \n1.View Inventory\n2.Restock");
                    int choice2 = getScanner().nextInt();
                    switch (choice2) {
                        case 1:
                            for (MenuItem item : getMenu().getItems()) {
                                System.out.println(item.getName() + " - Weekly Inventory: " + item.getItemInventory().getWeeklyInventory() +
                                        ", Monthly Inventory: " + item.getItemInventory().getMonthlyInventory() +
                                        ", Yearly Inventory: " + item.getItemInventory().getYearlyInventory());
                            }

                    
                        case 2:
                            System.out.println("Enter Restock Type: \n1.Weekly\n2.Monthly\n3.Yearly");
                            String choice3 = getScanner().next();
                            switch (choice3) {
                                case "Weekly":
                                    for (MenuItem item : getMenu().getItems()) {
                                        item.getItemInventory().Restuck("Weekly");
                                    }
                                    System.out.println("Weekly inventory restocked successfully.");
                                    break;
                                case "Monthly":
                                    for (MenuItem item : getMenu().getItems()) {
                                        item.getItemInventory().Restuck("Monthly");
                                    }
                                    System.out.println("Monthly inventory restocked successfully.");
                                    break;
                                case "Yearly":
                                    for (MenuItem item : getMenu().getItems()) {
                                        item.getItemInventory().Restuck("Yearly");
                                    }
                                    System.out.println("Yearly inventory restocked successfully.");
                                    break;

                            default:
                                break;
                    }
                }

                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }


    private void newOrder() {
        System.out.println("Creating a new order. Order ID: " + orderCounter);
        Order newOrder = new Order(orderCounter);
        boolean addingItems = true;
        while (addingItems) {

            getMenu().displayItems();

            
            System.out.println("Enter item name and quantity to add to the order, or Exit to finish adding items:");
            System.out.print("Input :");
            getScanner().nextLine();
            String itemChoice = getScanner().nextLine();

            if (itemChoice.equalsIgnoreCase("Exit")) {
                addingItems = false;
                continue;
            }

            MenuItem selectedItem = null;
            for (MenuItem item : getMenu().getItems()) {
                if (item.getName().equalsIgnoreCase(itemChoice)) {
                    selectedItem = item;
                    break;
                }
            }
            if (selectedItem == null) {
                System.out.println("Item not found. Please try again.");
                continue;
            }
            System.out.print("quantity :");
            int quantity = getScanner().nextInt();
                
            if (quantity <= 0) {
                System.out.println("Invalid quantity. Please enter a positive number.");
                continue;
            }

            if (quantity > selectedItem.getItemInventory().getWeeklyInventory()) {
                System.out.println("Insufficient inventory for " + selectedItem.getName() + ". Please try again.");
                continue;
            }

            selectedItem.setQuantity(quantity);
            selectedItem.getItemInventory().withdraw(quantity);
            System.out.println("You selected: " + selectedItem.getName() + " x" + selectedItem.getQuantity() +  " - $" + selectedItem.getPrice());
            MenuItem orderItem = new MenuItem(selectedItem.getName(), selectedItem.getPrice(), selectedItem.getCategory(), selectedItem.getQuantity());
            newOrder.addItem(orderItem);

            System.out.println("Do you want to add more items? (yes/no)");
            System.out.print("Input :");
            String addMore = getScanner().next().trim().toLowerCase();
            if (!addMore.equals("yes")) {
                addingItems = false;
            }
        }

        if (newOrder.getItems().isEmpty()) {
            System.out.println("No items added to the order. Order creation cancelled.");
            return;
        }

        orders.add(newOrder);
        orderCounter++;
    }

    private void modifyOrder() {
        if (orders.isEmpty()) {
            System.out.println("No orders available to modify.");
            return;
        }

        //System.out.println("Modifying an existing order. Order ID: " + orders.get(orders.size() - 1).getOrderId());
        System.out.println("Please select an order to modify by entering the corresponding number (1-" + orders.size() + "): ");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println((i + 1) + ". Order ID: " + orders.get(i).getOrderId());
        }

        System.out.print("Input :");
        int orderChoice = getScanner().nextInt();
        
        if (orderChoice < 1 || orderChoice > orders.size()) {
            System.out.println("Invalid order choice. Please try again.");
            return;
        }
        Order selectedOrder = orders.get(orderChoice - 1);

        System.out.println("Modifying the latest order. Order ID: " + orders.get(orders.size() - 1).getOrderId());

        System.out.println("Do you want to add items or remove items in the order? (add/remove)");
        System.out.print("Input :");
        String modifyChoice = getScanner().next().trim().toLowerCase();
        if (modifyChoice.equals("add")) {
            addItemsToOrder(selectedOrder);
        } else if (modifyChoice.equals("remove")) {
            removeItemsFromOrder(selectedOrder);
        } else {
            System.out.println("Invalid choice. Please try again.");
            return;
        }
    }
        

    private void cancelOrder() {
        if (orders.isEmpty()) {
            System.out.println("No orders available to cancel.");
            return;
        }
        System.out.println("Cancelling an existing order. Order ID: " + orders.get(orders.size() - 1).getOrderId());
        System.out.println("Please select an order to cancel by entering the corresponding number (1-" + orders.size() + "): ");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println((i + 1) + ". Order ID: " + orders.get(i).getOrderId());
        }

        System.out.print("Input :");
        int orderChoice = getScanner().nextInt();

        if (orderChoice < 1 || orderChoice > orders.size()) {
            System.out.println("Invalid order choice. Please try again.");
            return;
        }
        Order selectedOrder = orders.get(orderChoice - 1); 
        orders.remove(selectedOrder); 

        System.out.println("Order cancelled successfully!");
    }

    private void viewOrder() {
        if (orders.isEmpty()) {
            System.out.println("No orders available to view.");
            return;
        }
        System.out.println("Viewing an existing order. Order ID: " + orders.get(orders.size() - 1).getOrderId());
        System.out.println("Please select an order to view by entering the corresponding number (1-" + orders.size() + "): ");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println((i + 1) + ". Order ID: " + orders.get(i).getOrderId());
        }

        System.out.print("Input :");
        int orderChoice = getScanner().nextInt();
        if (orderChoice < 1 || orderChoice > orders.size()) {
            System.out.println("Invalid order choice. Please try again.");
            return;
        }
        Order selectedOrder = orders.get(orderChoice - 1);

        System.out.println("Order ID: " + selectedOrder.getOrderId());
        for (MenuItem item : selectedOrder.getItems()) {
            System.out.println("Item: " + item.getName() + " x" + item.getQuantity() + ", Price: $" + item.getPrice());
        }
    }

    private void generateInvoice() {
        if (orders.isEmpty()) {
            System.out.println("No orders available to generate an invoice.");
            return;
        } 

        System.out.println("generate an invoice for an existing order. Order ID: " + orders.get(orders.size() - 1).getOrderId());
        System.out.println("Please select an order to generate an invoice for by entering the corresponding number (1-" + orders.size() + "): ");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println((i + 1) + ". Order ID: " + orders.get(i).getOrderId());
        }

        System.out.print("Input :");
        int orderChoice = getScanner().nextInt();
        if (orderChoice < 1 || orderChoice > orders.size()) {
            System.out.println("Invalid order choice. Please try again.");
            return;
        }
        Order selectedOrder = orders.get(orderChoice - 1);


       // Order selectedOrder = orders.get(orders.size() - 1); // Get the latest order
        if (selectedOrder == null) {
            System.out.println("Order ID not found. Please try again.");
            return;
        }

        System.out.println("- - - - - - - - - - - - - - - - - -");
        double totalCost = 0.0;
        System.out.println("Invoice for Order ID: " + selectedOrder.getOrderId());
        for (MenuItem item : selectedOrder.getItems()) {
            System.out.println("Item: " + item.getName() + " X" + item.getQuantity() + ", Price: $" + item.getPrice() + " Total: $" + item.getTotal());
            totalCost += item.getTotal();
        }
        System.out.println("Total cost of the order: $" + totalCost);
        System.out.println("- - - - - - - - - - - - - - - - - -");
    }

    private void addItemsToOrder(Order selectedOrder){
        
        boolean addingItems = true;
        while (addingItems) {
            getMenu().displayItems();

            System.out.println("Enter the name of the item you want to add to the order, or type 'Exit' to finish adding items:");
            System.out.print("Input :");
            String itemChoice = getScanner().nextLine();
            MenuItem selectedItem = new MenuItem();

            if (itemChoice.equalsIgnoreCase("Exit")) {
                break; 
            }
            for (MenuItem item : getMenu().getItems()) {
                if (item.getName().equalsIgnoreCase(itemChoice)) {
                    selectedItem = item;
                    break;
                }
                else {
                    System.out.println("Item not found. Please try again.");
                    continue;
                }
            }

            System.out.println("Enter the quantity for " + itemChoice + ":");
            System.out.print("Input :");
            int quantity = getScanner().nextInt();

            if (quantity <= 0) {
                System.out.println("Invalid quantity. Please enter a positive number.");
                continue;
            }
            if (quantity > selectedItem.getItemInventory().getWeeklyInventory()) {
                System.out.println("Insufficient inventory for " + selectedItem.getName() + ". Please try again.");
                continue;
            }
            selectedItem.setQuantity(quantity);

            System.out.println("You selected: " + selectedItem.getName() + " x" + selectedItem.getQuantity() +" - $" + selectedItem.getPrice());
            selectedOrder.addItem(selectedItem);

            System.out.println("Do you want to add more items? (yes/no)");
            System.out.print("Input :");
            String addMore = getScanner().next().trim().toLowerCase();

            if (!addMore.equals("yes")) {
                addingItems = false;
            }
        }
    }

    private void removeItemsFromOrder(Order selectedOrder){
        boolean removingItems = true;
        while (removingItems) {
            
            System.out.println("Order ID: " + selectedOrder.getOrderId());
            ArrayList<MenuItem> items = selectedOrder.getItems();
            if (items.isEmpty()) {
                System.out.println("No items to remove in this order.");
                break;
            }
            for (int i = 0; i < items.size(); i++) {
                MenuItem item = items.get(i);
                System.out.println((i + 1) + ". Item: " + item.getName() + ", x" + item.getQuantity()  + ", Price: $" + item.getPrice());
            }

            System.out.println("Enter the name of the item you want to remove:");
            System.out.print("Input :");
            String itemChoice = getScanner().nextLine();

            
            if (itemChoice.equalsIgnoreCase("Exit")) {
                break;
            }

            
            MenuItem selectedItem = new MenuItem();
            for (MenuItem item : getMenu().getItems()) {
                if (item.getName().equalsIgnoreCase(itemChoice)) {
                    selectedItem = item;
                    break;
                }
                else {
                    System.out.println("Item not found. Please try again.");
                    continue;
                }
            }
            System.out.println("You selected to remove: " + selectedItem.getName() + " x" + selectedItem.getQuantity() + " - $" + selectedItem.getPrice());
            selectedItem.getItemInventory().deposit(selectedItem.getQuantity()); 
            selectedOrder.removeItem(selectedItem);

            System.out.println("Do you want to remove more items? (yes/no)");
            System.out.print("Input :");
            String removeMore = getScanner().next().trim().toLowerCase();
            if (!removeMore.equals("yes")) {
                removingItems = false;
            }
        }
    }

}