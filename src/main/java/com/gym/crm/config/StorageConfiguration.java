package com.gym.crm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

@Configuration
public class StorageConfiguration {
    private static final Logger LOG = Logger.getLogger(StorageConfiguration.class.getName());
    public static final String TRAINER_DB_BEAN_NAME = "trainerDb";
    public static final String TRAINEE_DB_BEAN_NAME = "traineeDb";
    public static final String TRAINING_DB_BEAN_NAME = "trainingDb";

    @Bean(name = TRAINER_DB_BEAN_NAME)
    public Map<UUID, Object> trainerDb() {
        LOG.info("Trainer DB bean has been initialized");
        return new ConcurrentHashMap<>();
    }

    @Bean(name = TRAINEE_DB_BEAN_NAME)
    public Map<UUID, Object> traineeDb() {
        LOG.info("Trainee DB bean has been initialized");
        return new ConcurrentHashMap<>();
    }

    @Bean(name = TRAINING_DB_BEAN_NAME)
    public Map<UUID, Object> trainingDb() {
        LOG.info("Training DB bean has been initialized");
        return new ConcurrentHashMap<>();
    }
}
