package com.gym.crm.service.impl;

import com.gym.crm.dao.impl.TrainingDaoImpl;
import com.gym.crm.model.Training;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TrainingServiceImplTest {

    @Mock
    private TrainingDaoImpl trainingDao;

    @InjectMocks
    private TrainingServiceImpl trainingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTraining() {
        Training training = new Training(null, null, null, "test", null, null, 60L);
        trainingService.createTraining(training);
        verify(trainingDao).create(training);
    }

    @Test
    void testFindById() {
        UUID id = UUID.randomUUID();
        Training training = new Training(id, null, null, "test", null, null, 60L);
        when(trainingDao.findById(id)).thenReturn(training);
        Training result = trainingService.findById(id);
        assertEquals(training, result);
    }

    @Test
    void testListAll() {
        Training training1 = new Training(null, null, null, "test1", null, null, 60L);
        Training training2 = new Training(null, null, null, "test2", null, null, 60L);
        when(trainingDao.findAll()).thenReturn(List.of(training1, training2));
        List<Training> result = trainingService.listAll();
        assertEquals(2, result.size());
    }
}