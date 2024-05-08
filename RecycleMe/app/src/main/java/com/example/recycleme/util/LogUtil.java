package com.example.recycleme.util;

import android.content.Context;
import android.widget.Toast;

public class LogUtil {
    private static String emailPattern = "[a-zA-Z0-9._]+@[a-zA-Z]+(\\.[a-zA-Z]+)+";

    public static boolean validateSignup(Context context, String email, String password, String confirmPassword) {
        if (email.isEmpty() || password.isEmpty()) {
            getToastForEmptyInput(context);
            return false;
        }
        else if (!email.matches(emailPattern)) {
            getToastForInvalidEmail(context);
            return false;
        }
        else if (password.length() < 6) {
            getToastForInvalidPassword(context);
            return false;
        }
        else if (!password.equals(confirmPassword)) {
            getToastForNonMatchPassword(context);
            return false;
        }
        else return true;
    }


    public static boolean validateLogin(Context context, String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            getToastForEmptyInput(context);
            return false;
        }
        else if (!email.matches(emailPattern)) {
            getToastForInvalidEmail(context);
            return false;
        }
        else if (password.length() < 6) {
            getToastForInvalidPassword(context);
            return false;
        }
        else return true;
    }

    public static String getUsernameFromEmail(String email) {
        if (email != null) {
            return email.split("@")[0];
        }
        return "";
    }


    private static void getToastForEmptyInput(Context context) {
        Toast.makeText(context, "Email or password cannot be empty", Toast.LENGTH_SHORT).show();
    }

    private static void getToastForInvalidEmail(Context context) {
        Toast.makeText(context, "Invalid email address", Toast.LENGTH_SHORT).show();
    }

    private static void getToastForInvalidPassword(Context context) {
        Toast.makeText(context, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
    }

    private static void getToastForNonMatchPassword(Context context) {
        Toast.makeText(context, "Password does not match", Toast.LENGTH_SHORT).show();
    }




}
