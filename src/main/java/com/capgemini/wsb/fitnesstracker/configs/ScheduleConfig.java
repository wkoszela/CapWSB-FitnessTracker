package com.capgemini.wsb.fitnesstracker.configs;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class ScheduleConfig {

    private final EmailService emailService;

    @Scheduled(cron = "0 0 0 * * MON")
    public void scheduleFixedRate() {
        log.info("Sending summaries...");
        emailService.sendSummaries();
    }
}
