package com.example.buensabor.entities.rubros;

import com.example.buensabor.entities.EntityBean;
import com.example.buensabor.entities.articulos.ArticuloInsumo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rubro_articulo")
public class RubroArticulo extends EntityBean {

    @Column(name = "denominacion")
    @NotNull
    private String denominacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rubro_articulo")
    private RubroArticulo rubroArticuloPadre;

    @JsonIgnore
    @OneToMany(mappedBy = "rubroArticuloPadre", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<RubroArticulo> rubroArticulosHijos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "rubroArticulo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ArticuloInsumo> articuloInsumos = new ArrayList<>();
}
