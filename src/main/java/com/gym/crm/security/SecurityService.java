package com.gym.crm.security;

public interface SecurityService {

    void login(String username, char[] password);

    void logout();
}