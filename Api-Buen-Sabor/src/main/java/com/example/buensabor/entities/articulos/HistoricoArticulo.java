package com.example.buensabor.entities.articulos;

import com.example.buensabor.entities.EntityBean;
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
public class HistoricoArticulo extends EntityBean {

    @Column(name = "fecha")
    @NotNull
    private Date fecha;

    @Column(name = "precio_compra")
    @NotNull
    private double precioCompra;

    @Column(name = "cantidad")
    @NotNull
    private int cantidad;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_inventario")
    private Inventario inventario;
}
