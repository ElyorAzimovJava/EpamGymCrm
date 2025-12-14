package com.gym.crm.dao;

import com.gym.crm.entity.Training;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TrainingDao extends Dao<Training, UUID> {
    List<Training> findTraineeTrainings(String username, LocalDate periodFrom, LocalDate periodTo, String trainerName, String trainingType);

    List<Training> findTrainerTrainings(String username, LocalDate periodFrom, LocalDate periodTo, String traineeName);
}