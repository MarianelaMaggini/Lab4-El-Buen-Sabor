package com.example.buensabor.entities.articulos;

import com.example.buensabor.entities.EntityBean;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Table(name = "historico_articulo_insumo")
public class HistoricoArtInsumo extends EntityBean {

    @Column(name = "fecha")
    @NotNull
    private Date fecha;

    @Column(name = "precio_venta")
    @NotNull
    private double precioCompra;

    @Column(name = "precio_compra")
    @NotNull
    private double precioVenta;

    @ManyToOne(fetch = FetchType.LAZY,cascade ={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "id_art_insumo")
    private ArticuloInsumo articuloInsumo;
}
