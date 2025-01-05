package pl.wsb.fitnesstracker;

import org.springframework.transaction.annotation.Transactional;
import pl.wsb.fitnesstracker.statistics.api.Statistics;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.user.api.User;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public abstract class IntegrationTestBase {

    @Autowired
    private JpaRepository<User, Long> userRepository;

    @Autowired
    private JpaRepository<Training, Long> trainingRepository;

    @Autowired
    private JpaRepository<Statistics, Long> statisticsRepository;

    @AfterEach
    void cleanUpDB() {
        statisticsRepository.deleteAll();
        trainingRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Before
    public void setUp() {
        statisticsRepository.deleteAll();
        trainingRepository.deleteAll();
        userRepository.deleteAll();
    }

    protected Training persistTraining(Training training) {
        return trainingRepository.save(training);
    }

    protected Statistics persistStatistic(Statistics statistics) {
        return statisticsRepository.save(statistics);
    }

    protected User existingUser(User user) {
        return userRepository.save(user);
    }

    protected List<User> getAllUsers() {
        return userRepository.findAll();
    }

    protected List<Training> createAllTrainings(List<Training> trainings) {
        trainings.forEach(training -> trainingRepository.save(training));
        return trainings;
    }

    protected List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    protected List<Statistics> getAllStatistics() {
        return statisticsRepository.findAll();
    }

}
