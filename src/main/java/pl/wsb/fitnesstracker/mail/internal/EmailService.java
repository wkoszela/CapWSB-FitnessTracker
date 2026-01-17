package pl.wsb.fitnesstracker.mail.internal;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.mail.api.EmailDto;
import pl.wsb.fitnesstracker.mail.api.EmailSender;

/**
 * EmailSender implementation based on Spring Mail.
 */
@Service
class EmailService implements EmailSender {

    private final JavaMailSender mailSender;
    private final MailProperties mailProperties;

    EmailService(JavaMailSender mailSender, MailProperties mailProperties) {
        this.mailSender = mailSender;
        this.mailProperties = mailProperties;
    }

    @Override
    public void send(EmailDto email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.toAddress());
        message.setSubject(email.subject());
        message.setText(email.content());
        message.setFrom(mailProperties.getFrom());
        mailSender.send(message);
    }
}
