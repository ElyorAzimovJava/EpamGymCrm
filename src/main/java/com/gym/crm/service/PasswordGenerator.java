package com.gym.crm.service;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordGenerator {
    private static final String CHAR_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPERCASE = CHAR_LOWERCASE.toUpperCase();
    private static final String DIGIT = "0123456789";
    private static final String OTHER_SPECIAL = "!@#$%^&*()_+-=[]{}|;':,.<>/?";
    private static final String PASSWORD_ALLOW = CHAR_LOWERCASE + CHAR_UPPERCASE + DIGIT + OTHER_SPECIAL;
    private static final Random RANDOM = new SecureRandom();

    public static char[] generatePassword(int length) {
        if (length < 4) {
            throw new IllegalArgumentException("Password length must be at least 4 characters");
        }

        char[] password = new char[length];
        password[0] = CHAR_LOWERCASE.charAt(RANDOM.nextInt(CHAR_LOWERCASE.length()));
        password[1] = CHAR_UPPERCASE.charAt(RANDOM.nextInt(CHAR_UPPERCASE.length()));
        password[2] = DIGIT.charAt(RANDOM.nextInt(DIGIT.length()));
        password[3] = OTHER_SPECIAL.charAt(RANDOM.nextInt(OTHER_SPECIAL.length()));

        for (int i = 4; i < length; i++) {
            password[i] = PASSWORD_ALLOW.charAt(RANDOM.nextInt(PASSWORD_ALLOW.length()));
        }

        return password;
    }
}