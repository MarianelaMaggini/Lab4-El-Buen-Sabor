package com.example.buensabor.controllers.mercadoPago;

import com.example.buensabor.services.configuracion.ConfiguracionService;
import com.google.gson.GsonBuilder;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class MercadoPagoController {
    private final ConfiguracionService configuracionService;

    @Autowired
    public MercadoPagoController(ConfiguracionService configuracionService) {
        this.configuracionService = configuracionService;
    }

    //MercadoPago.SDK.setAccessToken(new Configuracion().getTOKEN_MERCADO_PAGO());
    @GetMapping("/createAndRedirect")
    public ResponseEntity<?> createAndRedirect(@RequestParam("precio") float precio) throws MPException {
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

    @GetMapping("/success")
    public String success(HttpServletRequest request,
                          @RequestParam("collection_id") String collectionId,
                          @RequestParam("collection_status") String collectionStatus,
                          @RequestParam("external_reference") String externalReference,
                          @RequestParam("payment_type") String paymentType,
                          @RequestParam("merchant_order_id") String merchantOrderId,
                          @RequestParam("preference_id") String preferenceId,
                          @RequestParam("site_id") String siteId,
                          @RequestParam("processing_mode") String processingMode,
                          @RequestParam("merchant_account_id") String merchantAccountId,
                          Model model
    ) throws MPException {
        Payment payment = com.mercadopago.resources.Payment.findById(collectionId);
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(payment));
        model.addAttribute("payment", payment);
        return "ok";
    }
}
