package com.gym.crm.dao.impl;

import com.gym.crm.dao.TrainerDao;
import com.gym.crm.model.Trainer;
import com.gym.crm.storage.InMemoryStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Repository
public class TrainerDaoImpl implements TrainerDao {

    private final InMemoryStorage<Trainer> trainerStorage;

    @Autowired
    public TrainerDaoImpl(InMemoryStorage<Trainer> trainerStorage) {
        this.trainerStorage = trainerStorage;
    }

    @Override
    public Trainer create(Trainer entity) {
        log.trace("Creating trainer: {}", entity);
        entity.setId(UUID.randomUUID());
        trainerStorage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Trainer findById(UUID id) {
        log.trace("Finding trainer by ID: {}", id);
        return trainerStorage.get(id);
    }

    @Override
    public List<Trainer> findAll() {
        log.trace("Finding all trainers");
        return new ArrayList<>(trainerStorage.values());
    }

    @Override
    public Trainer update(Trainer entity) {
        log.trace("Updating trainer: {}", entity);
        trainerStorage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Trainer findByUsername(String username) {
        log.trace("Finding trainer by username: {}", username);
        return trainerStorage.values()
                .stream()
                .filter(trainer -> trainer.getUsername().equals(username))
                .findFirst().orElse(null);

    }

    @Override
    public void delete(UUID id) {
        log.trace("Deleting trainer by ID: {}", id);
        trainerStorage.remove(id);
    }
}