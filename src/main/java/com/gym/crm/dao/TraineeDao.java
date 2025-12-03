package com.gym.crm.dao;

import com.gym.crm.entity.Trainee;

import java.util.UUID;

public interface TraineeDao extends Dao<Trainee, UUID> {
    Trainee findByUsername(String username);
}