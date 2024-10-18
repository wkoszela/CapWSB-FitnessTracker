package com.capgemini.wsb.fitnesstracker.mail.internal;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailDto;
import com.capgemini.wsb.fitnesstracker.mail.api.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendMail(EmailDto emailDto) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("test@mail.com");
        mailMessage.setTo(emailDto.toAddress());
        mailMessage.setSubject(emailDto.subject());
        mailMessage.setText(emailDto.content());

        mailSender.send(mailMessage);
    }
}
