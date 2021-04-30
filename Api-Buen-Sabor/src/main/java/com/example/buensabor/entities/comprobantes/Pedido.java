package com.example.buensabor.entities.comprobantes;

import com.example.buensabor.entities.MercadoPagoDatos;
import com.example.buensabor.entities.cliente.Domicilio;
import com.example.buensabor.entities.cliente.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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
    @Column(name = "numero_factura")
    private int numero;

    @Column(name = "fecha")
    @NotNull
    private Date fecha;

    @Column(name = "estado")
    @NotNull
    private int estado;

    @Column(name = "hora_estimada_fin")
    @NotNull
    private Date horaEstimadaFin;

    @Column(name = "tipo_envio")
    @NotNull
    private int tipoEnvio;

    @Transient
    private double total;

    @JsonIgnore
    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_domicilio")
    private Domicilio domicilio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "numero_factura")
    private Factura factura;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_mercado_pago")
    private MercadoPagoDatos mercadoPagoDatos;
}
