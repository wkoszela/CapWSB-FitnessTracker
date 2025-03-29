package pl.wsb.fitnesstracker.report.internal;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.wsb.fitnesstracker.report.api.MonthlyReport;
import pl.wsb.fitnesstracker.report.api.ReportService;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static java.time.ZoneId.systemDefault;
import static org.junit.jupiter.api.Assertions.*;

class ReportServiceImplTest {

    @Test
    void generateMonthlyReports() {
        // given
        User user1 = new User("Jacek", "Nowak", LocalDate.now(), "jacek@nowak.local");
        Training training1 = new Training(user1, null, null, ActivityType.RUNNING, 1, 1);
        Training training2 = new Training(user1, null, null, ActivityType.CYCLING, 3, 4);
        User user2 = new User("Marek", "Kowalczyk", LocalDate.now(), "marek@kowalczyk.local");
        Training training3 = new Training(user2, null, null, ActivityType.TENNIS, 5, 6);
        Training training4 = new Training(user2, null, null, ActivityType.WALKING, 7, 8);

        LocalDate reportAfterLocalDate = LocalDate.of(2025, 3, 1);
        Date reportAfterDate = java.util.Date.from(reportAfterLocalDate.atStartOfDay(systemDefault()).toInstant());
        List<Training> trainings = List.of(training1, training2, training3, training4);
        MonthlyReport expectedReport1 = new MonthlyReport(user1, List.of(training1, training2), reportAfterDate);
        MonthlyReport expectedReport2 = new MonthlyReport(user2, List.of(training3, training4), reportAfterDate);

        TrainingProvider trainingProvider = Mockito.mock(TrainingProvider.class);
        Mockito.when(trainingProvider.findTrainingsWithEndDateAfter(reportAfterDate)).thenReturn(trainings);

        ReportService reportService = new ReportServiceImpl(trainingProvider);

        // when
        List<MonthlyReport> monthlyReports = reportService.generateMonthlyReports(reportAfterDate);

        // then
        assertEquals(2, monthlyReports.size());
        List<MonthlyReport> sorted = new ArrayList<>(monthlyReports);
        sorted.sort(Comparator.comparing(r -> r.user().getLastName()));
        assertIterableEquals(List.of(expectedReport2, expectedReport1), sorted);
    }
}
