package com.example.buensabor.entities.articulos;

import com.example.buensabor.entities.EntityBean;
import com.example.buensabor.entities.rubros.Rubro;
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
    @OneToMany(mappedBy = "articulo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ArticuloElaboradoDetalle> articuloElaboradoDetalles = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "articulo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<RecetaElaborado> recetaElaborados = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_tipo_articulo")
    private TipoArticulo tipoArticulo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_rubro")
    private Rubro rubro;
}
