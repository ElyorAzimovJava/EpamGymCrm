package com.gym.crm.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class TraineeDto extends UserDto {
    private LocalDate dateOfBirth;
    private String address;
    private List<UUID> trainerIds;
}
