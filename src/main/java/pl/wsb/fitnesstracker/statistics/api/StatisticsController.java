package pl.wsb.fitnesstracker.statistics.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wsb.fitnesstracker.statistics.WeeklyReportService;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticsController { // for tests

    private final WeeklyReportService weeklyReportService;

    @PostMapping("/generate-report")
    public String generateReport() {
        weeklyReportService.generateWeeklyReport();
        return "Raport wygenerowany i wysłany";
    }
}