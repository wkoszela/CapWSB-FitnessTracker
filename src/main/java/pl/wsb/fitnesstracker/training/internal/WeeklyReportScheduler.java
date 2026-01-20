package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.mail.api.EmailDto;
import pl.wsb.fitnesstracker.mail.api.EmailSender;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingRepository;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.internal.UserRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class WeeklyReportScheduler {

    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;
    private final EmailSender emailSender;

    @Scheduled(initialDelay = 5000, fixedRate = 60000)
    public void generateWeeklyReports() {
        log.info("Inicjacja generowania raportów dla wszystkich użytkowników...");

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -7);
        Date lastWeek = cal.getTime();

        List<User> allUsers = userRepository.findAll();

        for (User user : allUsers) {
            // Pobieramy treningi z ostatniego tygodnia
            List<Training> weeklyTrainings = trainingRepository.findByUserIdAndStartTimeAfter(user.getId(), lastWeek);

            // USUNIĘTO IF-a: Wywołujemy raport ZAWSZE
            printReport(user, weeklyTrainings);

            // Raport e-mail (wszystkie treningi)
            List<Training> allUserTrainings = trainingRepository.findByUserId(user.getId());
            sendSummaryEmail(user, allUserTrainings.size());
        }
        log.info("Zakończono sprawdzanie użytkowników.");
    }

    private void printReport(User user, List<Training> trainings) {
        // Jeśli lista jest pusta, dystans wyniesie 0.0
        double totalDistance = trainings.stream()
                .mapToDouble(Training::getDistance)
                .sum();

        System.out.println("======= RAPORT SYSTEMOWY =======");
        System.out.println("Użytkownik: " + user.getFirstName() + " " + user.getLastName());
        System.out.println("E-mail: " + user.getEmail());

        if (trainings.isEmpty()) {
            System.out.println("STATUS: BRAK TRENINGÓW W TYM TYGODNIU");
        } else {
            System.out.println("Liczba treningów (7 dni): " + trainings.size());
            System.out.println("Suma dystansu: " + String.format("%.2f", totalDistance) + " km");
        }

        System.out.println("================================");
    }

    private void sendSummaryEmail(User user, int totalCount) {
        String subject = "Twoje podsumowanie treningowe";
        String content = "Witaj " + user.getFirstName() + "! Łącznie masz u nas " + totalCount + " treningów.";

        try {
            emailSender.send(new EmailDto(user.getEmail(), subject, content));
            log.info("Wysłano powiadomienie do: {}", user.getEmail());
        } catch (Exception e) {
            log.error("Błąd wysyłki do {}: {}", user.getEmail(), e.getMessage());
        }
    }
}