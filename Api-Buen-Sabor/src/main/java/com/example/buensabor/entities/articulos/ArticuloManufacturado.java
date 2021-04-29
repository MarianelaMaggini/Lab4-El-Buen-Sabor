package com.example.buensabor.entities.articulos;

import com.example.buensabor.entities.EntityBean;
import com.example.buensabor.entities.comprobantes.DetalleFactura;
import com.example.buensabor.entities.comprobantes.DetallePedido;
import com.example.buensabor.entities.rubros.RubroGeneral;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "articulo_manufacturado")
public class ArticuloManufacturado extends EntityBean {

    @Column(name = "tiempo_estimado_cocina")
    @NotNull
    private int tiempoEstimadoCocina;

    @Column(name = "denominacion")
    @NotNull
    private String denominacion;

    @Column(name = "imagen")
    @NotNull
    private String imagen;

    @JsonIgnore
    @OneToMany(mappedBy = "articuloManufacturado", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<HistoricoArtManufacturado> historicoArtManufacturados = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "articuloManufacturado", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<DetalleFactura> detalleFacturas = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "articuloManufacturado", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "articuloManufacturado", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ArticuloManufacturadoDetalle> articuloManufacturadoDetalles = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY,cascade ={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "id_rubro_general")
    private RubroGeneral rubroGeneral;
}
