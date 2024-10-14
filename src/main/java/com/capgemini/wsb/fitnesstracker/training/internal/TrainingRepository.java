package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Retrieves a {@link List<TrainingDto>} of all trainings assigned to a user with specified user id
     *
     * @param userId id of the user
     * @return {@link List<TrainingDto>} of trainings assigned to specified user
     */
    List<Training> findByUserId(Long userId);
}
