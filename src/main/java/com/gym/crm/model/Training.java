package com.gym.crm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Training {
    private UUID id;
    private UUID trainingId;
    private UUID trainerId;
    private String trainingName;
    private String trainingType;
    private LocalDateTime trainingDate;
    private Long durationMinutes;
}
