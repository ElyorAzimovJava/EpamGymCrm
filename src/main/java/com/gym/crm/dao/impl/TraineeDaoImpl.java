package com.gym.crm.dao.impl;

import com.gym.crm.dao.TraineeDao;
import com.gym.crm.entity.Trainee;
import com.gym.crm.storage.InMemoryStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Repository
public class TraineeDaoImpl implements TraineeDao {

    private final InMemoryStorage<Trainee> traineeStorage;

    @Autowired
    public TraineeDaoImpl(InMemoryStorage<Trainee> traineeStorage) {
        this.traineeStorage = traineeStorage;
    }

    @Override
    public Trainee create(Trainee entity) {
        log.trace("Creating trainee: {}", entity);
        entity.setId(UUID.randomUUID());
        traineeStorage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Trainee findById(UUID id) {
        log.trace("Finding trainee by ID: {}", id);
        return traineeStorage.get(id);
    }

    @Override
    public List<Trainee> findAll() {
        log.trace("Finding all trainees");
        return new ArrayList<>(traineeStorage.values());
    }

    @Override
    public Trainee update(Trainee entity) {
        log.trace("Updating trainee: {}", entity);
        traineeStorage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void delete(UUID id) {
        log.trace("Deleting trainee by ID: {}", id);
        traineeStorage.remove(id);
    }

    @Override
    public Trainee findByUsername(String username) {
        log.trace("Finding trainee by username: {}", username);
        return traineeStorage.values()
                .stream()
                .filter(trainer -> trainer.getUsername().equals(username))
                .findFirst().orElse(null);
    }
}