package com.gym.crm.service;

import com.gym.crm.model.Trainee;

import java.util.List;
import java.util.UUID;

public interface TraineeService {
    Trainee createTrainee(Trainee trainee);
    Trainee updateTrainee(Trainee trainee);
    void deleteTrainee(UUID id);
    Trainee findById(UUID id);
    List<Trainee> listAll();
}