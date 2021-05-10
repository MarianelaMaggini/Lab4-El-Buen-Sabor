package com.example.buensabor.entities.comprobantes;

import com.example.buensabor.entities.MercadoPagoDatos;
import com.example.buensabor.entities.cliente.DomicilioEntity;
import com.example.buensabor.entities.cliente.UsuarioEntity;
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
public class PedidoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_pedido")
    private long numero;

    @Column(name = "estado", nullable = false)
    @NotNull
    private int estado;

    @Column(name = "hora_estimada_fin", nullable = false)
    @NotNull
    private Date horaEstimadaFin;

    @Column(name = "tipo_envio", nullable = false)
    @NotNull
    private int tipoEnvio;

    @Transient
    private double total;

    @JsonIgnore
    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<DetallePedidoEntity> detallePedidoEntities = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuarioEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_domicilio")
    private DomicilioEntity domicilioEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "numero_factura")
    private FacturaEntity facturaEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_mercado_pago")
    private MercadoPagoDatos mercadoPagoDatos;
}
