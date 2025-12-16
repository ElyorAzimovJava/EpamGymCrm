package com.gym.crm.dao.impl;

import com.gym.crm.dao.TrainerDao;
import com.gym.crm.entity.Trainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Slf4j
@Repository
public class TrainerDaoImpl implements TrainerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Trainer create(Trainer entity) {
        log.trace("Creating trainer: {}", entity);
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Trainer findById(UUID id) {
        log.trace("Finding trainer by ID: {}", id);
        return entityManager.find(Trainer.class, id);
    }

    @Override
    public List<Trainer> findAll() {
        log.trace("Finding all trainers");
        return entityManager.createQuery("FROM Trainer", Trainer.class).getResultList();
    }

    @Override
    @Transactional
    public Trainer update(Trainer entity) {
        log.trace("Updating trainer: {}", entity);
        return entityManager.merge(entity);
    }

    @Override
    public void delete(UUID id) {
        log.trace("Deleting trainer by ID: {}", id);
        Trainer trainer = entityManager.find(Trainer.class, id);
        if (trainer != null) {
            entityManager.remove(trainer);
        }
    }

    @Override
    public Trainer findByUsername(String username) {
        log.trace("Finding trainer by username: {}", username);
        return entityManager.createQuery("SELECT t FROM Trainer t WHERE t.user.username = :username", Trainer.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public List<Trainer> getUnassignedTrainers(String traineeUsername) {
        TypedQuery<Trainer> query = entityManager.createQuery(
                "SELECT t FROM Trainer t WHERE t.id NOT IN (" +
                        "SELECT tr.trainer.id FROM Training tr WHERE tr.trainee.user.username = :traineeUsername)", Trainer.class);
        query.setParameter("traineeUsername", traineeUsername);
        return query.getResultList();
    }
}