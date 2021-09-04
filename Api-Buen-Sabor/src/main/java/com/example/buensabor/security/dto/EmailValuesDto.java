package com.example.buensabor.security.dto;

import lombok.Data;

@Data
public class EmailValuesDto {
    private String nombre;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String token;

    public EmailValuesDto(String emailFrom, String emailTo, String subject, String token) {
        this.emailFrom = emailFrom;
        this.emailTo = emailTo;
        this.subject = subject;
        this.token = token;
    }
}
