//No exception handling required at this stage 
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserAuth.checkFile();  // Ensure users.txt exists
        boolean running = true;

        while (running) {
            System.out.println("\n🏨 Hostel Management System 🏨");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    UserAuth.registerUser();
                    break;
                case 2:
                    UserAuth.loginUser();
                    break;
                case 3:
                    System.out.println("Exiting... 🏁");
                    running = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
}

