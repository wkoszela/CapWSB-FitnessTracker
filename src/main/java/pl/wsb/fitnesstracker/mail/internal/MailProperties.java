package pl.wsb.fitnesstracker.mail.internal;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.javamail.JavaMailSender;
import pl.wsb.fitnesstracker.mail.api.EmailSender;

/**
 * Configuration of the {@link EmailSender} (additional to the Spring mail configuration for {@link JavaMailSender} bean autoconfiguration).
 */
@ConfigurationProperties(prefix = "mail")
@Getter
@RequiredArgsConstructor
class MailProperties {

    /**
     * Email address that the email should be sent from.
     */
    private final String from;

}
