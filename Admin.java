
import java.util.Scanner;

public class Admin {
    public static void adminMenu() {
        Hostel hostel = new Hostel();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n🛠 Admin Panel 🛠");
            System.out.println("1. Allocate Room");
            System.out.println("2. Show Room Allocations");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
