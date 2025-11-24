package com.gym.crm.dao.impl;

import com.gym.crm.dao.TrainerDao;
import com.gym.crm.model.Trainer;
import com.gym.crm.storage.InMemoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class TrainerDaoImpl implements TrainerDao {

    private final InMemoryStorage<Trainer> trainerStorage;

    @Autowired
    public TrainerDaoImpl(InMemoryStorage<Trainer> trainerStorage) {
        this.trainerStorage = trainerStorage;
    }

    @Override
    public Trainer create(Trainer entity) {
        trainerStorage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Trainer findById(UUID id) {
        return trainerStorage.get(id);
    }

    @Override
    public List<Trainer> findAll() {
        return new ArrayList<>(trainerStorage.values());
    }

    @Override
    public Trainer update(Trainer entity) {
        trainerStorage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Trainer findByUsername(String username) {
        return trainerStorage.values()
                .stream()
                .filter(trainer -> trainer.getUsername().equals(username))
                .findFirst().orElse(null);

    }

    @Override
    public void delete(UUID id) {
        trainerStorage.remove(id);
    }
}