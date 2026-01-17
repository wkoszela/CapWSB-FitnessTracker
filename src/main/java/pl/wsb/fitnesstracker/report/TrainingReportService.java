package pl.wsb.fitnesstracker.report;

import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingRepository;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

/**
 * Service generating weekly training summaries for users.
 */
@Service
class TrainingReportService {

    private final TrainingRepository trainingRepository;
    private final UserProvider userProvider;
    private final Clock clock;

    TrainingReportService(TrainingRepository trainingRepository, UserProvider userProvider, Clock clock) {
        this.trainingRepository = trainingRepository;
        this.userProvider = userProvider;
        this.clock = clock;
    }

    /**
     * Generates weekly summaries for every user.
     *
     * @param weekStart start date (inclusive)
     * @param weekEnd   end date (inclusive)
     * @return list of weekly summaries per user
     */
    List<WeeklyTrainingSummary> generateWeeklySummary(LocalDate weekStart, LocalDate weekEnd) {
        List<User> users = userProvider.findAllUsers();
        List<Training> trainings = trainingRepository.findAll();

        return users.stream()
                .map(user -> {
                    List<Training> weeklyTrainings = trainings.stream()
                            .filter(training -> isTrainingForUserInRange(training, user, weekStart, weekEnd))
                            .toList();
                    return new WeeklyTrainingSummary(
                            user.getId(),
                            user.getEmail(),
                            weeklyTrainings.size(),
                            weeklyTrainings);
                })
                .toList();
    }

    private boolean isTrainingForUserInRange(Training training, User user, LocalDate weekStart, LocalDate weekEnd) {
        if (training.getUser() == null || user.getId() == null) {
            return false;
        }
        if (!user.getId().equals(training.getUser().getId())) {
            return false;
        }
        LocalDate trainingDate = training.getStartTime()
                .toInstant()
                .atZone(clock.getZone())
                .toLocalDate();
        return !trainingDate.isBefore(weekStart) && !trainingDate.isAfter(weekEnd);
    }
}
