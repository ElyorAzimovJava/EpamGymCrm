package com.gym.crm.service;

import com.gym.crm.dao.TraineeDao;
import com.gym.crm.model.Trainee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Slf4j
@Service
public class TraineeService {

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
        String username = userService.generateUsername(trainee.getFirstName(), trainee.getLastName());
        trainee.setUsername(username);
        trainee.setPassword(PasswordGenerator.generatePassword());
        Trainee saved = traineeDao.create(trainee);
        log.info("Trainee with username {} saved successfully", saved.getUsername());
        return saved;
    }

    public Trainee updateTrainee(Trainee trainee) {
        traineeDao.update(trainee);
        return trainee;
    }

    public void deleteTrainee(UUID id) {
        traineeDao.delete(id);
    }

    public Trainee findById(UUID id) {
        return traineeDao.findById(id);
    }

    public List<Trainee> listAll() {
        return traineeDao.findAll();
    }
}