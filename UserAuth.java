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
