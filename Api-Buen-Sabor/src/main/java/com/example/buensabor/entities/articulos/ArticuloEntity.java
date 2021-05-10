package com.example.buensabor.entities.articulos;

import com.example.buensabor.entities.EntityBean;
import com.example.buensabor.entities.rubro.RubroEntity;
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
public class ArticuloEntity extends EntityBean {

    @Column(name = "denominacion", length = 65, nullable = false)
    @NotNull
    private String denominacion;

    @Column(name = "imagen", length = 65, nullable = false)
    @NotNull
    private String imagen;

    @JsonIgnore
    @OneToMany(mappedBy = "articulo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ArticuloElaboradoDetalleEntity> articuloElaboradoDetalleEntities = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "articulo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<RecetaElaboradoEntity> recetaElaboradoEntities = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_tipo_articulo")
    private TipoArticuloEntity tipoArticuloEntity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_rubro")
    private RubroEntity rubroEntity;
}
