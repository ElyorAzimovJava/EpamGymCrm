package com.gym.crm.storage;

import com.gym.crm.model.Trainer;

public class InMemoryTrainerStorageImpl extends InMemoryStorage<Trainer> {
    @Override
    public Class<Trainer> getValueType() {
        return Trainer.class;
    }
}