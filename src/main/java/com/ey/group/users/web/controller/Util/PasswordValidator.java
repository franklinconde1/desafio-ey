package com.ey.group.users.web.controller.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

    public static boolean validatePassword(String password) {
        // Mayúscula al menos 1
        if (!containsUppercaseLetter(password)) {
            return false;
        }

        // Minúscula al menos 1
        if (!containsLowercaseLetter(password)) {
            return false;
        }

        // Números al menos 2
        if (!containsAtLeastTwoDigits(password)) {
            return false;
        }

        return true;
    }

    private static boolean containsUppercaseLetter(String password) {
        return password.matches(".*[A-Z].*");
    }

    private static boolean containsLowercaseLetter(String password) {
        return password.matches(".*[a-z].*");
    }

    private static boolean containsAtLeastTwoDigits(String password) {
        // Expresión regular para contar los dígitos
        Pattern digitPattern = Pattern.compile("\\d");
        Matcher matcher = digitPattern.matcher(password);

        int digitCount = 0;
        while (matcher.find()) {
            digitCount++;
        }

        return digitCount >= 2;
    }

}
