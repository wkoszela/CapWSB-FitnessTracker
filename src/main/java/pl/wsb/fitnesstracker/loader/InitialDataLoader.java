package pl.wsb.fitnesstracker.loader;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.wsb.fitnesstracker.trainings.api.Trainings;
import pl.wsb.fitnesstracker.trainings.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.now;
import static java.util.Objects.isNull;

/**
 * Sample init data loader. If the application is run with `loadInitialData` profile, then on application startup it will fill the database with dummy data,
 * for the manual testing purposes. Loader is triggered by {@link ContextRefreshedEvent } event
 */
@Component
@Profile("loadInitialData")
@Slf4j
@ToString
@RequiredArgsConstructor
class InitialDataLoader {

    private final JpaRepository<User, Long> userRepository;

    private final JpaRepository<Trainings, Long> trainingRepository;

    @EventListener
    @Transactional
    @SuppressWarnings({"squid:S1854", "squid:S1481", "squid:S1192", "unused"})
    public void loadInitialData(ContextRefreshedEvent event) {
        verifyDependenciesAutowired();

        log.info("Loading initial data to the database");

        List<User> sampleUserList = generateSampleUsers();
        List<Trainings> sampleTrainingsList = generateTrainingData(sampleUserList);


        log.info("Finished loading initial data");
    }

    private User generateUser(String name, String lastName, int age) {
        User user = new User(name,
                lastName,
                now().minusYears(age),
                "%s.%s@domain.com".formatted(name, lastName));
        return userRepository.save(user);
    }

    private List<User> generateSampleUsers() {
        List<User> users = new ArrayList<>();

        users.add(generateUser("Emma", "Johnson", 28));
        users.add(generateUser("Ethan", "Taylor", 51));
        users.add(generateUser("Olivia", "Davis", 76));
        users.add(generateUser("Daniel", "Thomas", 34));
        users.add(generateUser("Sophia", "Baker", 49));
        users.add(generateUser("Liam", "Jones", 23));
        users.add(generateUser("Ava", "Williams", 21));
        users.add(generateUser("Noah", "Miller", 39));
        users.add(generateUser("Grace", "Anderson", 33));
        users.add(generateUser("Oliver", "Swift", 29));

        return users;
    }

    private List<Trainings> generateTrainingData(List<User> users) {
        List<Trainings> trainingsData = new ArrayList<>();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Trainings trainings1 = new Trainings(users.get(0),
                    sdf.parse("2024-01-19 08:00:00"),
                    sdf.parse("2024-01-19 09:30:00"),
                    ActivityType.RUNNING,
                    10.5,
                    8.2);
            Trainings trainings2 = new Trainings(users.get(1),
                    sdf.parse("2024-01-18 15:30:00"),
                    sdf.parse("2024-01-18 17:00:00"),
                    ActivityType.CYCLING,
                    25.0,
                    18.5);
            Trainings trainings3 = new Trainings(users.get(2),
                    sdf.parse("2024-01-17 07:45:00"),
                    sdf.parse("2024-01-17 09:00:00"),
                    ActivityType.WALKING,
                    5.2,
                    5.8);
            Trainings trainings4 = new Trainings(users.get(3),
                    sdf.parse("2024-01-16 18:00:00"),
                    sdf.parse("2024-01-16 19:30:00"),
                    ActivityType.RUNNING,
                    12.3,
                    9.0);
            Trainings trainings5 = new Trainings(users.get(4),
                    sdf.parse("2024-01-15 12:30:00"),
                    sdf.parse("2024-01-15 13:45:00"),
                    ActivityType.CYCLING,
                    18.7,
                    15.3);
            Trainings trainings6 = new Trainings(users.get(5),
                    sdf.parse("2024-01-14 09:00:00"),
                    sdf.parse("2024-01-14 10:15:00"),
                    ActivityType.WALKING,
                    3.5,
                    4.0);
            Trainings trainings7 = new Trainings(users.get(6),
                    sdf.parse("2024-01-13 16:45:00"),
                    sdf.parse("2024-01-13 18:30:00"),
                    ActivityType.RUNNING,
                    15.0,
                    10.8);
            Trainings trainings8 = new Trainings(users.get(7),
                    sdf.parse("2024-01-12 11:30:00"),
                    sdf.parse("2024-01-12 12:45:00"),
                    ActivityType.CYCLING,
                    22.5,
                    17.2);
            Trainings trainings9 = new Trainings(users.get(8),
                    sdf.parse("2024-01-11 07:15:00"),
                    sdf.parse("2024-01-11 08:30:00"),
                    ActivityType.WALKING,
                    4.2,
                    4.5);
            Trainings trainings10 = new Trainings(users.get(9),
                    sdf.parse("2024-01-10 14:00:00"),
                    sdf.parse("2024-01-10 15:15:00"),
                    ActivityType.RUNNING,
                    11.8,
                    8.5);

            trainingsData.add(trainings1);
            trainingsData.add(trainings2);
            trainingsData.add(trainings3);
            trainingsData.add(trainings4);
            trainingsData.add(trainings5);
            trainingsData.add(trainings6);
            trainingsData.add(trainings7);
            trainingsData.add(trainings8);
            trainingsData.add(trainings9);
            trainingsData.add(trainings10);

            trainingRepository.saveAll(trainingsData);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return trainingsData;
    }

    private void verifyDependenciesAutowired() {
        if (isNull(userRepository)) {
            throw new IllegalStateException("Initial data loader was not autowired correctly " + this);
        }
    }

}
