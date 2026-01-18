package pl.wsb.fitnesstracker.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;

@EnableScheduling
@Component
@RequiredArgsConstructor
public class RaportShedule {
    private final RaportService raportService;

    /*@Scheduled(cron = "0 59 11 * * SUN")*/
    @Scheduled(cron = "*/10 * * * * *")
    void generateWeeklyReport() {

        LocalDate now = LocalDate.now();
        LocalDate startOfWeek = now.minusWeeks(1).with(DayOfWeek.SUNDAY);
        LocalDate endOfWeek = startOfWeek.plusDays(6);

        System.out.println("=== WEEKLY TRAINING REPORT ===");

        raportService.generateReport(startOfWeek, endOfWeek)
                .forEach(System.out::println);

        System.out.println("=== END OF REPORT ===");
    }
}
