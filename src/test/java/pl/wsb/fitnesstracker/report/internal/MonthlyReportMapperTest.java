package pl.wsb.fitnesstracker.report.internal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.wsb.fitnesstracker.mail.api.EmailDto;
import pl.wsb.fitnesstracker.report.api.MonthlyReport;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static java.time.ZoneId.systemDefault;
import static org.junit.jupiter.api.Assertions.*;

class MonthlyReportMapperTest {

    @Test
    void toEmailDto() {
        // given
        User user = new User("Jacek", "Nowak", LocalDate.now(), "jacek@nowak.local");
        Training training1 = new Training(user, null, null, ActivityType.RUNNING, 1, 1);
        Training training2 = new Training(user, null, null, ActivityType.CYCLING, 2, 2);
        List<Training> trainings = List.of(training1, training2);
        LocalDate reportAfterLocalDate = LocalDate.of(2025, 3, 1);
        Date reportAfterDate = java.util.Date.from(reportAfterLocalDate.atStartOfDay(systemDefault()).toInstant());
        MonthlyReport monthlyReport = new MonthlyReport(user, trainings, reportAfterDate);

        // when
        EmailDto emailDto = MonthlyReportMapper.toEmailDto(monthlyReport);

        // then
        assertEquals(user.getEmail(), emailDto.toAddress());
        assertEquals(MonthlyReportMapper.EMAIL_TITLE, emailDto.subject());
        assertEquals("""
                Hey Jacek,
                
                Here’s a quick look at your training progress last month:
                📅 Month: March
                ✅ Trainings Completed: 2
                Great job! Keep it up, and see you next month for another update.
                
                Happy training!
                """, emailDto.content());
    }
}
