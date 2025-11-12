package com.gym.crm.service;

import java.security.SecureRandom;
import java.util.Random;

public class UsernameAndPasswordUtil {
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final  String ALL = LETTERS + DIGITS;
    private static final Random RANDOM = new SecureRandom();

    public static String generatePassword(int length) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            password.append(ALL.charAt(RANDOM.nextInt(ALL.length())));
        }
        return password.toString();
    }
    public static String buildUsername(String firstName, String lastName) {
        return String.format("%s %s", firstName.trim(), lastName.trim());
    }
}
