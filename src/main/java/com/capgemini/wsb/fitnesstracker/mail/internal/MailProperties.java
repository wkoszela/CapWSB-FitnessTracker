package com.capgemini.wsb.fitnesstracker.mail.internal;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration of the {@link EmailSender} (additional to the Spring mail configuration for {@link JavaMailSender} bean autoconfiguration).
 */
@ConfigurationProperties(prefix = "mail")
@Getter
@Setter
public class MailProperties {

    /**
     * Email address that the email should be sent from.
     */
    private String from;
}
