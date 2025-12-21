// The code snippet you provided is a Java class named `EmailDto` defined as a record. Records were
// introduced in Java 14 as a concise way to declare classes that are simple data carriers. In this
// case, the `EmailDto` record represents an email data transfer object with three fields: `toAddress`,
// `subject`, and `content`.
package pl.wsb.fitnesstracker.mail.api;

/**
 * API interface for component responsible for sending emails.
 */
public interface EmailSender {

    /**
     * Sends the email message to the recipient from the provided {@link EmailDto}.
     *
     * @param email information on email to be sent
     */
    void send(EmailDto email);

}
