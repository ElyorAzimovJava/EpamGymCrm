package com.gym.crm.service.impl;

import com.gym.crm.dao.TrainingDao;
import com.gym.crm.entity.Trainee;
import com.gym.crm.entity.Trainer;
import com.gym.crm.entity.Training;
import com.gym.crm.entity.TrainingType;
import com.gym.crm.service.TraineeService;
import com.gym.crm.service.TrainerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TrainingServiceImplTest {

    @Mock
    private TrainingDao trainingDao;

    @Mock
    private TraineeService traineeService;

    @Mock
    private TrainerService trainerService;

    @InjectMocks
    private TrainingServiceImpl trainingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTraining() {
        Trainee trainee = new Trainee();
        trainee.setId(UUID.randomUUID());

        TrainingType trainingType = new TrainingType();
        Trainer trainer = new Trainer();
        trainer.setId(UUID.randomUUID());
        trainer.setSpecialization(trainingType);

        Training training = new Training();
        training.setTrainee(trainee);
        training.setTrainer(trainer);

        when(traineeService.findById(trainee.getId())).thenReturn(trainee);
        when(trainerService.findById(trainer.getId())).thenReturn(trainer);

        trainingService.createTraining(training);

        ArgumentCaptor<Training> trainingArgumentCaptor = ArgumentCaptor.forClass(Training.class);
        verify(trainingDao).create(trainingArgumentCaptor.capture());

        Training capturedTraining = trainingArgumentCaptor.getValue();
        assertEquals(trainingType, capturedTraining.getTrainingType());
    }

    @Test
    void testFindById() {
        UUID id = UUID.randomUUID();
        Training training = new Training();
        when(trainingDao.findById(id)).thenReturn(training);
        Training result = trainingService.findById(id);
        assertEquals(training, result);
    }

    @Test
    void testListAll() {
        Training training1 = new Training();
        Training training2 = new Training();
        when(trainingDao.findAll()).thenReturn(List.of(training1, training2));
        List<Training> result = trainingService.listAll();
        assertEquals(2, result.size());
    }
}