package com.gabrielpolak.ticket.validators;

import java.util.regex.Pattern;

public class PatternValidator {

    public static boolean validateName(String name) {
        String pattern = "[A-Za-z]{3,}";
        return patternMatches(name, pattern);
    }

    public static boolean validateSurname(String surname) {
        String pattern = "([A-Za-z]{3,})|([A-Za-z]{3,}-[A-Za-z]{3,})";
        return patternMatches(surname, pattern);
    }

    public static boolean validateEmail(String email) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        return patternMatches(email, regexPattern);
    }

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}
