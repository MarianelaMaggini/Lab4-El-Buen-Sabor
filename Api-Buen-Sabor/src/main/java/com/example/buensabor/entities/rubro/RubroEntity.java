package com.example.buensabor.entities.rubro;


import com.example.buensabor.entities.EntityBean;
import com.example.buensabor.entities.articulos.ArticuloEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rubro")
public class RubroEntity extends EntityBean {

    @Column(name = "denominacion", length = 65, nullable = false)
    @NotNull
    private String denominacion;

    @JsonIgnore
    @OneToMany(mappedBy = "rubroEntity", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ArticuloEntity> articuloEntities = new ArrayList<>();
}
