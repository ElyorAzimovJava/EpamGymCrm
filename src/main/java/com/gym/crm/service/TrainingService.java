package com.gym.crm.service;

import com.gym.crm.entity.Training;
import com.gym.crm.model.TrainingDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TrainingService {
    Training createTraining(Training training);

    Training findById(UUID id);

    List<Training> listAll();

    List<Training> getTraineeTrainings(String username, LocalDate fromDate, LocalDate toDate, String trainerName, String trainingType);

    List<Training> getTrainerTrainings(String username, LocalDate fromDate, LocalDate toDate, String traineeName);
}