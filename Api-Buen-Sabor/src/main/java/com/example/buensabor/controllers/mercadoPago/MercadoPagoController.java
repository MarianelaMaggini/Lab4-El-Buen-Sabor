package com.example.buensabor.controllers.mercadoPago;

import com.example.buensabor.services.configuracion.ConfiguracionService;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class MercadoPagoController {

    private final ConfiguracionService configuracionService;

    @Autowired
    public MercadoPagoController(ConfiguracionService configuracionService) {
        this.configuracionService = configuracionService;
    }
    @GetMapping("/createAndRedirect")
    public ResponseEntity<?> createAndRedirect(@RequestParam("precio") float precio) throws MPException {
        MercadoPago.SDK.setAccessToken(this.configuracionService.getToken());
        Preference preference = new Preference();
        // Crea un ítem en la preferencia
        Item item = new Item();
        item.setTitle("Mi producto")
                .setQuantity(1)
                .setUnitPrice(precio);
        preference.appendItem(item);
        Preference result = preference.save();
        System.out.println(result.getSandboxInitPoint());
        return new ResponseEntity<>(result.getSandboxInitPoint(), HttpStatus.OK);
    }
}
