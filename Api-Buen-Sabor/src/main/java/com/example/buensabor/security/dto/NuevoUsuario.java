package com.example.buensabor.security.dto;

import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
public class NuevoUsuario {

    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    private String telefono;
    @Email
    private String email;
    @NotBlank
    private String clave;
    private boolean isEnabled;
    private String tokenPassword;
    private Set<String> roles = new HashSet<>();

}
