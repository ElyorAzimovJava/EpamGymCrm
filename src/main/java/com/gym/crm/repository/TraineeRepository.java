package com.gym.crm.repository;

import com.gym.crm.entity.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, UUID> {
    Trainee findByUserUsername(String username);
}