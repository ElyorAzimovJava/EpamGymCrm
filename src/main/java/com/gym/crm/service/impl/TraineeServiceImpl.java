package com.gym.crm.service.impl;

import com.gym.crm.dao.TraineeDao;
import com.gym.crm.entity.Trainee;
import com.gym.crm.entity.Trainer;
import com.gym.crm.service.TraineeService;
import com.gym.crm.service.TrainerService;
import com.gym.crm.service.UserService;
import com.gym.crm.utils.PasswordGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Slf4j
@Service
public class TraineeServiceImpl implements TraineeService {

    private final TraineeDao traineeDao;
    private final UserService userService;
    private final PasswordGenerator passwordGenerator;
    private final TrainerService trainerService;

    @Autowired
    public TraineeServiceImpl(TraineeDao traineeDao, UserService userService, PasswordGenerator passwordGenerator, TrainerService trainerService) {
        this.traineeDao = traineeDao;
        this.userService = userService;
        this.passwordGenerator = passwordGenerator;
        this.trainerService = trainerService;
    }

    public Trainee createTrainee(Trainee trainee) {
        log.debug("Creating trainee with details: {}", trainee);
        String username = userService.generateUsername(trainee.getFirstName(), trainee.getLastName());
        trainee.setUsername(username);
        trainee.setPassword(passwordGenerator.generatePassword(10));
        Trainee saved = traineeDao.create(trainee);
        log.info("Trainee with username {} saved successfully", saved.getUsername());
        return saved;
    }

    public Trainee updateTrainee(Trainee trainee) {
        log.debug("Updating trainee with details: {}", trainee);
        return traineeDao.update(trainee);
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

    @Override
    public Trainee findByUsername(String username) {
        log.debug("Finding trainee with username: {}", username);
        return traineeDao.findByUsername(username);
    }

    @Override
    public void activate(UUID id) {
        log.debug("Activating trainee with ID: {}", id);
        Trainee trainee = findById(id);
        if (trainee != null) {
            trainee.setActive(true);
            traineeDao.update(trainee);
        }
    }

    @Override
    public void deactivate(UUID id) {
        log.debug("Deactivating trainee with ID: {}", id);
        Trainee trainee = findById(id);
        if (trainee != null) {
            trainee.setActive(false);
            traineeDao.update(trainee);
        }
    }

    @Override
    public void deleteByUsername(String username) {
        log.debug("Deleting trainee with username: {}", username);
        Trainee trainee = findByUsername(username);
        if (trainee != null) {
            traineeDao.delete(trainee.getId());
        }
    }

    @Override
    public void updateTraineeTrainers(String username, List<String> trainerUsernames) {
        log.debug("Updating trainers for trainee: {}", username);
        Trainee trainee = findByUsername(username);
        if (trainee != null) {
            List<Trainer> trainers = trainerUsernames.stream()
                    .map(trainerService::findByUsername)
                    .collect(Collectors.toList());
            trainee.setTrainers(trainers);
            traineeDao.update(trainee);
        }
    }
}