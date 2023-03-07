package com.example.buensabor.entities.comprobantes;

import com.example.buensabor.entities.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tipo_envio")
public class TipoEnvio extends BaseEntity {

    @Column(name = "denominacion", length = 65, nullable = false)
    @NotNull
    private String denominacion;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoEnvio")
    private List<Pedido> pedidos = new ArrayList<>();
}
