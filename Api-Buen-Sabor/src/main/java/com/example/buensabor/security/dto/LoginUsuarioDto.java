package com.example.buensabor.security.dto;

import lombok.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginUsuarioDto {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String clave;
}
