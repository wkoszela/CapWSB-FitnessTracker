package pl.wsb.fitnesstracker.report;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for weekly training reports.
 */
@ConfigurationProperties(prefix = "app.reports.weekly")
class WeeklyReportProperties {

    /**
     * Cron expression controlling weekly report scheduling.
     */
    private String cron = "0 0 8 ? * MON";

    /**
     * Time zone identifier used for weekly reporting.
     */
    private String zone = "Europe/Warsaw";

    /**
     * Email-specific configuration.
     */
    private Email email = new Email();

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    static class Email {

        /**
         * Controls whether weekly report emails are sent.
         */
        private boolean enabled = true;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}
