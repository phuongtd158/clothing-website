package utils;

public class ValidateUtil {
    public static boolean checkTrong(String... args) {
        for (String arg : args) {
            if (arg.trim().length() == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkEmail(String email) {
        String regex = "(\\S.*\\S)(@)(\\S.*\\S)(.\\S[a-z]{2,3})";
        return !email.trim().matches(regex);
    }
}
