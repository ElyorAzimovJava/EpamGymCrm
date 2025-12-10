package com.gym.crm.service;

public interface UserService {
    String generateUsername(String firstName, String lastName);
    boolean checkPassword(String username, char[] password);
    void changePassword(String username, char[] oldPassword, char[] newPassword);
}