package com.gym.crm.service.impl;

import com.gym.crm.dao.TrainingDao;
import com.gym.crm.entity.Trainee;
import com.gym.crm.entity.Trainer;
import com.gym.crm.entity.Training;
import com.gym.crm.entity.TrainingType;
import com.gym.crm.model.TrainingDto;
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
    public Training createTraining(Training  trainingDto) {
        log.info("Creating training for trainee {} and trainer {}", trainingDto.getTrainee().getId(), trainingDto.getTrainer().getId());
        Trainee trainee = traineeService.findById(trainingDto.getTrainee().getId());
        Trainer trainer = trainerService.findById(trainingDto.getTrainer().getId());
        TrainingType trainingType = trainer.getSpecialization();

        Training training = new Training();
        training.setTrainee(trainee);
        training.setTrainer(trainer);
        training.setTrainingName(trainingDto.getTrainingName());
        training.setTrainingType(trainingType);
        training.setTrainingDate(trainingDto.getTrainingDate());
        training.setDurationMinutes(trainingDto.getDurationMinutes());

        return trainingDao.create(training);
    }

    @Override
    public Training findById(UUID id) {
        log.debug("Finding training with ID: {}", id);
        return trainingDao.findById(id);
    }

    @Override
    public List<Training> listAll() {
        log.debug("Listing all trainings");
        return trainingDao.findAll();
    }

    @Override
    public List<Training> getTraineeTrainings(String username, LocalDate fromDate, LocalDate toDate, String trainerName, String trainingType) {
        log.debug("Getting trainee trainings for username: {}", username);
        return trainingDao.findTraineeTrainings(username, fromDate, toDate, trainerName, trainingType);
    }

    @Override
    public List<Training> getTrainerTrainings(String username, LocalDate fromDate, LocalDate toDate, String traineeName) {
        log.debug("Getting trainer trainings for username: {}", username);
        return trainingDao.findTrainerTrainings(username, fromDate, toDate, traineeName);
    }
}