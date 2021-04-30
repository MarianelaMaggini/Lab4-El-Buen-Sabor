package com.example.buensabor.entities.articulos;

import com.example.buensabor.entities.EntityBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "historico_articulo_manufacturado")
public class HistoricoArtManufacturado extends EntityBean {

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "precio_venta")
    private double precioVenta;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_art_manufacturado")
    private ArticuloManufacturado articuloManufacturado;
}
