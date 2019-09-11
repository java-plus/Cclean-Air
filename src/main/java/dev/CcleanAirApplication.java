package dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.entities.Commune;
import dev.entities.Utilisateur;

@SpringBootApplication
public class CcleanAirApplication {

	public static void main(String[] args) {
		SpringApplication.run(CcleanAirApplication.class, args);

		Commune commune = new Commune();
		commune.setNom("rennes");

		Utilisateur u1 = new Utilisateur();
		u1.setPrenom("jean-pierre");

	}

}
