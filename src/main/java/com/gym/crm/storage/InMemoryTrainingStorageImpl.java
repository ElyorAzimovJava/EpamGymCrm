package com.gym.crm.storage;

import com.gym.crm.model.Training;

public class InMemoryTrainingStorageImpl extends InMemoryStorage<Training> {
    @Override
    public Class<Training> getValueType() {
        return Training.class;
    }
}