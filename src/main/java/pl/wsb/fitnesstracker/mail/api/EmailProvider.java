package pl.wsb.fitnesstracker.mail.api;

import pl.wsb.fitnesstracker.training.api.Training;

import java.util.List;

/**
 * Service interface for sending emails.
 */
public interface EmailProvider {
    EmailDto sendMail(String to, String subject, List<Training> trainingList);
}