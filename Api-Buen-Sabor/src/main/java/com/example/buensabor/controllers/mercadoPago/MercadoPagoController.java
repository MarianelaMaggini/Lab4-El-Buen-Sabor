package com.example.buensabor.controllers.mercadoPago;

import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Item;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MercadoPagoController {
    //MercadoPago.SDK.setAccessToken(new ConfiguracionEntity().getTOKEN_MERCADO_PAGO());
    @GetMapping("/createAndRedirect")
    public String createAndRedirect() throws MPException {
        Preference preference = new Preference();
        // Crea un ítem en la preferencia
        Item item = new Item();
        item.setTitle("Mi producto")
                .setQuantity(1)
                .setUnitPrice((float) 75.56);
        preference.appendItem(item);
        Preference result = preference.save();
        System.out.println(result.getSandboxInitPoint());
        return "redirect:" + result.getSandboxInitPoint();
    }
}
