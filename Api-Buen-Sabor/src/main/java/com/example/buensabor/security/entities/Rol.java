package com.example.buensabor.security.entities;

import com.example.buensabor.entities.base.BaseEntity;
import com.example.buensabor.security.enums.RolNombre;
import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "rol")
public class Rol extends BaseEntity {

    @NotNull
    @Enumerated(EnumType.STRING)
    private RolNombre rolNombre;

    public Rol() {
    }

    public Rol(@NotNull RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
}
