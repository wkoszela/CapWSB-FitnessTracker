package pl.wsb.fitnesstracker.report;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingRepository;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.internal.UserRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static java.util.UUID.randomUUID;
import static java.util.stream.Collectors.toMap;

@SpringBootTest
@Transactional
class TrainingReportServiceTest {

    private static final ZoneId WARSAW_ZONE = ZoneId.of("Europe/Warsaw");

    @Autowired
    private TrainingReportService reportService;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldGenerateWeeklySummaryForEachUser() {
        LocalDate weekStart = LocalDate.of(2024, 1, 8);
        LocalDate weekEnd = LocalDate.of(2024, 1, 14);

        User userWithTraining = userRepository.save(new User(
                randomUUID().toString(),
                randomUUID().toString(),
                LocalDate.of(1990, 1, 1),
                "user1@domain.com"));
        User anotherUser = userRepository.save(new User(
                randomUUID().toString(),
                randomUUID().toString(),
                LocalDate.of(1992, 2, 2),
                "user2@domain.com"));
        User userWithoutTraining = userRepository.save(new User(
                randomUUID().toString(),
                randomUUID().toString(),
                LocalDate.of(1994, 3, 3),
                "user3@domain.com"));

        trainingRepository.save(new Training(
                userWithTraining,
                toDate(LocalDate.of(2024, 1, 10), LocalTime.of(7, 0)),
                toDate(LocalDate.of(2024, 1, 10), LocalTime.of(8, 0)),
                ActivityType.RUNNING,
                10.0,
                8.0));
        trainingRepository.save(new Training(
                anotherUser,
                toDate(LocalDate.of(2024, 1, 14), LocalTime.of(18, 0)),
                toDate(LocalDate.of(2024, 1, 14), LocalTime.of(19, 0)),
                ActivityType.CYCLING,
                20.0,
                17.0));
        trainingRepository.save(new Training(
                userWithTraining,
                toDate(LocalDate.of(2024, 1, 20), LocalTime.of(7, 0)),
                toDate(LocalDate.of(2024, 1, 20), LocalTime.of(8, 0)),
                ActivityType.WALKING,
                5.0,
                5.0));

        List<WeeklyTrainingSummary> summaries = reportService.generateWeeklySummary(weekStart, weekEnd);

        Map<Long, WeeklyTrainingSummary> summaryByUser = summaries.stream()
                .collect(toMap(WeeklyTrainingSummary::userId, Function.identity()));

        assertThat(summaries).hasSize(3);
        assertThat(summaryByUser.get(userWithTraining.getId()).trainingsCount()).isEqualTo(1);
        assertThat(summaryByUser.get(anotherUser.getId()).trainingsCount()).isEqualTo(1);
        assertThat(summaryByUser.get(userWithoutTraining.getId()).trainingsCount()).isZero();
    }

    private static Date toDate(LocalDate date, LocalTime time) {
        return Date.from(ZonedDateTime.of(date, time, WARSAW_ZONE).toInstant());
    }
}
