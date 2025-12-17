package com.gym.crm.service.impl;

import com.gym.crm.dao.TrainingDao;
import com.gym.crm.entity.Trainee;
import com.gym.crm.entity.Trainer;
import com.gym.crm.entity.Training;
import com.gym.crm.entity.TrainingType;
import com.gym.crm.model.TrainingDto;
import com.gym.crm.security.AuthenticationContext;
import com.gym.crm.service.TraineeService;
import com.gym.crm.service.TrainerService;
import com.gym.crm.service.TrainingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService {

    private final TrainingDao trainingDao;
    private final TraineeService traineeService;
    private final TrainerService trainerService;

    @Override
    public Training createTraining(Training training) {
        if (AuthenticationContext.getCurrentUser() == null) {
            throw new IllegalStateException("User not authenticated");
        }
        log.debug("Creating training with details: {}", training);
        Trainee trainee = traineeService.findById(training.getTrainee().getId());
        Trainer trainer = trainerService.findById(training.getTrainer().getId());

        training.setTrainee(trainee);
        training.setTrainer(trainer);
        training.setTrainingType(trainer.getSpecialization());

        return trainingDao.create(training);
    }

    @Override
    public Training findById(UUID id) {
        if (AuthenticationContext.getCurrentUser() == null) {
            throw new IllegalStateException("User not authenticated");
        }
        log.debug("Finding training with ID: {}", id);
        return trainingDao.findById(id);
    }

    @Override
    public List<Training> listAll() {
        if (AuthenticationContext.getCurrentUser() == null) {
            throw new IllegalStateException("User not authenticated");
        }
        log.debug("Listing all trainings");
        return trainingDao.findAll();
    }

    @Override
    public List<Training> getTraineeTrainings(String username, LocalDate fromDate, LocalDate toDate, String trainerName, String trainingType) {
        if (AuthenticationContext.getCurrentUser() == null) {
            throw new IllegalStateException("User not authenticated");
        }
        log.debug("Getting trainee trainings for username: {}", username);
        return trainingDao.findTraineeTrainings(username, fromDate, toDate, trainerName, trainingType);
    }

    @Override
    public List<Training> getTrainerTrainings(String username, LocalDate fromDate, LocalDate toDate, String traineeName) {
        if (AuthenticationContext.getCurrentUser() == null) {
            throw new IllegalStateException("User not authenticated");
        }
        log.debug("Getting trainer trainings for username: {}", username);
        return trainingDao.findTrainerTrainings(username, fromDate, toDate, traineeName);
    }
}