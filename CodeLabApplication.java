package com.fresco.codelab;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CodeLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeLabApplication.class, args);
	}

	@Bean
	public ApplicationRunner testLogInitializer() {
		return args -> {
			if (!Files.exists(Paths.get("test.log"))) {
				Files.write(Paths.get("test.log"), Arrays.asList(
					"GET::http://localhost/register::clientIpAddr/127.0.0.1",
					"GET::http://localhost/login::clientIpAddr/127.0.0.1"
				));
			}
		};
	}

}
