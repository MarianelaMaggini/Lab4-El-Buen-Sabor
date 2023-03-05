package com.example.buensabor.entities.comprobantes;

import com.example.buensabor.entities.base.BaseEntity;
import com.example.buensabor.entities.domicilio.Domicilio;
import com.example.buensabor.security.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "pedido")
public class Pedido extends BaseEntity implements Serializable {

    @Column(name = "hora_estimada_fin", nullable = false)
    @NotNull
    private Date horaEstimadaFin;

    @Transient
    private double total;

    @JsonIgnore
    @OneToMany(mappedBy = "pedido")
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_tipo_envio")
    private TipoEnvio tipoEnvio;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_estado")
    private PedidoEstado pedidoEstado;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_domicilio")
    private Domicilio domicilio;

    @Column(name = "forma_pago", length = 50, nullable = false)
    @NotNull
    private String formaPago;

    public Pedido(Long id, Date horaEstimadaFin, double total, Domicilio domicilio, TipoEnvio tipoEnvio, Usuario usuario, PedidoEstado pedidoEstado, String formaPago) {
        this.id = id;
        this.horaEstimadaFin = horaEstimadaFin;
        this.total = total;
        this.domicilio = domicilio;
        this.tipoEnvio = tipoEnvio;
        this.usuario = usuario;
        this.pedidoEstado = pedidoEstado;
        this.formaPago = formaPago;
    }
}
