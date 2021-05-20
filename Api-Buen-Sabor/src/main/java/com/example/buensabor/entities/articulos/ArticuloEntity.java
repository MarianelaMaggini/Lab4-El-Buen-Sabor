package com.example.buensabor.entities.articulos;

import com.example.buensabor.entities.EntityBean;
import com.example.buensabor.entities.comprobantes.DetalleFacturaEntity;
import com.example.buensabor.entities.comprobantes.DetallePedidoEntity;
import com.example.buensabor.entities.rubro.RubroEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class ArticuloEntity extends EntityBean {

    @Column(name = "denominacion", length = 65, nullable = false)
    @NotNull
    private String denominacion;

    @Column(name = "imagen", length = 65, nullable = false)
    @NotNull
    private String imagen;

    //@JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "articuloEntity")
    private List<DetalleFacturaEntity> detalleFacturaEntities = new ArrayList<>();

    //@JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "articuloEntity")
    private List<DetallePedidoEntity> detallePedidoEntities = new ArrayList<>();

    //@JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "articuloEntity")
    private List<ArticuloElaboradoDetalleEntity> articuloElaboradoDetalleEntities = new ArrayList<>();

    //@JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "articuloEntity")
    private List<RecetaElaboradoEntity> recetaElaboradoEntities = new ArrayList<>();

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_tipo_articulo")
    private TipoArticuloEntity tipoArticuloEntity;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_rubro")
    private RubroEntity rubroEntity;
}
