package LogIn;

public class authentication {
    

    public static boolean Authenticate(String username, String password){

        if(username.equalsIgnoreCase("Larabelle") && password.equalsIgnoreCase("Lorebelle")){
            return true;
        }
        return false;
    }
}
