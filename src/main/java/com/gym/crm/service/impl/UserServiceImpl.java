package com.gym.crm.service.impl;

import com.gym.crm.dao.TraineeDao;
import com.gym.crm.dao.TrainerDao;
import com.gym.crm.entity.User;
import com.gym.crm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    @Override
    public boolean checkPassword(String username, char[] password) {
        log.debug("Checking password for user: {}", username);
        User user = findByUsername(username);
        if (user != null) {
            boolean matches = Arrays.equals(user.getPassword(), password);
            log.debug("Password for user {} matches: {}", username, matches);
            return matches;
        }
        log.debug("User {} not found", username);
        return false;
    }

    @Override
    public void changePassword(String username, char[] oldPassword, char[] newPassword) {
        log.debug("Changing password for user: {}", username);
        if (checkPassword(username, oldPassword)) {
            User user = findByUsername(username);
            if (user != null) {
                user.setPassword(newPassword);
                log.debug("Password changed successfully for user: {}", username);
            }
        } else {
            log.warn("Password change failed for user: {}. Old password did not match", username);
        }
    }

    private User findByUsername(String username) {
        log.debug("Finding user by username: {}", username);
        return Stream.concat(
                        traineeDao.findAll().stream(),
                        trainerDao.findAll().stream()
                )
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
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