package pl.wsb.fitnesstracker.notification.internal;

import pl.wsb.fitnesstracker.mail.api.*;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;

import lombok.Data;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
@EnableScheduling
public class NotificationService {
    private final EmailSender emailSender;
    private final EmailProvider emailProvider;
    private final TrainingProvider trainingProvider;
    private final UserProvider userProvider;

    public NotificationService(EmailSender emailSender, EmailProvider emailProvider, TrainingProvider trainingProvider, UserProvider userProvider) {
        this.emailSender = emailSender;
        this.emailProvider = emailProvider;
        this.trainingProvider = trainingProvider;
        this.userProvider = userProvider;
    }

    /**
     * Generates report and sends it to all users.
     */
    @Scheduled(cron = "0 0 8 1 * *") //Report scheduled for every 1st of month on 8:00
    public void generateReportAndSendMail() {
        System.out.println("Report generation on cron schedule");

        List<User> allUsers = userProvider.getAllUsers();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneWeekAgo = now.minusMonths(1);

        for (User user : allUsers) {

            List<Training> recentTrainings = trainingProvider.getTrainingsByUserId(user.getId()).stream()
                    .filter(training -> training.getStartTime().isAfter(oneWeekAgo))
                    .collect(Collectors.toList());

            if (!recentTrainings.isEmpty()) {
                String reportString = "MONTHLY REPORT";
                final EmailDto emailDto = emailProvider.sendMail(user.getEmail(),
                        reportString,
                        recentTrainings);

                System.out.println("Sending monthly e-mail...");
                emailSender.send(emailDto);
            }
        }
    }

}