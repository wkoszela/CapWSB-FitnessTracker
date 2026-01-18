package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingRepository;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Serwis odpowiedzialny za generowanie raportów treningowych.
 */
@Service
@RequiredArgsConstructor
@Slf4j
class TrainingReportService {

    private final TrainingRepository trainingRepository;
    private final UserProvider userProvider;

    /**
     * Generuje raport treningów z ostatniego tygodnia dla wszystkich użytkowników.
     *
     * @return mapa użytkowników wraz z ich treningami z ostatniego tygodnia
     */
    public Map<User, List<Training>> generateWeeklyReport() {
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);
        Date weekAgoDate = Date.from(oneWeekAgo.atZone(ZoneId.systemDefault()).toInstant());

        List<Training> recentTrainings = trainingRepository.findByEndTimeAfter(weekAgoDate);

        log.info("Generowanie raportu tygodniowego. Znaleziono {} treningów z ostatniego tygodnia",
                recentTrainings.size());

        return recentTrainings.stream()
                .collect(Collectors.groupingBy(Training::getUser));
    }

    /**
     * Generuje raport z całkowitą liczbą treningów dla każdego użytkownika.
     *
     * @return mapa użytkowników wraz z całkowitą liczbą ich treningów
     */
    public Map<User, Long> generateTotalTrainingCountReport() {
        List<User> allUsers = userProvider.findAllUsers();

        return allUsers.stream()
                .collect(Collectors.toMap(
                        user -> user,
                        user -> (long) trainingRepository.findByUserId(user.getId()).size()));
    }

    /**
     * Formatuje raport tygodniowy do postaci tekstowej dla wyświetlenia w konsoli.
     *
     * @param weeklyReport mapa użytkownik -> lista treningów
     * @return sformatowany raport jako tekst
     */
    public String formatWeeklyReportForConsole(Map<User, List<Training>> weeklyReport) {
        StringBuilder report = new StringBuilder();
        report.append("========================================\n");
        report.append("   RAPORT TYGODNIOWY TRENINGÓW\n");
        report.append("========================================\n\n");

        if (weeklyReport.isEmpty()) {
            report.append("Brak treningów w ostatnim tygodniu.\n");
        } else {
            weeklyReport.forEach((user, trainings) -> {
                report.append(String.format("Użytkownik: %s %s (ID: %d)\n",
                        user.getFirstName(), user.getLastName(), user.getId()));
                report.append(String.format("Email: %s\n", user.getEmail()));
                report.append(String.format("Liczba treningów: %d\n", trainings.size()));
                report.append("Treningi:\n");

                trainings.forEach(training -> {
                    report.append(String.format("  - %s | Dystans: %.2f km | Prędkość: %.2f km/h | Data: %s\n",
                            training.getActivityType(),
                            training.getDistance(),
                            training.getAverageSpeed(),
                            training.getEndTime()));
                });

                report.append("\n");
            });
        }

        report.append("========================================\n");
        return report.toString();
    }

    /**
     * Formatuje raport całkowitej liczby treningów dla wysyłki emailem.
     *
     * @param user           użytkownik, dla którego generowany jest raport
     * @param totalTrainings całkowita liczba treningów użytkownika
     * @return sformatowany tekst emaila
     */
    public String formatEmailReport(User user, Long totalTrainings) {
        return String.format(
                "Witaj %s %s!\n\n" +
                        "To jest Twoje tygodniowe podsumowanie treningowe.\n\n" +
                        "Łączna liczba zarejestrowanych treningów: %d\n\n" +
                        "Tak trzymaj!\n\n" +
                        "Pozdrawiam,\n" +
                        "FitnessTracker Team",
                user.getFirstName(),
                user.getLastName(),
                totalTrainings);
    }
}
