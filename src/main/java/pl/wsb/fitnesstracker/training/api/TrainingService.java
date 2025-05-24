package pl.wsb.fitnesstracker.training.api;


public interface TrainingService {

    /**
     * Deletes all trainings associated with a specific user ID.
     * This operation is transactional to ensure data integrity.
     *
     * @param userId the ID of the user whose trainings should be deleted
     */
     void deleteTrainingsByUserId(Long userId);
}
