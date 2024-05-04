package com.example.recycleme.util;

public class LogToastUtil {
    private static String emailPattern = "[a-zA-z0-9._]+@[a-z]+\\.+[a-z]+";
    public static boolean isEmpty(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            return true;
        }
        return false;
    }
    public static boolean isLessThanSixCharacter(String password) {
        if (password.length() < 6) {
            return true;
        }
        return false;
    }

    public static boolean invalidEmail(String email) {
        if (!email.matches(emailPattern)) {
            return true;
        }
        return false;
    }

    public static boolean nonMatchPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            return true;
        }
        return false;
    }


}
