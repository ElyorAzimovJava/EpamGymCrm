package com.gym.crm.dao.impl;

import com.gym.crm.entity.Trainer;
import com.gym.crm.entity.TrainingType;
import com.gym.crm.storage.InMemoryStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TrainerDaoImplTest {

    @Mock
    private InMemoryStorage<Trainer> storage;

    @InjectMocks
    private TrainerDaoImpl trainerDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Trainer trainer = new Trainer();
        trainer.setFirstName("John");
        trainer.setLastName("Doe");
        trainer.setUsername("john.doe");
        trainer.setPassword("password".toCharArray());
        trainer.setActive(true);
        trainer.setSpecialization(new TrainingType(UUID.randomUUID(),"Pull up"));
        trainerDao.create(trainer);
        assertNotNull(trainer.getId());
        verify(storage).put(trainer.getId(), trainer);
    }

    @Test
    void testFindById() {
        UUID id = UUID.randomUUID();
        Trainer trainer = new Trainer();
        when(storage.get(id)).thenReturn(trainer);
        Trainer result = trainerDao.findById(id);
        assertEquals(trainer, result);
    }

    @Test
    void testFindAll() {
        Trainer trainer1 = new Trainer();
        Trainer trainer2 = new Trainer();
        when(storage.values()).thenReturn(List.of(trainer1, trainer2));
        List<Trainer> result = trainerDao.findAll();
        assertEquals(2, result.size());
    }

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();
        trainerDao.delete(id);
        verify(storage).remove(id);
    }
}