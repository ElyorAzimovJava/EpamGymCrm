package com.gym.crm.dao.impl;

import com.gym.crm.entity.Trainee;
import com.gym.crm.storage.InMemoryStorage;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TraineeDaoImplTest {

    @Mock
    private InMemoryStorage<Trainee> storage;

    @InjectMocks
    private TraineeDaoImpl traineeDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Trainee trainee = new Trainee();
        trainee.setFirstName("John");
        trainee.setLastName("Doe");
        trainee.setUsername("john.doe");
        trainee.setPassword("password".toCharArray());
        trainee.setActive(true);
        trainee.setDateOfBirth(LocalDate.of(2005,2,12));
        trainee.setAddress("address");
        traineeDao.create(trainee);
        assertNotNull(trainee.getId());
        verify(storage).put(trainee.getId(), trainee);
    }

    @Test
    void testFindById() {
        UUID id = UUID.randomUUID();
        Trainee trainee = new Trainee();
        when(storage.get(id)).thenReturn(trainee);
        Trainee result = traineeDao.findById(id);
        assertEquals(trainee, result);
    }

    @Test
    void testFindAll() {
        Trainee trainee1 = new Trainee();
        Trainee trainee2 = new Trainee();
        when(storage.values()).thenReturn(List.of(trainee1, trainee2));
        List<Trainee> result = traineeDao.findAll();
        assertEquals(2, result.size());
    }

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();
        traineeDao.delete(id);
        verify(storage).remove(id);
    }
}