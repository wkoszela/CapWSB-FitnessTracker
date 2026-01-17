package pl.wsb.fitnesstracker.report;

import pl.wsb.fitnesstracker.training.api.Training;

import java.util.List;

/**
 * Weekly summary of trainings for a user.
 *
 * @param userId         id of the user
 * @param email          user email address
 * @param trainingsCount number of trainings in the week
 * @param trainings      list of trainings in the week
 */
record WeeklyTrainingSummary(Long userId, String email, int trainingsCount, List<Training> trainings) {

}
