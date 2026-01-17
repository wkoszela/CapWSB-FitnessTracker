package pl.wsb.fitnesstracker.mail.internal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import pl.wsb.fitnesstracker.mail.api.EmailDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @Test
    void shouldSendEmailWithConfiguredFromAddress() {
        MailProperties properties = new MailProperties("from@domain.com");
        EmailService emailService = new EmailService(mailSender, properties);

        EmailDto email = new EmailDto("to@domain.com", "Weekly report", "You have 3 trainings.");

        emailService.send(email);

        ArgumentCaptor<SimpleMailMessage> messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(messageCaptor.capture());

        SimpleMailMessage sentMessage = messageCaptor.getValue();
        assertThat(sentMessage.getTo()).containsExactly("to@domain.com");
        assertThat(sentMessage.getFrom()).isEqualTo("from@domain.com");
        assertThat(sentMessage.getSubject()).isEqualTo("Weekly report");
        assertThat(sentMessage.getText()).isEqualTo("You have 3 trainings.");
    }
}
