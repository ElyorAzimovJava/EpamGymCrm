package com.gym.crm.model;

import lombok.Data;

import lombok.EqualsAndHashCode;

import java.util.UUID;
@Data
@EqualsAndHashCode(of = {"username"})
public  abstract class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private char[] password;
    private Boolean active = true;
}