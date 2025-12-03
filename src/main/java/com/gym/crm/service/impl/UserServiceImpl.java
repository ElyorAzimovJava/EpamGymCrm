package com.gym.crm.service.impl;

import com.gym.crm.dao.TraineeDao;
import com.gym.crm.dao.TrainerDao;
import com.gym.crm.entity.User;
import com.gym.crm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
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
        log.debug("Generating username for {} {}", firstName, lastName);
        String base = buildUsername(firstName, lastName);
        String username = base;
        int serial = 1;
        List<String> allUsernames = getAllUsernames();
        while (allUsernames.contains(username)) {
            username = base + serial;
            serial++;
        }
        log.debug("Generated username: {}", username);
        return username;
    }

    private List<String> getAllUsernames() {
        log.debug("Getting all usernames");
        return Stream.concat(
                traineeDao.findAll().stream().map(User::getUsername),
                trainerDao.findAll().stream().map(User::getUsername)
        ).collect(Collectors.toList());
    }

    private String buildUsername(String firstName, String lastName) {
        log.debug("Building username from first name: {} and last name: {}", firstName, lastName);
        return String.format("%s.%s", firstName.trim().toLowerCase(), lastName.trim().toLowerCase());
    }
}