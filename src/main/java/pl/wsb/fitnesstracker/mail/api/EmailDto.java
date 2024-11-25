package pl.wsb.fitnesstracker.mail.api;

public record EmailDto(String toAddress, String subject, String content) {

}
