package com.gym.crm.service.impl;

import com.gym.crm.dao.Dao;
import com.gym.crm.model.Training;
import com.gym.crm.service.TrainingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
}