package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.mail.api.EmailDto; // DODAJ
import pl.wsb.fitnesstracker.mail.api.EmailSender; // DODAJ
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
    private final EmailSender emailSender; // DODAJ

    //@Scheduled(cron = "0 0 0 * * MON") // Docelowo
    @Scheduled(initialDelay = 5000, fixedRate = 60000) // Do testów
    public void generateWeeklyReports() {
        log.info("Rozpoczynanie generowania raportów...");

        // 1. Obliczamy datę dla raportu tygodniowego w konsoli
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -7);
        Date lastWeek = cal.getTime();

        List<User> allUsers = userRepository.findAll();

        for (User user : allUsers) {
            // --- RAPORT DO KONSOLI ---
            List<Training> weeklyTrainings = trainingRepository.findByUserIdAndStartTimeAfter(user.getId(), lastWeek);
            if (!weeklyTrainings.isEmpty()) {
                printReport(user, weeklyTrainings);
            }

            // --- RAPORT E-MAIL (ŁĄCZNIE) ---
            List<Training> allUserTrainings = trainingRepository.findByUserId(user.getId());
            int totalTrainingsCount = allUserTrainings.size();

            sendSummaryEmail(user, totalTrainingsCount);
        }
        log.info("Zakończono generowanie raportów.");
    }

    private void sendSummaryEmail(User user, int totalCount) {
        String subject = "Twoje podsumowanie treningowe";
        String content = "Witaj " + user.getFirstName() + "!\n\n" +
                "Łącznie zarejestrowałeś już " + totalCount + " treningów. Tak trzymaj!";

        // Używamy Twojego interfejsu z EmailDto
        EmailDto emailDto = new EmailDto(user.getEmail(), subject, content);

        try {
            emailSender.send(emailDto);
            log.info("Wysłano e-mail do: {}", user.getEmail());
        } catch (Exception e) {
            log.error("Błąd wysyłki e-maila do {}: {}", user.getEmail(), e.getMessage());
        }
    }

    private void printReport(User user, List<Training> trainings) {
        double totalDistance = trainings.stream().mapToDouble(Training::getDistance).sum();
        System.out.println("======= RAPORT KONSOLA: " + user.getFirstName() + " " + user.getLastName() + " =======");
        System.out.println("Treningi w tym tygodniu: " + trainings.size());
        System.out.println("Suma dystansu: " + String.format("%.2f", totalDistance) + " km");
        System.out.println("=================================");
    }
}