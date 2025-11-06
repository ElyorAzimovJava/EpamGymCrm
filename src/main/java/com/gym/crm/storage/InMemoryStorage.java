package com.gym.crm.storage;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public abstract class InMemoryStorage<T> extends ConcurrentHashMap<UUID, T> {
    public abstract Class<T> getValueType();
}