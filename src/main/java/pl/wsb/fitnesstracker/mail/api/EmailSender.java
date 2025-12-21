package pl.wsb.fitnesstracker.mail.api;

/**
 * Interfejs (API) dla komponentu odpowiedzialnego za wysyłanie wiadomości
 * email.
 */
public interface EmailSender {

    /**
     * Wysyła wiadomość email do odbiorcy na podstawie danych z obiektu
     * {@link EmailDto}.
     *
     * @param email informacje o wiadomości email do wysłania
     */
    void send(EmailDto email);

}
