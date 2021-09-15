package com.example.buensabor.security.dto;

import lombok.Data;

@Data
public class EmailValuesDto {
    private String nombre;
    private String mailFrom;
    private String mailTo;
    private String subject;
    private String tokenPassword;

    public EmailValuesDto() {
    }

    public EmailValuesDto(String mailFrom, String mailTo, String subject, String tokenPassword) {
        this.mailFrom = mailFrom;
        this.mailTo = mailTo;
        this.subject = subject;
        this.tokenPassword = tokenPassword;
    }
}
