package pl.wsb.fitnesstracker.report;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.ZoneId;

/**
 * Configuration for reporting components.
 */
@Configuration
@EnableConfigurationProperties(WeeklyReportProperties.class)
class ReportConfig {

    @Bean
    Clock reportingClock(WeeklyReportProperties properties) {
        return Clock.system(ZoneId.of(properties.getZone()));
    }
}
