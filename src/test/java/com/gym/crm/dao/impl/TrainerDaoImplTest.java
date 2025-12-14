package com.gym.crm.dao.impl;

import com.gym.crm.entity.Trainer;
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

class TrainerDaoImplTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private TrainerDaoImpl trainerDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Trainer trainer = new Trainer();
        trainerDao.create(trainer);
        verify(entityManager).persist(trainer);
    }

    @Test
    void testFindById() {
        UUID trainerId = UUID.randomUUID();
        Trainer trainer = new Trainer();
        when(entityManager.find(Trainer.class, trainerId)).thenReturn(trainer);
        Trainer result = trainerDao.findById(trainerId);
        assertEquals(trainer, result);
    }

    @Test
    void testFindAll() {
        TypedQuery<Trainer> query = mock(TypedQuery.class);
        when(entityManager.createQuery("FROM Trainer", Trainer.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.emptyList());
        List<Trainer> result = trainerDao.findAll();
        assertEquals(0, result.size());
    }

    @Test
    void testUpdate() {
        Trainer trainer = new Trainer();
        trainerDao.update(trainer);
        verify(entityManager).merge(trainer);
    }

    @Test
    void testDelete() {
        UUID trainerId = UUID.randomUUID();
        Trainer trainer = new Trainer();
        when(entityManager.find(Trainer.class, trainerId)).thenReturn(trainer);
        trainerDao.delete(trainerId);
        verify(entityManager).remove(trainer);
    }

    @Test
    void testFindByUsername() {
        String username = "test.user";
        Trainer trainer = new Trainer();
        TypedQuery<Trainer> query = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT t FROM Trainer t WHERE t.user.username = :username", Trainer.class)).thenReturn(query);
        when(query.setParameter("username", username)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(trainer);
        Trainer result = trainerDao.findByUsername(username);
        assertEquals(trainer, result);
    }

    @Test
    void testGetUnassignedTrainers() {
        String traineeUsername = "test.trainee";
        TypedQuery<Trainer> query = mock(TypedQuery.class);
        when(entityManager.createQuery(
                "SELECT t FROM Trainer t WHERE t.id NOT IN (" +
                        "SELECT tr.trainer.id FROM Training tr WHERE tr.trainee.user.username = :traineeUsername)", Trainer.class)).thenReturn(query);
        when(query.setParameter("traineeUsername", traineeUsername)).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.emptyList());
        List<Trainer> result = trainerDao.getUnassignedTrainers(traineeUsername);
        assertEquals(0, result.size());
    }
}