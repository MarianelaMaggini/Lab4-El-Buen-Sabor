package com.example.buensabor.entities.cliente;

import com.example.buensabor.entities.EntityBean;
import com.example.buensabor.enums.RolNombre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rol")
public class Rol extends EntityBean {

    @NotNull
    @Enumerated(EnumType.STRING)
    private RolNombre rolNombre;
}
