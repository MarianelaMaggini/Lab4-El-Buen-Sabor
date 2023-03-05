package com.example.buensabor.entities.rubro;

import com.example.buensabor.entities.base.BaseEntity;
import com.example.buensabor.entities.articulos.Articulo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "rubro")
public class Rubro extends BaseEntity {

    @Column(name = "denominacion", length = 65, nullable = false)
    @NotNull
    private String denominacion;

    @Column(name = "fecha_baja")
    private Date fechaBaja;

    @JsonIgnore
    @OneToMany(mappedBy = "rubro")
    private List<Articulo> articulo = new ArrayList<>();

}
