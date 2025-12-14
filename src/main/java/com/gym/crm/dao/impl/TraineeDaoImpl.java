package com.gym.crm.dao.impl;

import com.gym.crm.dao.TraineeDao;
import com.gym.crm.entity.Trainee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Slf4j
@Repository
public class TraineeDaoImpl implements TraineeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Trainee create(Trainee entity) {
        log.trace("Creating trainee: {}", entity);
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Trainee findById(UUID id) {
        log.trace("Finding trainee by ID: {}", id);
        return entityManager.find(Trainee.class, id);
    }

    @Override
    public List<Trainee> findAll() {
        log.trace("Finding all trainees");
        return entityManager.createQuery("FROM Trainee", Trainee.class).getResultList();
    }

    @Override
    @Transactional
    public Trainee update(Trainee entity) {
        log.trace("Updating trainee: {}", entity);
        return entityManager.merge(entity);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        log.trace("Deleting trainee by ID: {}", id);
        Trainee trainee = findById(id);
        if (trainee != null) {
            entityManager.remove(trainee);
        }
    }

    @Override
    public Trainee findByUsername(String username) {
        log.trace("Finding trainee by username: {}", username);
        return entityManager.createQuery("SELECT t FROM Trainee t WHERE t.user.username = :username", Trainee.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}