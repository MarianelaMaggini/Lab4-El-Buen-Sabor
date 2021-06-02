package com.example.buensabor.entities.articulos;

import com.example.buensabor.entities.EntityBean;
import com.example.buensabor.entities.comprobantes.DetalleFactura;
import com.example.buensabor.entities.comprobantes.DetallePedido;
import com.example.buensabor.entities.rubro.Rubro;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "articulo")
public class Articulo extends EntityBean {

    @Column(name = "denominacion", length = 65, nullable = false)
    @NotNull
    private String denominacion;

    @Column(name = "imagen", length = 65, nullable = false)
    @NotNull
    private String imagen;

    @JsonIgnore
    @OneToMany(mappedBy = "articulo")
    private List<DetalleFactura> detalleFacturas = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "articulo")
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "articulo")
    private List<ArticuloElaboradoDetalle> articuloElaboradoDetalles = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "articulo")
    private List<RecetaElaborado> recetaElaborados = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_tipo_articulo")
    private TipoArticulo tipoArticulo;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_rubro")
    private Rubro rubro;
}