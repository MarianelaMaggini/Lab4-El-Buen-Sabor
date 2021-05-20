package com.example.buensabor.entities.articulos;

import com.example.buensabor.entities.EntityBean;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "historico_articulo")
public class HistoricoArticuloEntity extends EntityBean {

    @Column(name = "fecha", nullable = false)
    @NotNull
    private Date fecha;

    @Column(name = "precio_compra", nullable = false)
    @NotNull
    private double precioCompra;

    @Column(name = "cantidad", nullable = false)
    @NotNull
    private int cantidad;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_inventario")
    private InventarioEntity inventarioEntity;
}
