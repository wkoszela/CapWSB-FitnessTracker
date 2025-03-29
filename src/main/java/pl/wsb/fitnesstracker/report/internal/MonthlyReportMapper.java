package pl.wsb.fitnesstracker.report.internal;

import pl.wsb.fitnesstracker.mail.api.EmailDto;
import pl.wsb.fitnesstracker.report.api.MonthlyReport;

import java.text.SimpleDateFormat;

public class MonthlyReportMapper {

    final static String EMAIL_TITLE = "Your monthly report is here!";
    private final static String EMAIL_BODY_TEMPLATE = """
            Hey %s,
            
            Here’s a quick look at your training progress last month:
            📅 Month: %s
            ✅ Trainings Completed: %d
            Great job! Keep it up, and see you next month for another update.
            
            Happy training!
            """;

    public static EmailDto toEmailDto(MonthlyReport monthlyReport) {
        String toAddress = monthlyReport.user().getEmail();
        String userName = monthlyReport.user().getFirstName();
        String monthName = new SimpleDateFormat("MMMM").format(monthlyReport.afterTime());
        int trainingsCompleted = monthlyReport.trainings().size();
        String emailBody = String.format(EMAIL_BODY_TEMPLATE, userName, monthName, trainingsCompleted);
        return new EmailDto(toAddress, EMAIL_TITLE,  emailBody);
    }
}
