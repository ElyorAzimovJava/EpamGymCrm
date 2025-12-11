package com.gym.crm.service.impl;

import com.gym.crm.dao.Dao;
import com.gym.crm.entity.Training;
import com.gym.crm.service.TrainingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TrainingServiceImpl implements TrainingService {

    private Dao<Training, UUID> trainingDao;

    @Autowired
    public void setTrainingDao(Dao<Training, UUID> trainingDao) { this.trainingDao = trainingDao; }

    public Training createTraining(Training training) {
        log.debug("Creating training with details: {}", training);
        Training saved = trainingDao.create(training);
        log.info("Training created: {}", saved);
        return saved;
    }


    public Training findById(UUID id) {
        log.debug("Finding training with ID: {}", id);
        return trainingDao.findById(id);
    }
    public List<Training> listAll() {
        log.debug("Listing all trainings");
        return trainingDao.findAll();
    }

    @Override
    public List<Training> getTraineeTrainings(String username, LocalDate fromDate, LocalDate toDate, String trainerName, String trainingType) {
        log.debug("Getting trainee trainings for username: {}", username);
        return trainingDao.findAll().stream()
                .filter(t -> t.getTrainee().getUsername().equals(username))
                .filter(t -> fromDate == null || t.getTrainingDate().isAfter(fromDate.atStartOfDay()))
                .filter(t -> toDate == null || t.getTrainingDate().isBefore(toDate.atStartOfDay()))
                .filter(t -> trainerName == null || t.getTrainer().getUsername().equals(trainerName))
                .filter(t -> trainingType == null || t.getTrainingType().getTrainingTypeName().equals(trainingType))
                .collect(Collectors.toList());
    }

    @Override
    public List<Training> getTrainerTrainings(String username, LocalDate fromDate, LocalDate toDate, String traineeName) {
        log.debug("Getting trainer trainings for username: {}", username);
        return trainingDao.findAll().stream()
                .filter(t -> t.getTrainer().getUsername().equals(username))
                .filter(t -> fromDate == null || t.getTrainingDate().isAfter(fromDate.atStartOfDay()))
                .filter(t -> toDate == null || t.getTrainingDate().isBefore(toDate.atStartOfDay()))
                .filter(t -> traineeName == null || t.getTrainee().getUsername().equals(traineeName))
                .collect(Collectors.toList());
    }

}