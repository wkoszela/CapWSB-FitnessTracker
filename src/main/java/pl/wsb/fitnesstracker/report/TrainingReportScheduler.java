package pl.wsb.fitnesstracker.report;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.mail.api.EmailDto;
import pl.wsb.fitnesstracker.mail.api.EmailSender;
import pl.wsb.fitnesstracker.training.api.Training;

import java.util.List;

/**
 * Scheduler responsible for triggering weekly report generation.
 */
@Component
@Slf4j
class TrainingReportScheduler {

    private final TrainingReportService reportService;
    private final WeeklyReportPeriodProvider periodProvider;
    private final WeeklyReportProperties properties;
    private final EmailSender emailSender;

    TrainingReportScheduler(
            TrainingReportService reportService,
            WeeklyReportPeriodProvider periodProvider,
            WeeklyReportProperties properties,
            EmailSender emailSender) {
        this.reportService = reportService;
        this.periodProvider = periodProvider;
        this.properties = properties;
        this.emailSender = emailSender;
    }

    @Scheduled(cron = "${app.reports.weekly.cron}", zone = "${app.reports.weekly.zone:Europe/Warsaw}")
    void generateWeeklyReports() {
        WeekRange range = periodProvider.previousWeek();
        List<WeeklyTrainingSummary> summaries = reportService.generateWeeklySummary(range.start(), range.end());

        summaries.forEach(summary -> log.info(
                "Weekly training report: userId={}, email={}, week={}..{}, trainings={}, trainingIds={}",
                summary.userId(),
                summary.email(),
                range.start(),
                range.end(),
                summary.trainingsCount(),
                summary.trainings().stream().map(Training::getId).toList()));

        if (properties.getEmail().isEnabled()) {
            sendEmails(range, summaries);
        }
    }

    private void sendEmails(WeekRange range, List<WeeklyTrainingSummary> summaries) {
        summaries.forEach(summary -> {
            if (summary.email() == null || summary.email().isBlank()) {
                log.warn("Skipping weekly report email for userId={} due to missing email address", summary.userId());
                return;
            }
            String subject = "Podsumowanie treningów – tydzień %s do %s".formatted(range.start(), range.end());
            String content = "Masz zarejestrowanych łącznie: %d treningów w tym tygodniu."
                    .formatted(summary.trainingsCount());
            emailSender.send(new EmailDto(summary.email(), subject, content));
        });
    }
}
