package com.gym.crm.service;

import com.gym.crm.entity.Trainer;

import java.util.List;
import java.util.UUID;

public interface TrainerService {
    Trainer createTrainer(Trainer trainer);
    Trainer updateTrainer(Trainer trainer);
    Trainer findById(UUID id);
    List<Trainer> listAll();
}