package dev;

import dev.entities.Utilisateur;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CcleanAirApplication {



	public static void main(String[] args) {
		SpringApplication.run(CcleanAirApplication.class, args);
	}

}
