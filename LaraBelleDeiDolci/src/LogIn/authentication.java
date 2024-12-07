package LogIn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class authentication {

    public static boolean Authenticate(String username, String password) {
        String filePath = "userInformation.txt";  // Path to your credentials file

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");

                if (credentials.length == 2) {
                    String fileUsername = credentials[0].trim();
                    String filePassword = credentials[1].trim();

                    // Check if the provided username and password match
                    if (username.equalsIgnoreCase(fileUsername) && password.equals(filePassword)) {
                        return true;  // Authentication successful
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  // Prints error if there is a file issue
        }

        return false;  // Authentication failed
    }
}