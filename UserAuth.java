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

    public static void loginUser() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        String role = validateUser(username, password);
        if (role == null) {
            System.out.println("Invalid credentials!");
            return;
        }

        System.out.println("Login successful! Welcome, " + username);

        if (role.equals("admin")) {
            Admin.adminMenu();
        } else {
            Student.studentMenu(username);
        }
    }

    private static boolean userExists(String username) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts[0].equals(username)) {
                br.close();
                return true;
            }
        }
        br.close();
        return false;
    }

