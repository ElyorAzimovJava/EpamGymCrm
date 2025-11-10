package com.gym.crm.dao;

import com.gym.crm.model.Trainee;
import com.gym.crm.storage.InMemoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class TraineeDaoImpl implements TraineeDao {

    private final InMemoryStorage<Trainee> traineeStorage;

    @Autowired
    public TraineeDaoImpl(InMemoryStorage<Trainee> traineeStorage) {
        this.traineeStorage = traineeStorage;
    }

    @Override
    public Trainee create(Trainee entity) {
        traineeStorage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Trainee findById(UUID id) {
        return traineeStorage.get(id);
    }

    @Override
    public List<Trainee> findAll() {
        return new ArrayList<>(traineeStorage.values());
    }

    @Override
    public Trainee update(Trainee entity) {
        traineeStorage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void delete(UUID id) {
        traineeStorage.remove(id);
    }
}