package pl.wsb.fitnesstracker.statistics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class WeeklyReportScheduler {

    private final WeeklyReportService weeklyReportService;

    @Scheduled(cron = "0 0 0 * * MON")
    public void generateWeeklyReport() {
        
        log.info("Start generowania tygodniowego raportu treningów");
        weeklyReportService.generateWeeklyReport();
        log.info("Koniec generowania tygodniowego raportu treningów");
    }
}
