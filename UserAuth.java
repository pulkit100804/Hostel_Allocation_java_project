import java.io.*;
import java.util.Scanner;

// Custom Exception for User Already Exists
class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}

// Custom Exception for Invalid Role
class InvalidRoleException extends Exception {
    public InvalidRoleException(String message) {
        super(message);
    }
}

public class UserAuth {
    private static final String FILE_NAME = "users.txt";

    public static void checkFile() {
        File file = new File(FILE_NAME);
        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("✅ 'users.txt' created.");
            }
        } catch (IOException e) {
            System.out.println("❌ Error creating file: " + e.getMessage());
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

        try {
            if (!role.equals("admin") && !role.equals("student")) {
                throw new InvalidRoleException("Invalid role! Choose 'admin' or 'student'.");
            }

            if (userExists(username)) {
                throw new UserAlreadyExistsException("❌ Username already exists!");
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
                bw.write(username + "," + password + "," + role);
                bw.newLine();
                System.out.println("✅ Registration successful!");
            }

        } catch (InvalidRoleException | UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
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

    private static boolean userExists(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading file.");
        }
        return false;
    }

    private static String validateUser(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username) && parts[1].equals(password)) {
                    return parts[2]; // Return role
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading file.");
        }
        return null;
    }
}
