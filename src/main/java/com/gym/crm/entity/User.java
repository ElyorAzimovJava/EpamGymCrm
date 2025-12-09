package com.gym.crm.entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

import jakarta.persistence.Convert;
import lombok.ToString;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Data
@EqualsAndHashCode(of = {"username"})
@NoArgsConstructor
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "First name is mandatory")
    private String firstName;
    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Username is mandatory")
    private String username;
    @Column(nullable = false)
    @Convert(converter = PasswordConverter.class)
    @ToString.Exclude
    @NotNull(message = "Password is mandatory")
    private char[] password;
    @Column(name = "is_active", nullable = false)
    private boolean active;

    public User(String firstName, String lastName, String username, char[] password, boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.active = active;
    }
}