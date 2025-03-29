package pl.wsb.fitnesstracker.report.api;

import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import pl.wsb.fitnesstracker.mail.api.EmailDto;
import pl.wsb.fitnesstracker.mail.api.EmailSender;
import pl.wsb.fitnesstracker.report.internal.ReportServiceImpl;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static java.time.ZoneId.systemDefault;
import static org.junit.jupiter.api.Assertions.*;

class ReportSchedulerTest {

    @Test
    void monthlyReport() {
        // given
        User user1 = new User("Jacek", "Nowak", LocalDate.now(), "jacek@nowak.local");
        Training training1 = new Training(user1, null, null, ActivityType.RUNNING, 1, 1);
        Training training2 = new Training(user1, null, null, ActivityType.CYCLING, 3, 4);
        User user2 = new User("Marek", "Kowalczyk", LocalDate.now(), "marek@kowalczyk.local");
        Training training3 = new Training(user2, null, null, ActivityType.TENNIS, 5, 6);

        LocalDate reportAfterLocalDate = LocalDate.of(2025, 3, 1);
        Date reportAfterDate = java.util.Date.from(reportAfterLocalDate.atStartOfDay(systemDefault()).toInstant());
        MonthlyReport report1 = new MonthlyReport(user1, List.of(training1, training2), reportAfterDate);
        MonthlyReport report2 = new MonthlyReport(user2, List.of(training3), reportAfterDate);

        ReportService reportService = Mockito.mock(ReportService.class);
        Mockito.when(reportService.generateMonthlyReports(reportAfterDate)).thenReturn(List.of(report1, report2));

        LocalDate reportLocalDate = LocalDate.of(2025, 4, 1);
        Date reportDate = java.util.Date.from(reportLocalDate.atStartOfDay(systemDefault()).toInstant());
        Clock clock = Clock.fixed(reportDate.toInstant(), ZoneId.systemDefault());
        EmailSender emailSender = Mockito.mock(EmailSender.class);
        ReportScheduler reportScheduler = new ReportScheduler(reportService, emailSender, clock);

        // when
        reportScheduler.monthlyReport();

        // then
        ArgumentCaptor<EmailDto> emailCaptor = ArgumentCaptor.forClass(EmailDto.class);
        Mockito.verify(emailSender, Mockito.times(2)).send(emailCaptor.capture());
        List<EmailDto> capturedEmails = emailCaptor.getAllValues();

        EmailDto expectedEmailDto1 = new EmailDto("jacek@nowak.local", "Your monthly report is here!", """
                Hey Jacek,
                
                Here’s a quick look at your training progress last month:
                📅 Month: March
                ✅ Trainings Completed: 2
                Great job! Keep it up, and see you next month for another update.
                
                Happy training!
                """);
        EmailDto expectedEmailDto2 = new EmailDto("marek@kowalczyk.local", "Your monthly report is here!", """
                Hey Marek,
                
                Here’s a quick look at your training progress last month:
                📅 Month: March
                ✅ Trainings Completed: 1
                Great job! Keep it up, and see you next month for another update.
                
                Happy training!
                """);
        assertIterableEquals(List.of(expectedEmailDto1, expectedEmailDto2), capturedEmails);
    }
}