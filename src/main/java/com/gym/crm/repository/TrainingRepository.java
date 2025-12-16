package com.gym.crm.repository;

import com.gym.crm.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TrainingRepository extends JpaRepository<Training, UUID> {
    @Query("select t from Training t where t.trainee.user.username = :username " +
            "and (:fromDate is null or t.trainingDate >= :fromDate) " +
            "and (:toDate is null or t.trainingDate <= :toDate) " +
            "and (:trainerName is null or t.trainer.user.username = :trainerName) " +
            "and (:trainingType is null or t.trainingType.trainingTypeName = :trainingType)")
    List<Training> findTraineeTrainings(@Param("username") String username,
                                    @Param("fromDate") LocalDate fromDate,
                                    @Param("toDate") LocalDate toDate,
                                    @Param("trainerName") String trainerName,
                                    @Param("trainingType") String trainingType);

    @Query("select t from Training t where t.trainer.user.username = :username " +
            "and (:fromDate is null or t.trainingDate >= :fromDate) " +
            "and (:toDate is null or t.trainingDate <= :toDate) " +
            "and (:traineeName is null or t.trainee.user.username = :traineeName)")
    List<Training> findTrainerTrainings(@Param("username") String username,
                                    @Param("fromDate") LocalDate fromDate,
                                    @Param("toDate") LocalDate toDate,
                                    @Param("traineeName") String traineeName);
}