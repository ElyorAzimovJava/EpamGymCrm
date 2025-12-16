package com.gym.crm.dao.impl;

import com.gym.crm.entity.Training;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TrainingDaoImplTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private TrainingDaoImpl trainingDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Training training = new Training();
        trainingDao.create(training);
        verify(entityManager).persist(training);
    }

    @Test
    void testFindById() {
        UUID trainingId = UUID.randomUUID();
        Training training = new Training();
        when(entityManager.find(Training.class, trainingId)).thenReturn(training);
        Training result = trainingDao.findById(trainingId);
        assertEquals(training, result);
    }

    @Test
    void testFindAll() {
        TypedQuery<Training> query = mock(TypedQuery.class);
        when(entityManager.createQuery("FROM Training", Training.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.emptyList());
        List<Training> result = trainingDao.findAll();
        assertEquals(0, result.size());
    }

    @Test
    void testUpdate() {
        Training training = new Training();
        trainingDao.update(training);
        verify(entityManager).merge(training);
    }

    @Test
    void testDelete() {
        UUID trainingId = UUID.randomUUID();
        Training training = new Training();
        when(entityManager.find(Training.class, trainingId)).thenReturn(training);
        trainingDao.delete(trainingId);
        verify(entityManager).remove(training);
    }

    @Test
    void testFindTraineeTrainings() {
        CriteriaBuilder cb = mock(CriteriaBuilder.class);
        CriteriaQuery<Training> cq = mock(CriteriaQuery.class);
        Root<Training> root = mock(Root.class);
        Path<Object> path = mock(Path.class);
        Predicate predicate = mock(Predicate.class);
        TypedQuery<Training> query = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(cb);
        when(cb.createQuery(Training.class)).thenReturn(cq);
        when(cq.from(Training.class)).thenReturn(root);
        when(root.get(any(String.class))).thenReturn(path);
        when(path.get(any(String.class))).thenReturn(path);
        when(cb.equal(path, "test.user")).thenReturn(predicate);
        when(entityManager.createQuery(cq)).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.emptyList());

        List<Training> result = trainingDao.findTraineeTrainings("test.user", null, null, null, null);

        assertEquals(0, result.size());
    }

    @Test
    void testFindTrainerTrainings() {
        CriteriaBuilder cb = mock(CriteriaBuilder.class);
        CriteriaQuery<Training> cq = mock(CriteriaQuery.class);
        Root<Training> root = mock(Root.class);
        Path<Object> path = mock(Path.class);
        Predicate predicate = mock(Predicate.class);
        TypedQuery<Training> query = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(cb);
        when(cb.createQuery(Training.class)).thenReturn(cq);
        when(cq.from(Training.class)).thenReturn(root);
        when(root.get(any(String.class))).thenReturn(path);
        when(path.get(any(String.class))).thenReturn(path);
        when(cb.equal(path, "test.user")).thenReturn(predicate);
        when(entityManager.createQuery(cq)).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.emptyList());

        List<Training> result = trainingDao.findTrainerTrainings("test.user", null, null, null);

        assertEquals(0, result.size());
    }
}