package pl.wsb.fitnesstracker.training.internal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class TrainingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Finds all trainings for a specific user using JPQL query.
     *
     * @param user the user to find trainings for
     * @return list of trainings for the user
     */
    public List<Training> findByUser(User user) {
        String jpql = "SELECT t FROM Training t WHERE t.user = :user ORDER BY t.startTime DESC";
        TypedQuery<Training> query = entityManager.createQuery(jpql, Training.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

    /**
     * Finds trainings by activity type using JPQL query.
     *
     * @param activityType the activity type to search for
     * @return list of trainings with the specified activity type
     */
    public List<Training> findByActivityType(ActivityType activityType) {
        String jpql = "SELECT t FROM Training t WHERE t.activityType = :activityType ORDER BY t.startTime DESC";
        TypedQuery<Training> query = entityManager.createQuery(jpql, Training.class);
        query.setParameter("activityType", activityType);
        return query.getResultList();
    }

    /**
     * Finds training by ID using native SQL query.
     *
     * @param id the training ID
     * @return optional containing the training if found
     */
    public Optional<Training> findByIdNative(Long id) {
        String sql = "SELECT * FROM trainings WHERE id = :id";
        jakarta.persistence.Query query = entityManager.createNativeQuery(sql, Training.class);
        query.setParameter("id", id);
        try {
            Training training = (Training) query.getSingleResult();
            return Optional.of(training);
        } catch (jakarta.persistence.NoResultException e) {
            return Optional.empty();
        }
    }

    /**
     * Persists a training entity.
     *
     * @param training the training to persist
     * @return the persisted training
     */
    public Training save(Training training) {
        if (training.getId() == null) {
            entityManager.persist(training);
            return training;
        } else {
            return entityManager.merge(training);
        }
    }

    /**
     * Finds training by ID.
     *
     * @param id the training ID
     * @return optional containing the training if found
     */
    public Optional<Training> findById(Long id) {
        Training training = entityManager.find(Training.class, id);
        return Optional.ofNullable(training);
    }

    /**
     * Deletes a training entity.
     *
     * @param training the training to delete
     */
    public void delete(Training training) {
        entityManager.remove(entityManager.contains(training) ? training : entityManager.merge(training));
    }
}

