package pl.wsb.fitnesstracker.mail.internal;

import pl.wsb.fitnesstracker.mail.api.EmailSender;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Configuration of the {@link EmailSender} (additional to the Spring mail configuration for {@link JavaMailSender} bean autoconfiguration).
 */
@ConfigurationProperties(prefix = "mail")
class MailProperties {

    public String getFrom() {
        return from;
    }

    /**
     * Email address that the email should be sent from.
     */
    private final String from;

    MailProperties(String from) {
        this.from = from;
    }
}
