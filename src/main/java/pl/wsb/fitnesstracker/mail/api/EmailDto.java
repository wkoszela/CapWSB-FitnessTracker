package pl.wsb.fitnesstracker.mail.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EmailDto {
    private final String toAddress;
    private final String subject;
    private final String content;
}