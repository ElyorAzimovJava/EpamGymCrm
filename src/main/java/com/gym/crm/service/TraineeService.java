package com.gym.crm.service;

import com.gym.crm.entity.Trainee;

import java.util.List;
import java.util.UUID;

public interface TraineeService {
    Trainee createTrainee(Trainee trainee);
    Trainee updateTrainee(Trainee trainee);
    void deleteTrainee(UUID id);
    Trainee findById(UUID id);
    List<Trainee> listAll();
    Trainee findByUsername(String username);
    void activate(UUID id);
    void deactivate(UUID id);
    void deleteByUsername(String username);
    void updateTraineeTrainers(String username, List<String> trainerUsernames);
}