package com.gym.crm.service;

import com.gym.crm.dao.TrainerDao;
import com.gym.crm.model.Trainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;



@Slf4j
@Service
public class TrainerService {

    private TrainerDao trainerDao;

    private UserService userService;

    @Autowired
    public void setTrainerDao(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Trainer createTrainer(Trainer trainer) {
        String username = userService.generateUsername(trainer.getFirstName(), trainer.getLastName());
        trainer.setUsername(username);
        trainer.setPassword(new String(PasswordGenerator.generatePassword(10)));
        Trainer newTrainer = trainerDao.create(trainer);
        log.info("Trainer saved successfully");
        return newTrainer;
    }

    public Trainer updateTrainer(Trainer trainer) {
        trainerDao.update(trainer);
        return trainer;
    }

    public Trainer findById(UUID id) {
        return trainerDao.findById(id);
    }

    public List<Trainer> listAll() {
        return trainerDao.findAll();
    }
}