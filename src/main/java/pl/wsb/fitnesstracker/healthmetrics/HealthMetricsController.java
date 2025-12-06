package pl.wsb.fitnesstracker.healthmetrics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/health")
public class HealthMetricsController {

    private final HealthMetricsRepository healthMetricsRepository;


    public HealthMetricsController(HealthMetricsRepository healthMetricsRepository) {
        this.healthMetricsRepository = healthMetricsRepository;
    }

    @GetMapping
    public List<HealthMetrics> findHealthMetricsAboveDefinedWeight() {
        return this.healthMetricsRepository.findHealthMetricsAboveDefinedWeight(85);

    }
}
