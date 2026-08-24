package dev.daniel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "dev.daniel")
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
