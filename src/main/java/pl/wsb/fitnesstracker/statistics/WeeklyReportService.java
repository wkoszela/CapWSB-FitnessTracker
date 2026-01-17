package pl.wsb.fitnesstracker.statistics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.mail.api.EmailDto;
import pl.wsb.fitnesstracker.mail.api.EmailSender;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.internal.TrainingRepository;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.internal.UserRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeeklyReportService {

    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;
    private final EmailSender emailSender;

    public void generateWeeklyReport() {

        LocalDate startOfWeek = LocalDate.now()
                .with(DayOfWeek.MONDAY)
                .minusWeeks(1);
        LocalDate endOfWeek = startOfWeek.plusWeeks(1);

        List<User> users = userRepository.findAll();
        List<Training> allTrainings = trainingRepository.findAll();

        for (User user : users) {

            // zad. 1
            long weeklyTrainingsCount = allTrainings.stream()
                    .filter(training -> training.getUser().getId().equals(user.getId()))
                    .filter(training -> {
                        LocalDate trainingDate = training.getStartTime()
                                .toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();
                        return !trainingDate.isBefore(startOfWeek)
                                && trainingDate.isBefore(endOfWeek);
                    })
                    .count();

            log.info(
                    "Użytkownik {} {} - liczba treningów w tygodniu {} – {}: {}",
                    user.getFirstName(),
                    user.getLastName(),
                    startOfWeek,
                    endOfWeek.minusDays(1),
                    weeklyTrainingsCount);

            // zad. 2
            long totalTrainingsCount = allTrainings.stream()
                    .filter(training -> training.getUser().getId().equals(user.getId()))
                    .count();

            EmailDto email = new EmailDto(
                    user.getEmail(),
                    "Podsumowanie treningów",
                    "Masz zarejestrowanych łącznie " + totalTrainingsCount + " treningów.");

            emailSender.send(email);
        }
    }
}
