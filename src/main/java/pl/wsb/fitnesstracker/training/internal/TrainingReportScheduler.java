package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.mail.api.EmailDto;
import pl.wsb.fitnesstracker.mail.api.EmailSender;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.List;
import java.util.Map;

/**
 * Komponent zawierający zaplanowane zadania cykliczne dla raportów
 * treningowych.
 */
@Component
@RequiredArgsConstructor
@Slf4j
class TrainingReportScheduler {

    private final TrainingReportService reportService;
    private final EmailSender emailSender;

    /**
     * ZADANIE 1: Zadanie cykliczne generujące raport treningowy w konsoli.
     * Wykonywane co tydzień w każdy poniedziałek o godzinie 8:00.
     */
    @Scheduled(cron = "0 0 8 * * MON")
    public void generateWeeklyConsoleReport() {
        log.info("Rozpoczęcie generowania tygodniowego raportu treningowego do konsoli...");

        Map<User, List<Training>> weeklyReport = reportService.generateWeeklyReport();
        String formattedReport = reportService.formatWeeklyReportForConsole(weeklyReport);

        System.out.println(formattedReport);

        log.info("Raport tygodniowy został wyświetlony w konsoli");
    }

    /**
     * ZADANIE 2: Zadanie cykliczne wysyłające email z podsumowaniem treningów.
     * Wykonywane co tydzień w każdy poniedziałek o godzinie 9:00.
     */
    @Scheduled(cron = "0 0 9 * * MON")
    public void sendWeeklyEmailReports() {
        log.info("Rozpoczęcie wysyłki tygodniowych raportów email...");

        Map<User, Long> totalTrainingsReport = reportService.generateTotalTrainingCountReport();

        totalTrainingsReport.forEach((user, totalTrainings) -> {
            try {
                String emailContent = reportService.formatEmailReport(user, totalTrainings);

                EmailDto email = new EmailDto(
                        user.getEmail(),
                        "Twoje tygodniowe podsumowanie treningowe",
                        emailContent);

                emailSender.send(email);
                log.info("Email wysłany do użytkownika: {} {} ({})",
                        user.getFirstName(), user.getLastName(), user.getEmail());

            } catch (Exception e) {
                log.error("Błąd podczas wysyłania emaila do użytkownika: {} {}",
                        user.getFirstName(), user.getLastName(), e);
            }
        });

        log.info("Zakończono wysyłkę tygodniowych raportów email");
    }

    /**
     * DODATKOWE: Testowe zadanie uruchamiane co minutę (do celów testowych).
     * Odkomentuj, jeśli chcesz testować działanie schedulera bez czekania tydzień.
     */
    @Scheduled(cron = "0 * * * * *")
    public void testScheduledTask() {
        log.info("=== TEST: Scheduled task wykonane o: {}",
                java.time.LocalDateTime.now());
        generateWeeklyConsoleReport();
    }
}
