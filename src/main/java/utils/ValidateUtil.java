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
        return !email.matches(regex);
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        String regex = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$";
        return !phoneNumber.matches(regex);
    }


    public static boolean checkIsNumberInt(String string) {
        int intValue;
        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }

    public static boolean checkIsNumberDouble(String string) {
        double doubleValue;
        try {
            doubleValue = Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Double.");
        }
        return false;
    }
}
