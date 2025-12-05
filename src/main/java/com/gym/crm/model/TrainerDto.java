package com.gym.crm.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class TrainerDto extends UserDto {
    private TrainingTypeDto specialization;
    private List<UUID> traineeIds;
}
