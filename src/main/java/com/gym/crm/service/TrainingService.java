package com.gym.crm.service;

import com.gym.crm.model.Training;

import java.util.List;
import java.util.UUID;

public interface TrainingService {
    Training createTraining(Training training);
    Training findById(UUID id);
    List<Training> listAll();
}