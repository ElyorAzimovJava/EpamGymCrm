package com.gym.crm.service;

import com.gym.crm.dao.TraineeDao;
import com.gym.crm.dao.TrainerDao;
import com.gym.crm.model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
@Service
public class TrainerService {
    private static final Logger LOG = Logger.getLogger(TrainerService.class.getName());
    private TrainerDao trainerDao;
    private TraineeDao traineeDao;
    @Autowired
    public void setTrainerDao(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }
    @Autowired
    public void setTraineeDao(TraineeDao traineeDao) {
        this.traineeDao = traineeDao;
    }
    public Trainer createTrainer(Trainer trainer) {
        String base = UsernameAndPasswordUtil.buildUsername(trainer.getFirstName(), trainer.getLastName());
        String username = base;
        int serial = 1;
        while(existsUsername(username)){
            username= base + serial;
            serial++;
        }
        trainer.setUsername(username);
        trainer.setPassword(UsernameAndPasswordUtil.generatePassword(10));
        Trainer newTrainer = trainerDao.create(trainer);
        LOG.info("Trainer saved successfully");
        return newTrainer;
    }
    private boolean existsUsername(String username) {
        return trainerDao.findByUsername(username) != null || traineeDao.findByUsername(username) != null;
    }
    public Trainer updateTrainer(Trainer trainer) {
        trainerDao.update(trainer);
        return trainer;
    }
    public Trainer findById(UUID id) { return trainerDao.findById(id); }
    public List<Trainer> listAll() { return trainerDao.findAll(); }
}
