package com.localization.ip;

import com.google.gson.Gson;
import com.localization.ip.model.ResponseModel;
import com.localization.ip.service.OpcionesMapStrategy;
import com.localization.ip.service.OpcionesService;
import com.localization.ip.service.strategies.BuscarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.localization.ip.persistence")
public class IpApplication {

	private final BuscarService buscarService;

	@Autowired
	OpcionesMapStrategy opcionesMapStrategy;

	@Autowired
	public IpApplication(BuscarService buscarService) {
		this.buscarService = buscarService;
	}

	public static void main(String[] args) {
		SpringApplication.run(IpApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args1 -> {
			if (args1.length > 1) {
				String ip = args1[0];
				String operacion = args1[1];
				System.out.println("Parámetro recibido: " + ip);
				System.out.println("Parámetro recibido: " + operacion);


				OpcionesService opcionesService =opcionesMapStrategy.getOpcionesService(operacion);
				ResponseModel responseModel = opcionesService.response(ip);
				Gson json = new Gson();
				String responseConsole = json.toJson(responseModel);
				System.out.println(responseConsole);
			} else {
				System.out.println("No se proporcionaron parámetros.");
			}
		};
	}
}
