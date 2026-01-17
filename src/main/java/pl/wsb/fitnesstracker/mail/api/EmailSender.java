package pl.wsb.fitnesstracker.mail.api;

/**
 * Publiczny interfejs dostępny dla innych modułów aplikacji.
 */
public interface EmailSender {

    /**
     * Wysyła wiadomość e-mail na podstawie danych z obiektu DTO.
     *
     * @param email obiekt zawierający adresata, temat i treść
     */
    void send(EmailDto email);

}