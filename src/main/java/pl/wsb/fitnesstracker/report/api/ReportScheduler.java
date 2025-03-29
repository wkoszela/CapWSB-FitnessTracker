package pl.wsb.fitnesstracker.report.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.mail.api.EmailDto;
import pl.wsb.fitnesstracker.mail.api.EmailSender;
import pl.wsb.fitnesstracker.report.internal.MonthlyReportMapper;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static java.time.ZoneId.systemDefault;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReportScheduler {

    @Autowired private final ReportService reportService;

    @Autowired private final EmailSender emailSender;

    @Autowired private final Clock clock;

//    @Scheduled(cron = "0 20 1 * * *")
    @Scheduled(cron = "*/30 * * * * *")
    void monthlyReport() {
        LocalDate reportAfterLocalDate = LocalDate.now(clock).minusMonths(1).withDayOfMonth(1);
        Date reportAfterDate = java.util.Date.from(reportAfterLocalDate.atStartOfDay(systemDefault()).toInstant());
        log.info("Generating monthly report after date {}", reportAfterDate);
        List<EmailDto> emailDtos = reportService.generateMonthlyReports(reportAfterDate)
                .stream()
                .map(MonthlyReportMapper::toEmailDto)
                .toList();
        emailDtos.forEach(emailSender::send);
    }
}
