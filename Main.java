import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserAuth.checkFile();  // Ensure users.txt exists
        boolean running = true;
