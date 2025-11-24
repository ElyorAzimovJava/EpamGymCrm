package com.gym.crm.service.impl;

import com.gym.crm.dao.TraineeDao;
import com.gym.crm.dao.TrainerDao;
import com.gym.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

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
        List<String> allUsernames = getAllUsernames();
        while (allUsernames.contains(username)) {
            username = base + serial;
            serial++;
        }
        return username;
    }

    private List<String> getAllUsernames() {
        return Stream.concat(
                traineeDao.findAll().stream().map(trainee -> trainee.getUsername()),
                trainerDao.findAll().stream().map(trainer -> trainer.getUsername())
        ).collect(Collectors.toList());
    }

    private String buildUsername(String firstName, String lastName) {
        return String.format("%s.%s", firstName.trim(), lastName.trim());
    }
}