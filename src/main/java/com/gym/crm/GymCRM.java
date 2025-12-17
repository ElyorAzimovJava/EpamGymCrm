package com.gym.crm;

import com.gym.crm.config.AppConfig;
import com.gym.crm.entity.Trainee;
import com.gym.crm.entity.Trainer;
import com.gym.crm.entity.Training;
import com.gym.crm.entity.TrainingType;
import com.gym.crm.security.SecurityService;
import com.gym.crm.service.TraineeService;
import com.gym.crm.service.TrainerService;
import com.gym.crm.service.TrainingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
public class GymCRM {
    public static void main(String[] args) {
        log.info("Application starting...");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        log.info("Application context has been initialized");

        TraineeService traineeService = context.getBean(TraineeService.class);
        TrainerService trainerService = context.getBean(TrainerService.class);
        TrainingService trainingService = context.getBean(TrainingService.class);
        SecurityService securityService = context.getBean(SecurityService.class);

        Trainee createdTrainee = traineeService.createTrainee(new Trainee("John", "Doe","john_doe", "123456".toCharArray(), true, LocalDate.of(2005, 2, 12), "123 Main St"));
        log.info("Trainee created: " + createdTrainee);

        TrainingType trainingType = new TrainingType();
        trainingType.setTrainingTypeName("Cardio");

        Trainer trainer = new Trainer();
        trainer.setFirstName("Jane");
        trainer.setLastName("Doe");
        trainer.setSpecialization(trainingType);
        Trainer createdTrainer = trainerService.createTrainer(trainer);

        securityService.login(createdTrainee.getUsername(), createdTrainee.getPassword());
        log.info("Authentication successful");

        Training training = new Training();
        training.setTrainee(createdTrainee);
        training.setTrainer(createdTrainer);
        training.setTrainingName("Running");
        training.setTrainingDate(LocalDateTime.now());
        training.setDurationMinutes(60L);
        Training createdTraining = trainingService.createTraining(training);
    }
}