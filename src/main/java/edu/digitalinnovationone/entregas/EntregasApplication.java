package edu.digitalinnovationone.entregas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients//hablita o feign na api
@SpringBootApplication
public class EntregasApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntregasApplication.class, args);
	}
}
