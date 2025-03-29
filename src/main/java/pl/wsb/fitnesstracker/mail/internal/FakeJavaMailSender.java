package pl.wsb.fitnesstracker.mail.internal;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Arrays;

@Component
@Slf4j
public class FakeJavaMailSender implements JavaMailSender {

    @Override
    public MimeMessage createMimeMessage() {
        return null;
    }

    @Override
    public MimeMessage createMimeMessage(InputStream contentStream) throws MailException {
        return null;
    }

    @Override
    public void send(MimeMessage... mimeMessages) throws MailException {
        Arrays.stream(mimeMessages).forEach(mail -> {
            log.info("Sent email {}", mail);
        });
    }

    @Override
    public void send(SimpleMailMessage... simpleMessages) throws MailException {
        Arrays.stream(simpleMessages).forEach(mail -> {
            log.info("Sent email {}", mail);
        });
    }
}
