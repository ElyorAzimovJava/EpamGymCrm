package com.gym.crm.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@EqualsAndHashCode(of = {"username"})
@AllArgsConstructor
@NoArgsConstructor
public  abstract class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private char[] password;
    private Boolean active = true;
}