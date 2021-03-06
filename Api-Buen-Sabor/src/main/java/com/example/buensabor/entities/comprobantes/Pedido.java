package com.example.buensabor.entities.comprobantes;

import com.example.buensabor.entities.domicilio.Domicilio;
import com.example.buensabor.security.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedido")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_pedido")
    private long numeroPedido;

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

    public Pedido(long numeroPedido, @NotNull Date horaEstimadaFin, double total, Domicilio domicilio, TipoEnvio tipoEnvio, Usuario usuario, PedidoEstado pedidoEstado, String formaPago) {
        this.numeroPedido = numeroPedido;
        this.horaEstimadaFin = horaEstimadaFin;
        this.total = total;
        this.domicilio = domicilio;
        this.tipoEnvio = tipoEnvio;
        this.usuario = usuario;
        this.pedidoEstado = pedidoEstado;
        this.formaPago = formaPago;
    }

}
