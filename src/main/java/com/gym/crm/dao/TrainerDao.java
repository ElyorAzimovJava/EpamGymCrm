package com.gym.crm.dao;

import com.gym.crm.entity.Trainer;

import java.util.UUID;

public interface TrainerDao extends Dao<Trainer, UUID> {
    Trainer findByUsername(String username);
}