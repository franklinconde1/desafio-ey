package com.ey.group.users.web.controller.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarEmail {

    public static boolean validarEmail(String email) {
        String patronEmail = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";

        Pattern pattern = Pattern.compile(patronEmail);

        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

}
