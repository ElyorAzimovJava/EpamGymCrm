package com.gym.crm.dao;

import com.gym.crm.model.Trainee;
import com.gym.crm.model.Trainer;

import java.util.List;
import java.util.UUID;

public interface TraineeDao extends Dao<Trainee, UUID> {
    Trainee findByUsername(String username);
}