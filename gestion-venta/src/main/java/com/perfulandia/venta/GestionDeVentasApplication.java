package com.perfulandia.venta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.perfulandia"})
public class GestionDeVentasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionDeVentasApplication.class, args);
	}

}
