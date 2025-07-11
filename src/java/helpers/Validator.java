package helpers;

public class Validator {

    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean isValidUsername(String username) {
        return isNotEmpty(username) && username.length() >= 4 && username.matches("[a-zA-Z0-9_]+");
    }

    public static boolean isValidPassword(String password) {
        return isNotEmpty(password) && password.length() >= 6 && password.matches(".*[a-zA-Z].*") && password.matches(".*[0-9].*");
    }

//    public static boolean isValidEmail(String email) {
//        return isNotEmpty(email) && email.matches("^\\S+@\\S+\\.\\S+$");
//    }
}
