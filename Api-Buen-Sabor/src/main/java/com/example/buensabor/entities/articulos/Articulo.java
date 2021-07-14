package com.example.buensabor.entities.articulos;

import com.example.buensabor.entities.EntityBean;
import com.example.buensabor.entities.comprobantes.DetalleFactura;
import com.example.buensabor.entities.comprobantes.DetallePedido;
import com.example.buensabor.entities.rubro.Rubro;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Clase Articulo almacenará todos los artículos y tiene muchos detalles artículos, recetas, históricos, detalles
 * pedidos, detalles facturas. También pertenece a un rubro, a un tipo artículo y a una unidad de medida
 * Se utiliza anotaciones lombok para constructores, getters and setters
 * Se utiliza anotaciones para la persistencia en la base de datos
 * @author Maggini - Panella - Tarditi
 */
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

    @Transient
    private Double precioVenta;

    @Column(name = "fecha_baja")
    private Date fechaBaja;

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
    private List<HistoricoArticulo> historicoArticulos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "articulo")
    private List<RecetaElaborado> recetaElaborados = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_tipo_articulo")
    private TipoArticulo tipoArticulo;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_rubro")
    private Rubro rubro;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_unidad_medida")
    private UnidadMedida unidadMedida;

    /**
     * Se navega hasta el historico de articulos y se le asigna al precio de venta
     * el precio de compra * el 50%
     *
     * @return precioVenta
     */
    public Double getPrecioNoElaborado() {
        for (HistoricoArticulo h : historicoArticulos) {
            precioVenta = h.getPrecioCompra() * 1.5;
        }
        return precioVenta;
    }

}
