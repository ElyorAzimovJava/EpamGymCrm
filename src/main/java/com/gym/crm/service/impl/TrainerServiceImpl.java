package com.gym.crm.service.impl;

import com.gym.crm.dao.TrainerDao;
import com.gym.crm.entity.Trainer;
import com.gym.crm.entity.Trainee;
import com.gym.crm.service.TraineeService;
import com.gym.crm.service.TrainerService;
import com.gym.crm.service.UserService;
import com.gym.crm.utils.PasswordGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TrainerServiceImpl implements TrainerService {

    private final TrainerDao trainerDao;
    private final UserService userService;
    private final TraineeService traineeService;
    private final PasswordGenerator passwordGenerator;

    @Autowired
    public TrainerServiceImpl(TrainerDao trainerDao, UserService userService, @Lazy TraineeService traineeService, PasswordGenerator passwordGenerator) {
        this.trainerDao = trainerDao;
        this.userService = userService;
        this.traineeService = traineeService;
        this.passwordGenerator = passwordGenerator;
    }

    public Trainer createTrainer(Trainer trainer) {
        log.debug("Creating trainer with details: {}", trainer);
        String username = userService.generateUsername(trainer.getFirstName(), trainer.getLastName());
        trainer.setUsername(username);
        trainer.setPassword(passwordGenerator.generatePassword(10));
        Trainer newTrainer = trainerDao.create(trainer);
        log.info("Trainer with username {} saved successfully", newTrainer.getUsername());
        return newTrainer;
    }

    public Trainer updateTrainer(Trainer trainer) {
        log.debug("Updating trainer with details: {}", trainer);
        trainerDao.update(trainer);
        return trainer;
    }

    public Trainer findById(UUID id) {
        log.debug("Finding trainer with ID: {}", id);
        return trainerDao.findById(id);
    }

    public List<Trainer> listAll() {
        log.debug("Listing all trainers");
        return trainerDao.findAll();
    }

    @Override
    public Trainer findByUsername(String username) {
        log.debug("Finding trainer with username: {}", username);
        return trainerDao.findAll().stream()
                .filter(trainer -> trainer.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void activate(UUID id) {
        log.debug("Activating trainer with ID: {}", id);
        Trainer trainer = findById(id);
        if (trainer != null) {
            trainer.setActive(true);
            trainerDao.update(trainer);
        }
    }

    @Override
    public void deactivate(UUID id) {
        log.debug("Deactivating trainer with ID: {}", id);
        Trainer trainer = findById(id);
        if (trainer != null) {
            trainer.setActive(false);
            trainerDao.update(trainer);
        }
    }

    @Override
    public List<Trainer> getUnassignedTrainers(String traineeUsername) {
        log.debug("Getting unassigned trainers for trainee: {}", traineeUsername);
        Trainee trainee = traineeService.findByUsername(traineeUsername);
        if (trainee != null) {
            List<Trainer> allTrainers = trainerDao.findAll();
            List<Trainer> traineeTrainers = trainee.getTrainers();
            return allTrainers.stream()
                    .filter(trainer -> !traineeTrainers.contains(trainer))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}