package pl.wsb.fitnesstracker.report.api;

import java.util.Date;
import java.util.List;

public interface ReportService {

    /**
     * This method generates a monthly report for every user that was active *after* the given time.
     *
     * @param afterTime date used to find active users since
     * @return {@link List} containing the generated reports
     */
    List<MonthlyReport> generateMonthlyReports(Date afterTime);
}
