package com.capgemini.wsb.fitnesstracker;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class IntegrationTestBase {

    @Autowired
    private JpaRepository<User, Long> userRepository;

    @Autowired
    private JpaRepository<Training, Long> trainingRepository;

    @AfterEach
    void cleanUpDB() {
        trainingRepository.deleteAll();
        userRepository.deleteAll();

    }

    @Before
    public void setUp() {
        trainingRepository.deleteAll();
        userRepository.deleteAll();

    }

    protected Training persistTraining(Training training) {
        return trainingRepository.save(training);
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


}
