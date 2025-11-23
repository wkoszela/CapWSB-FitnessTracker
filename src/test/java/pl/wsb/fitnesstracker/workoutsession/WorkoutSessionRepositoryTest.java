package pl.wsb.fitnesstracker.workoutsession;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(WorkoutSessionRepository.class)
class WorkoutSessionRepositoryTest {

    @Autowired
    private WorkoutSessionRepository workoutSessionRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void shouldSaveWorkoutSession() {
        // given
        User user = new User("John", "Doe", LocalDate.now(), "john.doe@example.com");
        entityManager.persist(user);
        Training training = new Training(user, new Date(), new Date(), ActivityType.RUNNING, 10.0, 5.0);
        entityManager.persist(training);
        WorkoutSession session = new WorkoutSession(training, new Date(), 10.0, 20.0, 30.0, 40.0, 100.0);

        // when
        WorkoutSession saved = workoutSessionRepository.save(session);

        // then
        assertThat(saved.getId()).isNotNull();
        WorkoutSession found = entityManager.find(WorkoutSession.class, saved.getId());
        assertThat(found).isNotNull();
    }

    @Test
    void shouldFindById() {
        // given
        User user = new User("Jane", "Doe", LocalDate.now(), "jane.doe@example.com");
        entityManager.persist(user);
        Training training = new Training(user, new Date(), new Date(), ActivityType.CYCLING, 20.0, 15.0);
        entityManager.persist(training);
        WorkoutSession session = new WorkoutSession(training, new Date(), 11.0, 21.0, 31.0, 41.0, 200.0);
        entityManager.persist(session);

        // when
        Optional<WorkoutSession> found = workoutSessionRepository.findById(session.getId());

        // then
        assertThat(found).isPresent();
        assertThat(found.get().getId()).isEqualTo(session.getId());
    }

    @Test
    void shouldFindAll() {
        // given
        User user = new User("Jack", "Doe", LocalDate.now(), "jack.doe@example.com");
        entityManager.persist(user);
        Training training = new Training(user, new Date(), new Date(), ActivityType.WALKING, 5.0, 3.0);
        entityManager.persist(training);
        WorkoutSession session1 = new WorkoutSession(training, new Date(), 12.0, 22.0, 32.0, 42.0, 300.0);
        WorkoutSession session2 = new WorkoutSession(training, new Date(), 13.0, 23.0, 33.0, 43.0, 400.0);
        entityManager.persist(session1);
        entityManager.persist(session2);

        // when
        List<WorkoutSession> all = workoutSessionRepository.findAll();

        // then
        assertThat(all).hasSizeGreaterThanOrEqualTo(2);
    }

    @Test
    void shouldDelete() {
        // given
        User user = new User("Jill", "Doe", LocalDate.now(), "jill.doe@example.com");
        entityManager.persist(user);
        Training training = new Training(user, new Date(), new Date(), ActivityType.SWIMMING, 2.0, 2.0);
        entityManager.persist(training);
        WorkoutSession session = new WorkoutSession(training, new Date(), 14.0, 24.0, 34.0, 44.0, 500.0);
        entityManager.persist(session);
        Long id = session.getId();

        // when
        workoutSessionRepository.delete(session);

        // then
        WorkoutSession found = entityManager.find(WorkoutSession.class, id);
        assertThat(found).isNull();
    }
}
