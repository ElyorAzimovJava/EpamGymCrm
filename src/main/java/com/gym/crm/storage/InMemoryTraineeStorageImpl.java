package com.gym.crm.storage;

import com.gym.crm.model.Trainee;

public class InMemoryTraineeStorageImpl extends InMemoryStorage<Trainee> {
    @Override
    public Class<Trainee> getValueType() {
        return Trainee.class;
    }
}