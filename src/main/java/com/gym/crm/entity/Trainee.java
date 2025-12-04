package com.gym.crm.entity;

import lombok.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true, exclude = "trainers")
@ToString(callSuper = true, exclude = "trainers")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trainee")
public class Trainee extends User {
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    @Column(nullable = false)
    private String address;
    @ManyToMany(mappedBy = "trainees")
    private List<Trainer> trainers;

    public Trainee(String firstName, String lastName, String username, char[] password, boolean active, LocalDate dateOfBirth, String address) {
        super(firstName, lastName, username, password, active);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }
}