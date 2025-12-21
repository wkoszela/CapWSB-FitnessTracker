package pl.wsb.fitnesstracker.mail.api;

/**
 * Obiekt DTO (Data Transfer Object) reprezentujący wiadomość email.
 *
 * @param toAddress adres odbiorcy
 * @param subject   temat wiadomości
 * @param content   treść wiadomości
 */
public record EmailDto(String toAddress, String subject, String content) {

}
