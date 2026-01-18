package pl.wsb.fitnesstracker.mail.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.mail.api.EmailDto;
import pl.wsb.fitnesstracker.mail.api.EmailSender;

/**
 * Implementacja serwisu do wysyłania wiadomości email.
 * Wykorzystuje Spring Mail do wysyłania wiadomości poprzez skonfigurowany
 * serwer SMTP.
 */
@Service
@RequiredArgsConstructor
@Slf4j
class EmailSenderImpl implements EmailSender {

    private final JavaMailSender mailSender;
    private final MailProperties mailProperties;

    /**
     * Wysyła wiadomość email do odbiorcy na podstawie danych z obiektu
     * {@link EmailDto}.
     *
     * @param email informacje o wiadomości email do wysłania
     */
    @Override
    public void send(EmailDto email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(mailProperties.getFrom());
            message.setTo(email.toAddress());
            message.setSubject(email.subject());
            message.setText(email.content());

            mailSender.send(message);
            log.info("Email wysłany pomyślnie do: {}", email.toAddress());
        } catch (Exception e) {
            log.error("Błąd podczas wysyłania emaila do: {}", email.toAddress(), e);
            throw new RuntimeException("Nie udało się wysłać emaila", e);
        }
    }
}
