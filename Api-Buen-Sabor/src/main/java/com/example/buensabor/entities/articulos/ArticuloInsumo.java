package com.example.buensabor.entities.articulos;

import com.example.buensabor.entities.EntityBean;
import com.example.buensabor.entities.cliente.Usuario;
import com.example.buensabor.entities.comprobantes.DetalleFactura;
import com.example.buensabor.entities.comprobantes.DetallePedido;
import com.example.buensabor.entities.rubros.RubroArticulo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "articulo_insumo")
public class ArticuloInsumo extends EntityBean {

    @Column(name = "denominacion", length = 65)
    @NotNull
    private String denominacion;

    @Column(name = "stock_actual")
    @NotNull
    private double stockActual;

    @Column(name = "stock_minimo")
    @NotNull
    private double stockMinimo;

    @Column(name = "unidad_medida")
    @NotNull
    private String unidadMedida;

    @Column(name = "es_insumo")
    @NotNull
    private boolean esInsumo;

    @JsonIgnore
    @OneToMany(mappedBy = "articuloInsumo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<HistoricoArtInsumo> historicoArtInsumos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "articuloInsumo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ArticuloManufacturadoDetalle> articuloManufacturadoDetalles = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "articuloInsumo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "articuloInsumo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<DetalleFactura> detalleFacturas = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY,cascade ={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "id_rubro_articulo")
    private RubroArticulo rubroArticulo;
}
