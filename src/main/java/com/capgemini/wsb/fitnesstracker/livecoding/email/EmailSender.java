package com.capgemini.wsb.fitnesstracker.livecoding.email;

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
