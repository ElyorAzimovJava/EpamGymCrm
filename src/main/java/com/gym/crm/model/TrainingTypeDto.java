package com.gym.crm.model;

import lombok.Data;
import java.util.UUID;

@Data
public class TrainingTypeDto {
    private UUID id;
    private String trainingTypeName;
}
