package pl.wsb.fitnesstracker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.trainings.api.Trainings;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class IntegrationTestBase {

    @Autowired
    private JpaRepository<User, Long> userRepository;

    @Autowired
    private JpaRepository<Trainings, Long> trainingsRepository;

    @AfterEach
    void cleanUp() {
        cleanDatabase();

    }

    private void cleanDatabase() {
        trainingsRepository.deleteAll();
        userRepository.deleteAll();
    }

    @BeforeEach
    public void setUp() {

        cleanDatabase();

    }

    protected Trainings persistTraining(Trainings trainings) {
        return trainingsRepository.save(trainings);
    }

    protected User existingUser(User user) {

        return userRepository.save(user);
    }

    protected List<User> getAllUsers() {
        return userRepository.findAll();
    }

    protected List<Trainings> createAllTrainings(List<Trainings> trainings) {

        trainings.forEach(training -> trainingsRepository.save(training));
        return trainings;
    }

    protected List<Trainings> getAllTrainings() {
        return trainingsRepository.findAll();
    }


}
