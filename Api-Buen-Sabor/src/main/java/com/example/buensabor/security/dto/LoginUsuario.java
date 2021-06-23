package com.example.buensabor.security.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginUsuario {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String clave;
}
