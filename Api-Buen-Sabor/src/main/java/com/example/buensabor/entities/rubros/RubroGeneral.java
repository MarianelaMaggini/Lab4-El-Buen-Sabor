package com.example.buensabor.entities.rubros;

import com.example.buensabor.entities.EntityBean;
import com.example.buensabor.entities.articulos.ArticuloManufacturado;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rubro_general")
public class RubroGeneral extends EntityBean {

    @Column(name = "denominacion")
    @NotNull
    private String denominacion;

    @JsonIgnore
    @OneToMany(mappedBy = "rubroGeneral", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ArticuloManufacturado> articuloManufacturados;
}
