package com.gym.crm.dao.impl;

import com.gym.crm.dao.Dao;
import com.gym.crm.model.Training;
import com.gym.crm.storage.InMemoryStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Repository
public class TrainingDaoImpl implements Dao<Training, UUID> {

    private final InMemoryStorage<Training> trainingStorage;

    @Autowired
    public TrainingDaoImpl(InMemoryStorage<Training> trainingStorage) {
        this.trainingStorage = trainingStorage;
    }

    @Override
    public Training create(Training entity) {
        log.trace("Creating training: {}", entity);
        entity.setId(UUID.randomUUID());
        trainingStorage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Training findById(UUID id) {
        log.trace("Finding training by ID: {}", id);
        return trainingStorage.get(id);
    }

    @Override
    public List<Training> findAll() {
        log.trace("Finding all trainings");
        return new ArrayList<>(trainingStorage.values());
    }

    @Override
    public Training update(Training entity) {
        log.trace("Updating training: {}", entity);
        trainingStorage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void delete(UUID id) {
        log.trace("Deleting training by ID: {}", id);
        trainingStorage.remove(id);
    }
}