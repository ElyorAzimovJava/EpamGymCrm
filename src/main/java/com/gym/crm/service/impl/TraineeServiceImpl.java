package com.gym.crm.service.impl;

import com.gym.crm.dao.TraineeDao;
import com.gym.crm.entity.Trainee;
import com.gym.crm.service.PasswordGenerator;
import com.gym.crm.service.TraineeService;
import com.gym.crm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Slf4j
@Service
public class TraineeServiceImpl implements TraineeService {

    private TraineeDao traineeDao;

    private UserService userService;

    @Autowired
    public void setTraineeDao(TraineeDao traineeDao) {
        this.traineeDao = traineeDao;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Trainee createTrainee(Trainee trainee) {
        log.debug("Creating trainee with details: {}", trainee);
        String username = userService.generateUsername(trainee.getFirstName(), trainee.getLastName());
        trainee.setUsername(username);
        trainee.setPassword(PasswordGenerator.generatePassword(10));
        Trainee saved = traineeDao.create(trainee);
        log.info("Trainee with username {} saved successfully", saved.getUsername());
        return saved;
    }

    public Trainee updateTrainee(Trainee trainee) {
        log.debug("Updating trainee with details: {}", trainee);
        traineeDao.update(trainee);
        return trainee;
    }

    public void deleteTrainee(UUID id) {
        log.debug("Deleting trainee with ID: {}", id);
        traineeDao.delete(id);
    }

    public Trainee findById(UUID id) {
        log.debug("Finding trainee with ID: {}", id);
        return traineeDao.findById(id);
    }

    public List<Trainee> listAll() {
        log.debug("Listing all trainees");
        return traineeDao.findAll();
    }
}