package pl.wsb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling; // Dodano

@EntityScan(basePackages = "pl.wsb.fitnesstracker")
@EnableJpaRepositories(basePackages = "pl.wsb.fitnesstracker")
@EnableScheduling // Dodano tę adnotację
@SpringBootApplication
public class FitnessTracker {

    public static void main(String[] args) {
        SpringApplication.run(FitnessTracker.class, args);
    }

}