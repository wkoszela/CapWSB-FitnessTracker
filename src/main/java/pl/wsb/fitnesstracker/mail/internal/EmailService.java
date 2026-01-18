package pl.wsb.fitnesstracker.mail.internal;
import io.mailtrap.client.MailtrapClient;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.emails.Address;
import io.mailtrap.model.request.emails.MailtrapMail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {
    private static final String TOKEN = "cc1986de375cfbb40bdcaf3b0f3b5b50";

    private final MailtrapClient client;

    EmailService() {
        MailtrapConfig config = new MailtrapConfig.Builder()
                .sandbox(true)
                .inboxId(4320449L)
                .token(TOKEN)
                .build();

        this.client = MailtrapClientFactory.createMailtrapClient(config);
    }

    public void sendWeeklyReportMail(String to, String content) {
        MailtrapMail mail = MailtrapMail.builder()
                .from(new Address("hello@demomailtrap.co", "Fitness Tracker"))
                .to(List.of(new Address(to)))
                .subject("Weekly training report")
                .text(content)
                .category("Weekly Report")
                .build();

        try {
            client.send(mail);
        } catch (Exception e) {
            System.err.println("Mail sending failed: " + e.getMessage());
        }
    }
}
