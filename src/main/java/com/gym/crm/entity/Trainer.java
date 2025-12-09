package com.gym.crm.entity;

import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.List;

import jakarta.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true, exclude = "trainees")
@ToString(callSuper = true, exclude = "trainees")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trainer")
public class Trainer extends User {
    @ManyToOne
    @JoinColumn(name = "specialization_id")
    @NotNull(message = "Specialization is mandatory")
    private TrainingType specialization;
    @ManyToMany
    @JoinTable(
            name = "trainer_trainee",
            joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "trainee_id")
    )
    private List<Trainee> trainees;

    public Trainer(String firstName, String lastName, String username, char[] password, boolean active, TrainingType specialization) {
        super(firstName, lastName, username, password, active);
        this.specialization = specialization;
    }
}