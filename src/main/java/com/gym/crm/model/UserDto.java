package com.gym.crm.model;

import lombok.Data;
import java.util.UUID;

@Data
public abstract class UserDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private boolean active;
}
