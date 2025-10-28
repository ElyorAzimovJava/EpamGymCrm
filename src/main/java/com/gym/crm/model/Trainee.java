package com.gym.crm.model;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Trainee extends User{
    private LocalDate dateOfBirth;
    private String address;
}
