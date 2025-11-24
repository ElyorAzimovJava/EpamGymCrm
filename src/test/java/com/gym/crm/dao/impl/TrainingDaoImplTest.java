package com.gym.crm.dao.impl;

import com.gym.crm.model.Training;
import com.gym.crm.storage.InMemoryStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TrainingDaoImplTest {

    @Mock
    private InMemoryStorage<Training> storage;

    @InjectMocks
    private TrainingDaoImpl trainingDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Training training = new Training(null, null, null, "test", null, null, 60L);
        trainingDao.create(training);
        assertNotNull(training.getId());
        verify(storage).put(training.getId(), training);
    }

    @Test
    void testFindById() {
        UUID id = UUID.randomUUID();
        Training training = new Training(null, null, null, "test", null, null, 60L);
        when(storage.get(id)).thenReturn(training);
        Training result = trainingDao.findById(id);
        assertEquals(training, result);
    }

    @Test
    void testFindAll() {
        Training training1 = new Training(null, null, null, "test1", null, null, 60L);
        Training training2 = new Training(null, null, null, "test2", null, null, 60L);
        when(storage.values()).thenReturn(List.of(training1, training2));
        List<Training> result = trainingDao.findAll();
        assertEquals(2, result.size());
    }
}