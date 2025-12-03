package com.gym.crm.model;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TrainingDto {
    private UUID id;
    private UUID traineeId;
    private UUID trainerId;
    private String trainingName;
    private TrainingTypeDto trainingType;
    private LocalDateTime trainingDate;
    private Long durationMinutes;
}
