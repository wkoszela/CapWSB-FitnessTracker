package pl.wsb.fitnesstracker.mail.internal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.mail.api.EmailDto;
import pl.wsb.fitnesstracker.mail.api.EmailSender;

@Slf4j
@Component
public class EmailSenderImpl implements EmailSender {

    @Autowired private JavaMailSender javaMailSender;

    @Autowired private MailProperties mailProperties;

    @Override
    public void send(EmailDto email) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(mailProperties.getFrom());
            mailMessage.setTo(email.toAddress());
            mailMessage.setText(email.content());
            mailMessage.setSubject(email.subject());
            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            log.error("failed to send email", e);
        }
    }
}
