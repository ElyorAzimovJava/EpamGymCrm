package com.gym.crm.dao.impl;

import com.gym.crm.entity.Trainee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TraineeDaoImplTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private TraineeDaoImpl traineeDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Trainee trainee = new Trainee();
        traineeDao.create(trainee);
        verify(entityManager).persist(trainee);
    }

    @Test
    void testFindById() {
        UUID traineeId = UUID.randomUUID();
        Trainee trainee = new Trainee();
        when(entityManager.find(Trainee.class, traineeId)).thenReturn(trainee);
        Trainee result = traineeDao.findById(traineeId);
        assertEquals(trainee, result);
    }

    @Test
    void testFindAll() {
        TypedQuery<Trainee> query = mock(TypedQuery.class);
        when(entityManager.createQuery("FROM Trainee", Trainee.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.emptyList());
        List<Trainee> result = traineeDao.findAll();
        assertEquals(0, result.size());
    }

    @Test
    void testUpdate() {
        Trainee trainee = new Trainee();
        traineeDao.update(trainee);
        verify(entityManager).merge(trainee);
    }

    @Test
    void testDelete() {
        UUID traineeId = UUID.randomUUID();
        Trainee trainee = new Trainee();
        when(entityManager.find(Trainee.class, traineeId)).thenReturn(trainee);
        traineeDao.delete(traineeId);
        verify(entityManager).remove(trainee);
    }

    @Test
    void testFindByUsername() {
        String username = "test.user";
        Trainee trainee = new Trainee();
        TypedQuery<Trainee> query = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT t FROM Trainee t WHERE t.user.username = :username", Trainee.class)).thenReturn(query);
        when(query.setParameter("username", username)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(trainee);
        Trainee result = traineeDao.findByUsername(username);
        assertEquals(trainee, result);
    }
}