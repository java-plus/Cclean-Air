package dev;

import dev.entities.Statut;
import dev.entities.Utilisateur;
import dev.repositories.ICodePostalRepository;
import dev.repositories.ICommuneRepository;
import dev.repositories.IUtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class CcleanAirApplication {

	/** utilisateurRepository : IUtilisateurRepository */
	@Autowired
	IUtilisateurRepository utilisateurRepository;

	/** communeRepository : ICommuneRepository */
	@Autowired
	ICommuneRepository communeRepository;

	/** codePostalRepository : ICodePostalRepository */
	@Autowired
	ICodePostalRepository codePostalRepository;

	/** passwordEncoder : PasswordEncoder */
	@Autowired
	private PasswordEncoder passwordEncoder;

	@EventListener(ContextRefreshedEvent.class)
	public void init() {

		// création utilisateurs
		List<Statut> listeStatuts = new ArrayList<>();
		listeStatuts.add(Statut.ADMINISTRATEUR);
		listeStatuts.add(Statut.MEMBRE);
		var user1 = new Utilisateur("Cussonet", "Simon", "simon.cussonet@gmail.com", passwordEncoder.encode("1234"),
				listeStatuts, Boolean.TRUE, Integer.valueOf(0), ZonedDateTime.now(), null, null);

		List<Statut> listeStatuts2 = new ArrayList<>();
		listeStatuts.add(Statut.MEMBRE);
		var user2 = new Utilisateur("Nombidon", "Raoul", "raoul.nomBidon@gmail.com", passwordEncoder.encode("5678"),
				listeStatuts2, Boolean.FALSE, Integer.valueOf(0), ZonedDateTime.now(), null, null);

		// Création communes
		/*var commune1 = new Commune("Quimper", Long.valueOf(50000), "29000", Double.valueOf(-1), Double.valueOf(2));
		communeRepository.save(commune1);
		List<CodePostal> CP = new ArrayList<>();
		user1.setCommune(commune1);
		var c1 = new CodePostal("29000", commune1);
		codePostalRepository.save(c1);
		CP.add(c1);
		commune1.setCodesPostaux(CP);

		var commune2 = new Commune("Lannion", Long.valueOf(25000), "22300", Double.valueOf(-2), Double.valueOf(3));
		communeRepository.save(commune2);
		user2.setCommune(commune2);
		List<CodePostal> CP2 = new ArrayList<>();
		var c2 = new CodePostal("22300", commune2);
		codePostalRepository.save(c2);
		CP2.add(c2);
		commune2.setCodesPostaux(CP2);

		utilisateurRepository.save(user1);
		utilisateurRepository.save(user2);*/

	}

	public static void main(String[] args) {
		SpringApplication.run(CcleanAirApplication.class, args);

	}

}
