package com.capgemini.wsb.fitnesstracker.mail.internal;

import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;
import com.capgemini.wsb.fitnesstracker.mail.api.EmailDto;
import com.capgemini.wsb.fitnesstracker.mail.api.EmailSender;
import com.capgemini.wsb.fitnesstracker.mail.api.EmailService;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsProvider;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Slf4j
@Service
class EmailServiceImpl implements EmailSender, EmailService {

    private final JavaMailSender mailSender;

    private final StatisticsProvider statisticsProvider;

    private final StatisticsService statisticsService;


    @Override
    public void sendSummaries() {
        try{
            statisticsService.reGenerateStatistics();
        } catch (NotFoundException e){
            log.error("{}. Couldn't sent email summaries", e.getMessage());
        }


        var stats = statisticsProvider.getAllStatistics();

        String today = LocalDate.now().toString();

        String subject = "Weekly summary report";
        String content = "Hello, as of %s, you have %d registered trainings";

        for (var stat : stats) {
            String email = stat.getUser().getEmail();
            int noOfTrainings = stat.getTotalTrainings();
            EmailDto emailDto = new EmailDto(email, subject, String.format(content, today, noOfTrainings));
            log.info("Sending email: {}", email);
            send(emailDto);
            log.info("Email sent: {}", email);
        }
    }

    @Override
    public void send(EmailDto email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("test@mail.com");
        mailMessage.setTo(email.toAddress());
        mailMessage.setSubject(email.subject());
        mailMessage.setText(email.content());

        mailSender.send(mailMessage);
    }

}
