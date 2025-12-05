package com.gym.crm.service.impl;

import com.gym.crm.dao.TraineeDao;
import com.gym.crm.entity.Trainee;
import com.gym.crm.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TraineeServiceImplTest {

    @Mock
    private TraineeDao traineeDao;

    @Mock
    private UserService userService;

    @InjectMocks
    private TraineeServiceImpl traineeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTrainee() {
        Trainee trainee = new Trainee();
        trainee.setFirstName("John");
        trainee.setLastName("Doe");
        trainee.setActive(true);
        trainee.setDateOfBirth(LocalDate.of(2005,2,12));
        trainee.setAddress("address");
        when(userService.generateUsername("John", "Doe")).thenReturn("john.doe");
        when(traineeDao.create(any(Trainee.class))).thenReturn(trainee);
        traineeService.createTrainee(trainee);
        assertNotNull(trainee.getUsername());
        assertNotNull(trainee.getPassword());
        verify(traineeDao).create(trainee);
    }

    @Test
    void testUpdateTrainee() {
        Trainee trainee = new Trainee();
        traineeService.updateTrainee(trainee);
        verify(traineeDao).update(trainee);
    }

    @Test
    void testDeleteTrainee() {
        UUID id = UUID.randomUUID();
        traineeService.deleteTrainee(id);
        verify(traineeDao).delete(id);
    }

    @Test
    void testFindById() {
        UUID id = UUID.randomUUID();
        Trainee trainee = new Trainee();
        when(traineeDao.findById(id)).thenReturn(trainee);
        Trainee result = traineeService.findById(id);
        assertEquals(trainee, result);
    }

    @Test
    void testListAll() {
        Trainee trainee1 = new Trainee();
        Trainee trainee2 = new Trainee();
        when(traineeDao.findAll()).thenReturn(List.of(trainee1, trainee2));
        List<Trainee> result = traineeService.listAll();
        assertEquals(2, result.size());
    }
}