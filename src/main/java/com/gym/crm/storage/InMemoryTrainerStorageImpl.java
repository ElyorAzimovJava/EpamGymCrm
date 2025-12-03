package com.gym.crm.storage;

import com.gym.crm.entity.Trainer;

public class InMemoryTrainerStorageImpl extends InMemoryStorage<Trainer> {
    @Override
    public Class<Trainer> getValueType() {
        return Trainer.class;
    }
}