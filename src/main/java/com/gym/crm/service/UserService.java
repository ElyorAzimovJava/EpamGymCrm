package com.gym.crm.service;

import com.gym.crm.dao.TraineeDao;
import com.gym.crm.dao.TrainerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

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

    public String generateUsername(String firstName, String lastName) {
        String base = buildUsername(firstName, lastName);
        String username = base;
        int serial = 1;
        while (existsUsername(username)) {
            username = base + serial;
            serial++;
        }
        return username;
    }

    private boolean existsUsername(String username) {
        return traineeDao.findByUsername(username) != null || trainerDao.findByUsername(username) != null;
    }

    private String buildUsername(String firstName, String lastName) {
        return String.format("%s.%s", firstName.trim(), lastName.trim());
    }
}