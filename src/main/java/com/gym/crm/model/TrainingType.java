package com.gym.crm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingType {
    private String trainingTypeName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrainingType that)) return false;
        return trainingTypeName != null && trainingTypeName.equalsIgnoreCase(that.trainingTypeName);
    }

    @Override
    public int hashCode() {
        return trainingTypeName != null ? trainingTypeName.toLowerCase().hashCode() : 0;
    }
}

