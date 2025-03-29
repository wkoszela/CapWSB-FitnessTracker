package pl.wsb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static java.time.ZoneId.systemDefault;

@SpringBootApplication
@EnableScheduling
public class FitnessTracker {

    @Bean
    public Clock clock() {
//        return Clock.systemDefaultZone();
        LocalDate reportLocalDate = LocalDate.of(2024, 2, 1);
        Date reportDate = java.util.Date.from(reportLocalDate.atStartOfDay(systemDefault()).toInstant());
        return Clock.fixed(reportDate.toInstant(), ZoneId.systemDefault());
    }

    public static void main(String[] args) {
        SpringApplication.run(FitnessTracker.class, args);
    }
}
