public class Main {
    public static void main(String[] args) {

        System.out.println("Launching the Fast-Food Restaurant System...");
        final String ANSI_RESET = "\u001B[00m";
        final String ANSI_GREEN = "\u001B[32m";

        System.out.println(ANSI_GREEN+" ____  ____  ____  ____  _  _  ____   __   __ _  ____      ____  _  _  ____  ____  ____  _  _   \r\n" + //
                          "(  _ \\(  __)/ ___)(_  _)/ )( \\(  _ \\ / _\\ (  ( \\(_  _)___ / ___)( \\/ )/ ___)(_  _)(  __)( \\/ ) \r\n" + //
                          " )   / ) _) \\___ \\  )(  ) \\/ ( )   //    \\/    /  )( (___)\\___ \\ )  / \\___ \\  )(   ) _) / \\/ \\\r\n" + //
                          "(__\\_)(____)(____/ (__) \\____/(__\\_)\\_/\\_/\\_)__) (__)     (____/(__/  (____/ (__) (____)\\_)(_/"+ ANSI_RESET);

        
        System.out.println("Welcome to the Fast-Food Restaurant System!");
        RestaurantSystem restaurantSystem = new RestaurantSystem();
        restaurantSystem.run();
    }
}