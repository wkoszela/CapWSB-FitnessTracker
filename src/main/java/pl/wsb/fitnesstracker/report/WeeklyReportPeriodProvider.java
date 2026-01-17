package pl.wsb.fitnesstracker.report;

import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * Calculates reporting ranges for weekly summaries.
 */
@Component
class WeeklyReportPeriodProvider {

    private final Clock clock;

    WeeklyReportPeriodProvider(Clock clock) {
        this.clock = clock;
    }

    /**
     * Returns the previous full week (Monday-Sunday) range.
     *
     * @return week range for the previous full week
     */
    WeekRange previousWeek() {
        LocalDate today = LocalDate.now(clock);
        LocalDate thisWeekStart = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate weekStart = thisWeekStart.minusWeeks(1);
        LocalDate weekEnd = weekStart.plusDays(6);
        return new WeekRange(weekStart, weekEnd);
    }
}
