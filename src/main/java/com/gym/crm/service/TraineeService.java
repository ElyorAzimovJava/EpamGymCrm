package com.gym.crm.service;

import com.gym.crm.dao.TraineeDao;
import com.gym.crm.dao.TrainerDao;
import com.gym.crm.model.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class TraineeService {
    private static final Logger LOG = Logger.getLogger(TraineeService.class.getName());
    private TraineeDao traineeDao;
    private TrainerDao trainerDao;
    @Autowired
    public void setTraineeDao(TraineeDao traineeDao) {
        this.traineeDao = traineeDao;
    }
    @Autowired
    public void setTrainerDao(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }
    public Trainee createTrainee(Trainee t) {
        String base = UsernameAndPasswordUtil.buildUsername(t.getFirstName(), t.getLastName());
        String username = base;
        int serial = 1;
        while (existsUsername(username)) {
            username = base + serial;
            serial++;
        }
        t.setUsername(username);
        t.setPassword(UsernameAndPasswordUtil.generatePassword(10));
        Trainee saved = traineeDao.create(t);
        LOG.info("Trainee saved: " + saved);
        return saved;
    }


    private boolean existsUsername(String username) {
        return traineeDao.findByUsername(username) != null || trainerDao.findByUsername(username) != null;
    }
    public Trainee updateTrainee(Trainee t) { traineeDao.update(t); return t; }
    public void deleteTrainee(UUID id) { traineeDao.delete(id); }
    public Trainee findById(UUID id) { return traineeDao.findById(id); }
    public List<Trainee> listAll() { return traineeDao.findAll(); }
}
