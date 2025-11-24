package com.gym.crm.dao.impl;

import com.gym.crm.dao.Dao;
import com.gym.crm.model.Training;
import com.gym.crm.storage.InMemoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class TrainingDaoImpl implements Dao<Training, UUID> {

    private final InMemoryStorage<Training> trainingStorage;

    @Autowired
    public TrainingDaoImpl(InMemoryStorage<Training> trainingStorage) {
        this.trainingStorage = trainingStorage;
    }

    @Override
    public Training create(Training entity) {
        entity.setId(UUID.randomUUID());
        trainingStorage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Training findById(UUID id) {
        return trainingStorage.get(id);
    }

    @Override
    public List<Training> findAll() {
        return new ArrayList<>(trainingStorage.values());
    }

    @Override
    public Training update(Training entity) {
        trainingStorage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void delete(UUID id) {
        trainingStorage.remove(id);
    }
}