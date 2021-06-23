package com.example.buensabor.security.entities;

import com.example.buensabor.entities.EntityBean;
import com.example.buensabor.security.enums.RolNombre;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "rol")
public class Rol extends EntityBean {

    @NotNull
    @Enumerated(EnumType.STRING)
    private RolNombre rolNombre;

    public Rol() {
    }

    public Rol(@NotNull RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
}
