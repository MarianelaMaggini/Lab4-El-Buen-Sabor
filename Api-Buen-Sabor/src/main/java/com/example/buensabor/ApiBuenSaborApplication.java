package com.example.buensabor;

import com.example.buensabor.entities.Configuracion;
import com.mercadopago.MercadoPago;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiBuenSaborApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiBuenSaborApplication.class, args);
	}
	@Override
	public void run(String... args)throws Exception {
		MercadoPago.SDK.setAccessToken(new Configuracion().getTOKEN_MERCADO_PAGO());
	}

}
