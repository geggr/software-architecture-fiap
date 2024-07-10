package br.com.fiap.totem_express;

import org.springframework.boot.SpringApplication;

public class TestTotemExpressApplication {

	public static void main(String[] args) {
		SpringApplication.from(TotemExpressApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
