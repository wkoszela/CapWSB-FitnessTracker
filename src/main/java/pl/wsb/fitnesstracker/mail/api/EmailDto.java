// The code snippet you provided is defining a Java interface named `EmailSender`. This interface
// serves as an API for a component responsible for sending emails. It declares a method `send` that
// takes an `EmailDto` object as a parameter, representing the information of the email to be sent.
/**
 * The `InitialDataLoader` class in Java is responsible for loading dummy data into the database upon
 * application startup when run with the `loadInitialData` profile.
 */
package pl.wsb.fitnesstracker.mail.api;

public record EmailDto(String toAddress, String subject, String content) {

}
