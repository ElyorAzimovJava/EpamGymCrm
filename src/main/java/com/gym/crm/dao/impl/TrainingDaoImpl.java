package com.gym.crm.dao.impl;

import com.gym.crm.dao.TrainingDao;
import com.gym.crm.entity.Training;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Repository
public class TrainingDaoImpl implements TrainingDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Training create(Training entity) {
        log.trace("Creating training: {}", entity);
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Training findById(UUID id) {
        log.trace("Finding training by ID: {}", id);
        return entityManager.find(Training.class, id);
    }

    @Override
    public List<Training> findAll() {
        log.trace("Finding all trainings");
        return entityManager.createQuery("FROM Training", Training.class).getResultList();
    }

    @Override
    public Training update(Training entity) {
        log.trace("Updating training: {}", entity);
        return entityManager.merge(entity);
    }

    @Override
    public void delete(UUID id) {
        log.trace("Deleting training by ID: {}", id);
        Training training = entityManager.find(Training.class, id);
        if (training != null) {
            entityManager.remove(training);
        }
    }

    public List<Training> findTraineeTrainings(String username, LocalDate periodFrom, LocalDate periodTo, String trainerName, String trainingType) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Training> cq = cb.createQuery(Training.class);
        Root<Training> training = cq.from(Training.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(training.get("trainee").get("user").get("username"), username));

        if (periodFrom != null) {
            predicates.add(cb.greaterThanOrEqualTo(training.get("trainingDate"), periodFrom.atStartOfDay()));
        }
        if (periodTo != null) {
            predicates.add(cb.lessThanOrEqualTo(training.get("trainingDate"), periodTo.plusDays(1).atStartOfDay()));
        }
        if (trainerName != null && !trainerName.isEmpty()) {
            predicates.add(cb.equal(training.get("trainer").get("user").get("firstName"), trainerName));
        }
        if (trainingType != null && !trainingType.isEmpty()) {
            predicates.add(cb.equal(training.get("trainingType").get("trainingTypeName"), trainingType));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<Training> findTrainerTrainings(String username, LocalDate periodFrom, LocalDate periodTo, String traineeName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Training> cq = cb.createQuery(Training.class);
        Root<Training> training = cq.from(Training.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(training.get("trainer").get("user").get("username"), username));

        if (periodFrom != null) {
            predicates.add(cb.greaterThanOrEqualTo(training.get("trainingDate"), periodFrom.atStartOfDay()));
        }
        if (periodTo != null) {
            predicates.add(cb.lessThanOrEqualTo(training.get("trainingDate"), periodTo.plusDays(1).atStartOfDay()));
        }
        if (traineeName != null && !traineeName.isEmpty()) {
            predicates.add(cb.equal(training.get("trainee").get("user").get("firstName"), traineeName));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }
}