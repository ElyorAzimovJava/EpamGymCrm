package com.gym.crm.service;

import com.gym.crm.entity.User;

public interface UserService {
    String generateUsername(String firstName, String lastName);
    boolean checkPassword(String username, char[] password);
    void changePassword(String username, char[] oldPassword, char[] newPassword);
    User findByUsername(String username);
}