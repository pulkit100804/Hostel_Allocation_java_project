import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/hostel_management";
    private static final String USER = "root";
    private static final String PASSWORD = "Test123$";

    static {
        try {
            // This loads the MySQL JDBC driver class
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL JDBC Driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL JDBC Driver not found—check your classpath!");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("❌ DB error during connection: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
