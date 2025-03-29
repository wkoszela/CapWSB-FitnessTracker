package pl.wsb.fitnesstracker.report.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.report.api.MonthlyReport;
import pl.wsb.fitnesstracker.report.api.ReportService;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportServiceImpl implements ReportService {

    @Autowired
    private final TrainingProvider trainingProvider;

    @Override
    public List<MonthlyReport> generateMonthlyReports(Date afterTime) {
        return trainingProvider.findTrainingsWithEndDateAfter(afterTime)
                .stream()
                .collect(groupingBy(Training::getUser))
                .entrySet()
                .stream()
                .map(entry -> new MonthlyReport(entry.getKey(), entry.getValue(), afterTime))
                .toList();
    }
}
