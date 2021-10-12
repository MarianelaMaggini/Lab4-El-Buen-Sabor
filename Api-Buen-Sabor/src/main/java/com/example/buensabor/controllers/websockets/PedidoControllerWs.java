package com.example.buensabor.controllers.websockets;

import com.example.buensabor.dtos.Message;
import com.example.buensabor.entities.comprobantes.Pedido;
import com.example.buensabor.services.comprobantes.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class PedidoControllerWs {
    private final PedidoService pedidoService;

    @Autowired
    public PedidoControllerWs(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @MessageMapping("/pedidos")
    @SendTo("/topic/pedido")
    public Pedido getPedido(Pedido pedido){
        return new Pedido(
                pedido.getNumeroPedido(),
                pedido.getHoraEstimadaFin(),
                pedido.getTotal(),
                pedido.getDomicilio(),
                pedido.getTipoEnvio(),
                pedido.getUsuario(),
                pedido.getPedidoEstado(),
                pedido.getFormaPago()
        );
    }

    @MessageMapping("/mensajes")
    @SendTo("/topic/mensaje")
    public Message getMessage(Message message){
        return new Message(message.getMessage());
    }

}
