package com.gym.crm.security;

import com.gym.crm.entity.User;
import com.gym.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final UserService userService;

    @Autowired
    public SecurityServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void login(String username, char[] password) {
        if (userService.checkPassword(username, password)) {
            User user = userService.findByUsername(username); // Assuming findByUsername is available
            AuthenticationContext.setCurrentUser(user);
        }
    }

    @Override
    public void logout() {
        AuthenticationContext.clear();
    }
}