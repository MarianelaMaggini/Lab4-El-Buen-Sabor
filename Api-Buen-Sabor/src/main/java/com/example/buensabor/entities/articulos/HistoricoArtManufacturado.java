package com.example.buensabor.entities.articulos;

import com.example.buensabor.entities.EntityBean;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class HistoricoArtManufacturado extends EntityBean {

    @Column (name = "fecha")
    private Date fecha;

    @Column(name = "precio_venta")
    private double precioVenta;

    @ManyToOne(fetch = FetchType.LAZY,cascade ={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "id_art_manufacturado")
    private ArticuloManufacturado articuloManufacturado;
}
