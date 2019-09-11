package dev;

import dev.entities.Administrateur;
import dev.entities.Statut;
import dev.entities.Utilisateur;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CcleanAirApplication {

	Utilisateur utilisateur = new Administrateur("peyras", "cecile", "cecile@test.fr", "test", Arrays.asList(Statut.MEMBRE), true, 0);

	public static void main(String[] args) {
		SpringApplication.run(CcleanAirApplication.class, args);
	}

}
