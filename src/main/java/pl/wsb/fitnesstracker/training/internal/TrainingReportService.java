package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
class TrainingReportService {

    private final TrainingProvider trainingProvider;
    private final UserProvider userProvider;

    @Scheduled(cron = "0 0 0 * * MON")
    public void generateWeeklyReport() {
        log.info("Starting weekly training report generation...");
        
        Date oneWeekAgo = Date.from(LocalDate.now().minusWeeks(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        List<User> users = userProvider.findAllUsers();
        
        for (User user : users) {
            List<Training> trainings = trainingProvider.findAllTrainingsForUserAfterDate(user.getId(), oneWeekAgo);
            
            log.info("User: {} {} (ID: {}) - Trainings in last week: {}", 
                    user.getFirstName(), 
                    user.getLastName(), 
                    user.getId(), 
                    trainings.size());
        }
        
        log.info("Weekly training report generation finished.");
    }
}
