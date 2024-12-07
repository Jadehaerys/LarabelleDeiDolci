package LogIn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class authentication {

    public static boolean Authenticate(String username, String password) {
        String filePath = "userInformation.txt"; 

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");

                if (credentials.length == 2) {
                    String fileUsername = credentials[0].trim();
                    String filePassword = credentials[1].trim();

                    
                    if (username.equalsIgnoreCase(fileUsername) && password.equalsIgnoreCase(filePassword)) {
                        return true;  
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  
        }

        return false;  
    }
}