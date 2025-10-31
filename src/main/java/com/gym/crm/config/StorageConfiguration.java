package com.gym.crm.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

@Configuration
@Slf4j
public class StorageConfiguration {
    public static final String TRAINER_DB_BEAN_NAME = "trainerDb";
    public static final String TRAINEE_DB_BEAN_NAME = "traineeDb";
    public static final String TRAINING_DB_BEAN_NAME = "trainingDb";

    @Bean(name = TRAINER_DB_BEAN_NAME)
    public Map<UUID, Object> trainerDb() {
        log.info("Trainer DB bean has been initialized");
        return new ConcurrentHashMap<>();
    }

    @Bean(name = TRAINEE_DB_BEAN_NAME)
    public Map<UUID, Object> traineeDb() {
        log.info("Trainee DB bean has been initialized");
        return new ConcurrentHashMap<>();
    }

    @Bean(name = TRAINING_DB_BEAN_NAME)
    public Map<UUID, Object> trainingDb() {
        log.info("Training DB bean has been initialized");
        return new ConcurrentHashMap<>();
    }
}
