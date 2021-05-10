package com.example.buensabor.entities.articulos;

import com.example.buensabor.entities.EntityBean;
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inventario")
public class InventarioEntity extends EntityBean {

    @Column(name = "stock_actual", nullable = false)
    @NotNull
    private double stockActual;

    @Column(name = "stock_minimo", nullable = false)
    @NotNull
    private double stockMinimo;

    @JsonIgnore
    @OneToMany(mappedBy = "inventario", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<HistoricoArticuloEntity> historicoArticuloEntities = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_unidad_medida")
    private UnidadMedidaEntity unidadMedidaEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_articulo")
    private ArticuloEntity articuloEntity;
}
