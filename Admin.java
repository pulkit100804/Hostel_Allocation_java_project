import java.util.Scanner;

public class Admin {
    public static void adminMenu() {
        Hostel hostel = new Hostel();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n\uD83D\uDEE0 Admin Panel \uD83D\uDEE0");
            System.out.println("1. Allocate Room");
            System.out.println("2. Show Room Allocations");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter student username: ");
                    String uname = scanner.nextLine();
                    int room = hostel.allocateRoom(uname);
                    if (room != -1) {
                        System.out.println("✅ Room " + room + " allocated to " + uname);
                    } else {
                        System.out.println("❌ No available rooms.");
                    }
                    break;
                case 2:
                    hostel.showRoomAllocation();
                    break;
                case 3:
                    running = false;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }
}
