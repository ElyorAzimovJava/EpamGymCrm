package com.gym.crm.service;

import com.gym.crm.dao.Dao;
import com.gym.crm.model.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TrainingService {

    private Dao<Training, UUID> trainingDao;

    @Autowired
    public void setTrainingDao(Dao<Training, UUID> trainingDao) { this.trainingDao = trainingDao; }

    public Training createTraining(Training training) {
        Training saved = trainingDao.create(training);
        log.info("Training created: {}", saved);
        return saved;
    }


    public Training findById(UUID id) { return trainingDao.findById(id); }
    public List<Training> listAll() { return trainingDao.findAll(); }
}