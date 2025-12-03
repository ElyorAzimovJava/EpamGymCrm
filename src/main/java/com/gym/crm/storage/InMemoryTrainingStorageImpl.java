package com.gym.crm.storage;

import com.gym.crm.entity.Training;

public class InMemoryTrainingStorageImpl extends InMemoryStorage<Training> {
    @Override
    public Class<Training> getValueType() {
        return Training.class;
    }
}