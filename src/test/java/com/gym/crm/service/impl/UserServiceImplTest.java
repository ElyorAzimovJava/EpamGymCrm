package com.gym.crm.service.impl;

import com.gym.crm.dao.TraineeDao;
import com.gym.crm.dao.TrainerDao;
import com.gym.crm.entity.Trainee;
import com.gym.crm.entity.Trainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private TraineeDao traineeDao;

    @Mock
    private TrainerDao trainerDao;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateUsername_FirstTime() {
        when(traineeDao.findAll()).thenReturn(Collections.emptyList());
        when(trainerDao.findAll()).thenReturn(Collections.emptyList());
        String username = userService.generateUsername("John", "Doe");
        assertEquals("john.doe", username);
    }

    @Test
    void testGenerateUsername_AlreadyExists() {
        Trainee trainee = new Trainee();
        trainee.setUsername("john.doe");
        when(traineeDao.findAll()).thenReturn(Collections.singletonList(trainee));
        when(trainerDao.findAll()).thenReturn(Collections.emptyList());
        String username = userService.generateUsername("John", "Doe");
        assertEquals("john.doe1", username);
    }

    @Test
    void testGenerateUsername_MultipleExist() {
        Trainee trainee1 = new Trainee();
        trainee1.setUsername("john.doe");
        Trainer trainer1 = new Trainer();
        trainer1.setUsername("john.doe1");
        when(traineeDao.findAll()).thenReturn(Collections.singletonList(trainee1));
        when(trainerDao.findAll()).thenReturn(Collections.singletonList(trainer1));
        String username = userService.generateUsername("John", "Doe");
        assertEquals("john.doe2", username);
    }
}