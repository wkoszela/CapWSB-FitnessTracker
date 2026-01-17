package pl.wsb.fitnesstracker.mail.internal; // Folder internal

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.mail.api.EmailDto;
import pl.wsb.fitnesstracker.mail.api.EmailSender;

/**
 * Implementacja serwisu odpowiedzialnego za wysyłkę e-maili.
 * Adnotacja @Service mówi Springowi, że to jest "Bean", którego może wstrzyknąć do Schedulera.
 */
@Service
@RequiredArgsConstructor
public class EmailService implements EmailSender {

    private final JavaMailSender mailSender;

    @Override
    public void send(EmailDto email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getToAddress());
        message.setSubject(email.getSubject());
        message.setText(email.getContent());
        message.setFrom("reports@fitnesstracker.com");

        mailSender.send(message);
    }
}