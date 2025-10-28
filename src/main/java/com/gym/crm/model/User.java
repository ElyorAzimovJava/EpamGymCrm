package com.gym.crm.model;

import lombok.Data;

import java.util.UUID;
@Data
public  abstract class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Boolean active = true;
}
