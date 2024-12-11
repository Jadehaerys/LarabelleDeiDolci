package LogIn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class authentication {
    protected String filePath;

    public authentication(String filePath) {
        this.filePath = filePath;
    }

    public abstract boolean authenticate(String username, String password);

    protected boolean checkCredentials(String username, String password) {
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

class FileAuthentication extends authentication {
    public FileAuthentication(String filePath) {
        super(filePath);
    }

    @Override
    public boolean authenticate(String username, String password) {
        return checkCredentials(username, password);
    }
}