package com.capgemini.wsb.fitnesstracker.mail.internal;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailDto;
import com.capgemini.wsb.fitnesstracker.mail.api.EmailSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSenderImpl implements EmailSender {

    private final JavaMailSender javaMailSender;
    private final MailProperties mailProperties;

    @Override
    public void send(EmailDto email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailProperties.getFrom());
        message.setTo(email.toAddress());
        message.setSubject(email.subject());
        message.setText(email.content());

        try {
            javaMailSender.send(message);
            log.info("Email sent to {}", email.toAddress());
        } catch (Exception e) {
            log.error("Failed to send email to {}", email.toAddress(), e);
        }
    }
}
