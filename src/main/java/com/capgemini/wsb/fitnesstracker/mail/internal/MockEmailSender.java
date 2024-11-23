package com.capgemini.wsb.fitnesstracker.mail.internal;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailDto;
import com.capgemini.wsb.fitnesstracker.mail.api.EmailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@Slf4j
public class MockEmailSender implements EmailSender {

    @Override
    public void send(EmailDto email) {
        log.info("Mock email sent to: {}\nSubject: {}\nContent:\n{}", email.toAddress(), email.subject(), email.content());
    }
}
