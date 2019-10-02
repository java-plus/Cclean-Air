package dev;

import dev.entities.Statut;
import dev.entities.Utilisateur;
import dev.repositories.IUtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	/**
	 * isDataInit : Boolean
	 */
	@Value("${data.init}")
	private Boolean isDataInit;

	/** passwordEncoder : PasswordEncoder */
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Méthode init qui permet ici d'insérer des données dès le lancement de l'application.
	 */
	@EventListener(ContextRefreshedEvent.class)
	public void init() {
		if(isDataInit) {
			List<Statut> listeStatuts = new ArrayList<>();
			listeStatuts.add(Statut.MEMBRE);
			var user = new Utilisateur("user", "user", "user@mail.com", passwordEncoder.encode("user"),
					listeStatuts, Boolean.FALSE, Integer.valueOf(0), ZonedDateTime.now(), null, null);

			List<Statut> listeStatuts2 = new ArrayList<>();
			listeStatuts2.add(Statut.MEMBRE);
			listeStatuts2.add(Statut.ADMINISTRATEUR);
			var admin = new Utilisateur("admin", "admin", "admin@mail.com", passwordEncoder.encode("admin"),
					listeStatuts2, Boolean.FALSE, 0, ZonedDateTime.now(), null, null);

			utilisateurRepository.save(user);
			utilisateurRepository.save(admin);
		}

	}

	/**
	 * Méthode main : méthode principale de lancement de l'application.
	 * @param args : String[]
	 */
	public static void main(String[] args) {
		SpringApplication.run(CcleanAirApplication.class, args);
	}

}
