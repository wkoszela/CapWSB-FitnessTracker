package com.capgemini.wsb.fitnesstracker.report;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailDto;
import com.capgemini.wsb.fitnesstracker.mail.api.EmailSender;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MonthlyTrainingReportService {

    private final UserProvider userProvider;
    private final TrainingProvider trainingProvider;
    private final EmailSender emailSender;

    /**
     * Generates monthly training reports for each user and sends them via email.
     * This method is scheduled to run at midnight on the first day of every month.
     * @Scheduled(cron = "0 0 0 1 * *")
     */

    /**
     * Executes every minute to perform the desired task. (for TESTING)
     */
    @Scheduled(cron = "0 * * * * *")
    public void generateMonthlyReports() {
        log.info("Starting monthly training report generation...");

        // Get the current date
        LocalDate now = LocalDate.now();

        // Determine the first and last day of the previous month
        LocalDate firstDayOfLastMonth = now.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayOfLastMonth = now.withDayOfMonth(1).minusDays(1);

        // Convert LocalDate to ZonedDateTime in the system's default timezone
        ZonedDateTime startOfMonth = firstDayOfLastMonth.atStartOfDay(ZoneId.systemDefault());
        ZonedDateTime endOfMonth = lastDayOfLastMonth.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault());

        // Convert ZonedDateTime to Instant for comparison
        Instant startInstant = startOfMonth.toInstant();
        Instant endInstant = endOfMonth.toInstant();

        // Retrieve all users
        List<User> users = userProvider.findAllUsers();

        for (User user : users) {
            // Retrieve trainings for the user
            List<Training> userTrainings = trainingProvider.getTrainingsByUserId(user.getId()).stream()
                    .filter(training -> {
                        Instant trainingTime = training.getStartTime().toInstant();
                        return !trainingTime.isBefore(startInstant) && !trainingTime.isAfter(endInstant);
                    })
                    .collect(Collectors.toList());

            // Generate email content
            String reportContent = generateReportContent(user, userTrainings, firstDayOfLastMonth, lastDayOfLastMonth);

            // Define email subject
            String subject = String.format("Your Training Summary for %s %d",
                    firstDayOfLastMonth.getMonth().name(),
                    firstDayOfLastMonth.getYear());

            // Create EmailDto
            EmailDto email = new EmailDto(user.getEmail(), subject, reportContent);

            // Send the email
            emailSender.send(email);
        }

        log.info("Monthly training report generation completed.");
    }

    /**
     * Generates the content of the training report email for a user.
     *
     * @param user        The user
     * @param trainings   List of trainings in the period
     * @param startDate   Start date of the reporting period
     * @param endDate     End date of the reporting period
     * @return The report content as a string
     */
    private String generateReportContent(User user, List<Training> trainings, LocalDate startDate, LocalDate endDate) {
        StringBuilder sb = new StringBuilder();
        sb.append("Dear ").append(user.getFirstName()).append(",\n\n");
        sb.append("Here is your training summary for the period ").append(startDate).append(" to ").append(endDate).append(":\n\n");
        sb.append("Total Trainings: ").append(trainings.size()).append("\n\n");

        if (!trainings.isEmpty()) {
            sb.append("Details:\n");
            for (Training training : trainings) {
                sb.append("- Date: ").append(training.getStartTime()).append(", Activity: ").append(training.getActivityType())
                        .append(", Distance: ").append(training.getDistance()).append(" km\n");
            }
            sb.append("\n");
        }

        sb.append("Keep up the good work!\n");
        sb.append("Best regards,\n");
        sb.append("Your Fitness Tracker Team");

        return sb.toString();
    }
}
