import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Student {
    public static void studentMenu(String username) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n🏠 Student Panel 🏠");
            System.out.println("1. View Assigned Room");
            System.out.println("2. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAssignedRoom(username);
                    break;
                case 2:
                    running = false;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }
