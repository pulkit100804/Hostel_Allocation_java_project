import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Student {
    public static void studentMenu(String username) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n\uD83C\uDFE0 Student Panel \uD83C\uDFE0");
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

    private static void viewAssignedRoom(String username) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT room_number FROM allocations WHERE student_id = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("✅ Your assigned room is: Room " + rs.getInt("room_number"));
            } else {
                System.out.println("❌ No room assigned.");
            }

            rs.close();
        } catch (Exception e) {
            System.out.println("❌ Error fetching room info: " + e.getMessage());
        }
    }
}
