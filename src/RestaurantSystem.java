import java.util.ArrayList;
import java.util.Scanner;

public class RestaurantSystem {
    private ArrayList<Order> orders;
    private static int orderCounter = 0;
    private static final Scanner sharedScanner = new Scanner(System.in);
    private Menu menu;
    public ArrayList<Inventory> inventories = new ArrayList<>();

    public RestaurantSystem() {
        this.orders = new ArrayList<>();
        this.menu = new Menu();
        for (MenuItem item : menu.getItems()) {
            inventories.add(new Inventory(item.getName(),item.getWeekly(),item.getMonthly(),item.getYearly()));
        }
    }

    private Scanner getScanner() {
        return sharedScanner;
    }

    private Menu getMenu(){
        return menu;
    }

    private Inventory getInventoryByName(String name) {
        for (Inventory inv : inventories) {
            if (inv.getName().equalsIgnoreCase(name)) {
                return inv;
            }
        }
        return null;
    }

    private void displayMainMenu() {
    System.out.println("\n========== Fast-Food Restaurant System ==========");
    System.out.println("1. New Order");
    System.out.println("2. Modify Order");
    System.out.println("3. Cancel Order");
    System.out.println("4. View Order");
    System.out.println("5. Generate Invoice");
    System.out.println("6. Inventory Management");
    System.out.println("7. Exit");
    System.out.println("===============================================");
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
            System.out.print("Input: ");
            int mainMenuChoice = getScanner().nextInt();

            switch (mainMenuChoice) {
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
                    System.out.print("Input: ");
                    int inventoryAction = getScanner().nextInt();
                    switch (inventoryAction) {
                        case 1:
                            for (Inventory inventory : inventories) {
                                System.out.println(inventory.getName() + " - Weekly Inventory: " + inventory.getWeeklyInventory() +
                                        ", Monthly Inventory: " + inventory.getMonthlyInventory() +
                                        ", Yearly Inventory: " + inventory.getYearlyInventory());
                            }
                            break;
                        case 2:
                            System.out.println("Do you want to Restock to (1.all Items/2.one Item)");
                            System.out.print("Input: ");
                            getScanner().nextLine();

                            int restockFor = getScanner().nextInt();

                            switch (restockFor) {
                                case 1:
                                    System.out.println("Restocking all items...");
                                    System.out.println("Select restock type: \n1. Weekly\n2. Monthly\n3. Yearly");
                                    System.out.print("Input: ");
                                    int restockTypeChoice = getScanner().nextInt();

                                    if (restockTypeChoice < 1 || restockTypeChoice > 3) {
                                        System.out.println("Invalid restock type. Please try again.");
                                        break;
                                    }
                                    for (Inventory inventory : inventories) {
                                        inventory.restock(restockTypeChoice);
                                        System.out.println(inventory.getName() + " restocked successfully!");
                                    }
                                    break;
                                
                                case 2:
                                    System.out.print("Enter the name of the item to restock:");
                                    getScanner().nextLine();
                                    String restockItemName = getScanner().nextLine();
                                    Inventory inventoryToRestock = getInventoryByName(restockItemName);

                                    System.out.println("Select restock type: \n1. Weekly\n2. Monthly\n3. Yearly");
                                    System.out.print("Input: ");
                                    int restockType = getScanner().nextInt();

                                    if (restockType < 1 || restockType > 3) {
                                        System.out.println("Invalid restock type. Please try again.");
                                        break;
                                    }

                                    if (inventoryToRestock != null) {
                                        inventoryToRestock.restock(restockType);
                                        System.out.println(restockItemName + " restocked successfully!");
                                    } else {
                                        System.out.println("Item not found.");
                                    }
                                    break;
                                
                                default:
                                    System.out.println("Invalid Input! Please try again.");
                                    break;
                            }
                            
                            break;

                        default:
                            System.out.println("Invalid Input! Please try again.");
                            break;
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
            getMenu().displayItems(inventories);

            System.out.println("enter the number of the item you want to add to the order (1-" + getMenu().getItems().size() + "), or 0 to finish adding items:");
            System.out.print("Input: ");
            getScanner().nextLine();
            int itemIndex = getScanner().nextInt() - 1;

            if (itemIndex == -1) {
                System.out.println("Finished adding items to the order.");
                break;
            }

            if (itemIndex < 0 || itemIndex >= getMenu().getItems().size()) {
                System.out.println("Invalid item number. Please try again.");
                continue;
            }

            MenuItem menuItem = getMenu().getItems().get(itemIndex);
            if (menuItem == null) {
                System.out.println("Item not found. Please try again.");
                continue;
            }
            System.out.print("Quantity: ");
            int quantityInput = getScanner().nextInt();

            if (quantityInput <= 0) {
                System.out.println("Invalid quantity. Please enter a positive number.");
                continue;
            }

            Inventory itemInventory = getInventoryByName(menuItem.getName());
            if (itemInventory == null) {
                System.out.println("Inventory not found for " + menuItem.getName());
                continue;
            }
            if (quantityInput > itemInventory.getWeeklyInventory()) {
                System.out.println("Insufficient inventory for " + menuItem.getName() + ". Please try again.");
                continue;
            }
            itemInventory.withdraw(quantityInput);
            if (itemInventory.getWeeklyInventory() < 5) {
                System.out.println("Warning: Low inventory for " + itemInventory.getName() + " (" + itemInventory.getWeeklyInventory() + " left)");
            }

            menuItem.setQuantity(quantityInput);
            System.out.println("You selected: " + menuItem.getName() + " x" + menuItem.getQuantity() +  " - $" + menuItem.getPrice());
            MenuItem orderItem = new MenuItem(menuItem.getName(), menuItem.getPrice(), menuItem.getCategory(), menuItem.getQuantity());
            newOrder.addItem(orderItem);

            System.out.println("Do you want to add more items? (yes/no)");
            System.out.print("Input: ");
            String addMoreInput = getScanner().next().trim().toLowerCase();
            if (!addMoreInput.equals("yes")) {
                addingItems = false;
            }
        }

        if (newOrder.getItems().isEmpty()) {
            System.out.println("No items added to the order. Order creation cancelled.");
            return;
        }
        System.out.println("Order summary:");
        for (MenuItem item : newOrder.getItems()) {
            System.out.println(item.getName() + " x" + item.getQuantity() + " - $" + item.getTotal());
        }

        orders.add(newOrder);
        orderCounter++;
    }

    private void modifyOrder() {
        if (orders.isEmpty()) {
            System.out.println("No orders available to modify.");
            return;
        }

        System.out.println("Please select an order to modify by entering the corresponding number (1-" + orders.size() + "): ");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println((i + 1) + ". Order ID: " + orders.get(i).getOrderId() + ", Order Time: " + orders.get(i).getOrderTime());
        }

        System.out.print("Input: ");
        int orderSelection = getScanner().nextInt();

        if (orderSelection < 1 || orderSelection > orders.size()) {
            System.out.println("Invalid order choice. Please try again.");
            return;
        }
        Order selectedOrder = orders.get(orderSelection - 1);

        System.out.println("Do you want to add items, remove items, or update item quantity in the order? (add/remove/update)");
        System.out.print("Input: ");
        String modifyChoice = getScanner().next().trim().toLowerCase();
        getScanner().nextLine(); // Consume newline

        if (modifyChoice.equals("add")) {
            addItemsToOrder(selectedOrder);
        } else if (modifyChoice.equals("remove")) {
            removeItemsFromOrder(selectedOrder);
        } else if (modifyChoice.equals("update")) {
            updateItemQuantityInOrder(selectedOrder);
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }


    private void cancelOrder() {
        if (orders.isEmpty()) {
            System.out.println("No orders available to cancel.");
            return;
        }
        System.out.println("Cancelling an existing order. Order ID: " + orders.get(orders.size() - 1).getOrderId() + "order Time: " + orders.get(orders.size() - 1).getOrderTime());
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

        for (int i = 0; i < orders.get(orderChoice - 1).getItems().size(); i++) {
            MenuItem item = orders.get(orderChoice - 1).getItems().get(i);
            Inventory inv = getInventoryByName(item.getName());
            if (inv != null) {
                inv.deposit(item.getQuantity());
            } else {
                System.out.println("Inventory not found for " + item.getName());
            }
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
        //System.out.println("Viewing an existing order. Order ID: " + orders.get(orders.size() - 1).getOrderId() + ", Order Time: " + orders.get(orders.size() - 1).getOrderTime());
        System.out.println("Please select an order to view by entering the corresponding number (1-" + orders.size() + "): ");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println((i + 1) + ". Order ID: " + orders.get(i).getOrderId() + ", Order Time: " + orders.get(i).getOrderTime());
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

        System.out.println("generate an invoice for an existing order. Order ID: " + orders.get(orders.size() - 1).getOrderId() + ", Order Time: " + orders.get(orders.size() - 1).getOrderTime());
        System.out.println("Please select an order to generate an invoice for by entering the corresponding number (1-" + orders.size() + "): ");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println((i + 1) + ". Order ID: " + orders.get(i).getOrderId() + ", Order Time: " + orders.get(i).getOrderTime());
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
            getMenu().displayItems(inventories);

            System.out.println("enter the number of the item you want to add to the order (1-" + getMenu().getItems().size() + "), or 0 to finish adding items:");
            System.out.print("Input: ");
            int itemChoice = getScanner().nextInt();
            getScanner().nextLine(); 

            if (itemChoice < 0 || itemChoice > getMenu().getItems().size() + 1) {
                System.out.println("Invalid item number. Please try again.");
                continue;
            }
            if (itemChoice == 0) {
                System.out.println("Finished adding items to the order.");
                break;
            }

            MenuItem selectedItem = getMenu().getItems().get(itemChoice - 1);
            System.out.println("Enter the quantity for " + selectedItem.getName() + ":");
            System.out.print("Input :");
            int quantity = getScanner().nextInt();

            if (quantity <= 0) {
                System.out.println("Invalid quantity. Please enter a positive number.");
                continue;
            }
            Inventory inv = getInventoryByName(selectedItem.getName());
            if (inv == null) {
                System.out.println("Inventory not found for " + selectedItem.getName());
                continue;
            }
            if (quantity > inv.getWeeklyInventory()) {
                System.out.println("Insufficient inventory for " + selectedItem.getName() + ". Please try again.");
                continue;
            }
            inv.withdraw(quantity);
            if (inv.getWeeklyInventory() < 5) {
                System.out.println("Warning: Low inventory for " + inv.getName() + " (" + inv.getWeeklyInventory() + " left)");
            }

            selectedItem.setQuantity(quantity);

            MenuItem orderItem = new MenuItem(selectedItem.getName(), selectedItem.getPrice(), selectedItem.getCategory(), quantity);
            System.out.println("You selected: " + orderItem.getName() + " x" + orderItem.getQuantity() +" - $" + orderItem.getPrice());
            selectedOrder.addItem(orderItem);

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
            Inventory inv = getInventoryByName(selectedItem.getName());
            if (inv == null) {
                System.out.println("Inventory not found for " + selectedItem.getName());
                continue;
            }
            inv.deposit(selectedItem.getQuantity());
            selectedOrder.removeItem(selectedItem);

            System.out.println("Do you want to remove more items? (yes/no)");
            System.out.print("Input :");
            String removeMore = getScanner().next().trim().toLowerCase();
            if (!removeMore.equals("yes")) {
                removingItems = false;
            }
        }
    }

    private void updateItemQuantityInOrder(Order selectedOrder) {
        ArrayList<MenuItem> items = selectedOrder.getItems();
        if (items.isEmpty()) {
            System.out.println("No items to update in this order.");
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " x" + item.getQuantity());
        }
        System.out.println("Enter the name of the item to update:");
        String itemName = getScanner().nextLine();
        MenuItem orderItem = null;
        for (MenuItem item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                orderItem = item;
                break;
            }
        }
        if (orderItem == null) {
            System.out.println("Item not found in order.");
            return;
        }
        System.out.println("Current quantity: " + orderItem.getQuantity());
        System.out.print("Enter new quantity: ");
        int newQty = getScanner().nextInt();
        int diff = newQty - orderItem.getQuantity();
        Inventory inv = getInventoryByName(orderItem.getName());
        if (diff > 0) {
            if (inv.getWeeklyInventory() < diff) {
                System.out.println("Not enough inventory.");
                return;
            }
            inv.withdraw(diff);
        } else if (diff < 0) {
            inv.deposit(-diff);
        }
        orderItem.setQuantity(newQty);
        System.out.println("Quantity updated.");
    }
}