public class Main {
    public static void main(String[] args) {

        System.out.println("Launching the Fast-Food Restaurant System...");
        System.out.println(" ____  ____  ____  ____  _  _  ____   __   __ _  ____      ____  _  _  ____  ____  ____  _  _ \r\n" + //
                        "(  _ \\(  __)/ ___)(_  _)/ )( \\(  _ \\ / _\\ (  ( \\(_  _)___ / ___)( \\/ )/ ___)(_  _)(  __)( \\/ )\r\n" + //
                        " )   / ) _) \\___ \\  )(  ) \\/ ( )   //    \\/    /  )( (___)\\___ \\ )  / \\___ \\  )(   ) _) / \\/ \\\r\n" + //
                        "(__\\_)(____)(____/ (__) \\____/(__\\_)\\_/\\_/\\_)__) (__)     (____/(__/  (____/ (__) (____)\\_)(_/");
        System.out.println("Welcome to the Fast-Food Restaurant System!");
        RestaurantSystem restaurantSystem = new RestaurantSystem();
        restaurantSystem.run();
    }
}