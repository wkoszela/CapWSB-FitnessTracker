package pl.wsb.fitnesstracker.report.api;

import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;
import java.util.List;

public record MonthlyReport(User user, List<Training> trainings, Date afterTime) {

}
