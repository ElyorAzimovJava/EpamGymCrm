package com.gym.crm.config;

import com.gym.crm.entity.Trainee;
import com.gym.crm.entity.Trainer;
import com.gym.crm.entity.Training;
import com.gym.crm.storage.InMemoryStorage;
import com.gym.crm.storage.InMemoryTraineeStorageImpl;
import com.gym.crm.storage.InMemoryTrainerStorageImpl;
import com.gym.crm.storage.InMemoryTrainingStorageImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class StorageConfiguration {

    public static final String TRAINER_DB_BEAN_NAME = "trainerDb";
    public static final String TRAINEE_DB_BEAN_NAME = "traineeDb";
    public static final String TRAINING_DB_BEAN_NAME = "trainingDb";

    @Bean(name = TRAINER_DB_BEAN_NAME)
    public InMemoryStorage<Trainer> trainerDb() {
        log.info("Trainer DB bean has been initialized");
        return new InMemoryTrainerStorageImpl();
    }

    @Bean(name = TRAINEE_DB_BEAN_NAME)
    public InMemoryStorage<Trainee> traineeDb() {
        log.info("Trainee DB bean has been initialized");
        return new InMemoryTraineeStorageImpl();
    }

    @Bean(name = TRAINING_DB_BEAN_NAME)
    public InMemoryStorage<Training> trainingDb() {
        log.info("Training DB bean has been initialized");
        return new InMemoryTrainingStorageImpl();
    }
}