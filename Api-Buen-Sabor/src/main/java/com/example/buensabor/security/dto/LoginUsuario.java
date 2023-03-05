package com.example.buensabor.security.dto;

import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
public class LoginUsuario {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String clave;
}
