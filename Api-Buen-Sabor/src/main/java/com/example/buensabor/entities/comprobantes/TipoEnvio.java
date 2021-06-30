package com.example.buensabor.entities.comprobantes;

import com.example.buensabor.entities.EntityBean;
import com.example.buensabor.entities.articulos.Articulo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tipo_envio")
public class TipoEnvio extends EntityBean {

    @Column(name = "denominacion", length = 65, nullable = false)
    @NotNull
    private String denominacion;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoEnvio")
    private List<Pedido> pedidos = new ArrayList<>();
}
