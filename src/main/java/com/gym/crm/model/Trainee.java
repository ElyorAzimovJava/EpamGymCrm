package com.gym.crm.model;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Trainee extends User{
    private LocalDate dateOfBirth;
    private String address;
}
