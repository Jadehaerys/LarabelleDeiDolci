package LogIn;

public class authentication {
    

    public static boolean Authenticate(String username, String password){

        if(username.equals("Larabelle") && password.equals("Lorebelle")){
            return true;
        }
        return false;
    }
}
