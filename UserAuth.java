import java.io.*;
import java.util.Scanner;

public class UserAuth {
    private static final String FILE_NAME = "users.txt";

    public static void checkFile() throws IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("'users.txt' created.");
        }
    }
    public static void registerUser() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (admin/student): ");
        String role = scanner.nextLine().toLowerCase();

        if (!role.equals("admin") && !role.equals("student")) {
            System.out.println(" Invalid role! Choose 'admin' or 'student'.");
            return;
        }

        if (userExists(username)) {
            System.out.println(" Username already exists!");
            return;
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true));
        bw.write(username + "," + password + "," + role);
        bw.newLine();
        bw.close();
        System.out.println(" Registration successful!");
    }
