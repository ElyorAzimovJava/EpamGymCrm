package com.gym.crm.service.impl;

import com.gym.crm.dao.TrainerDao;
import com.gym.crm.entity.Trainer;
import com.gym.crm.entity.TrainingType;
import com.gym.crm.service.TraineeService;
import com.gym.crm.service.UserService;
import com.gym.crm.utils.PasswordGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TrainerServiceImplTest {

    @Mock
    private TrainerDao trainerDao;

    @Mock
    private UserService userService;

    @Mock
    private TraineeService traineeService;

    @Mock
    private PasswordGenerator passwordGenerator;

    @InjectMocks
    private TrainerServiceImpl trainerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTrainer() {
        Trainer trainer = new Trainer();
        trainer.setFirstName("John");
        trainer.setLastName("Doe");
        trainer.setActive(true);
        trainer.setSpecialization(new TrainingType(UUID.randomUUID(),"Push up"));
        when(userService.generateUsername("John", "Doe")).thenReturn("john.doe");
        when(passwordGenerator.generatePassword(10)).thenReturn("password".toCharArray());
        when(trainerDao.create(any(Trainer.class))).thenReturn(trainer);
        trainerService.createTrainer(trainer);
        assertNotNull(trainer.getUsername());
        assertNotNull(trainer.getPassword());
        verify(trainerDao).create(trainer);
    }

    @Test
    void testUpdateTrainer() {
        Trainer trainer = new Trainer();
        trainerService.updateTrainer(trainer);
        verify(trainerDao).update(trainer);
    }

    @Test
    void testFindById() {
        UUID id = UUID.randomUUID();
        Trainer trainer = new Trainer();
        when(trainerDao.findById(id)).thenReturn(trainer);
        Trainer result = trainerService.findById(id);
        assertEquals(trainer, result);
    }

    @Test
    void testListAll() {
        Trainer trainer1 = new Trainer();
        Trainer trainer2 = new Trainer();
        when(trainerDao.findAll()).thenReturn(List.of(trainer1, trainer2));
        List<Trainer> result = trainerService.listAll();
        assertEquals(2, result.size());
    }
}