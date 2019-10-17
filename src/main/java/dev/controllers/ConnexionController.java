package dev.controllers;

import dev.controllers.dto.InfosConnexion;
import dev.entities.Utilisateur;
import dev.exceptions.ConnexionInvalideException;
import dev.exceptions.UtilisateurInvalideException;
import dev.repositories.IUtilisateurRepository;
import dev.services.CommuneService;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

/**
 * @author Cécile&Bilel controller qui permet de gérer la connexion sur
 *         l'application
 */
@RestController
public class ConnexionController {
	/**
	 * EXPIRES_IN : Integer
	 */
	@Value("${jwt.expires_in}")
	private Integer EXPIRES_IN;
	/**
	 * TOKEN_COOKIE : String
	 */
	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;
	/**
	 * SECRET : String
	 */
	@Value("${jwt.secret}")
	private String SECRET;
	/**
	 * utilisateurRepository : IUtilisateurRepository
	 */
	@Autowired
	IUtilisateurRepository utilisateurRepository;
	/**
	 * passwordEncoder : PasswordEncoder
	 */
	@Autowired
	PasswordEncoder passwordEncoder;
	/**
	 * LOGGER : Logger
	 */
	private final Logger LOGGER = LoggerFactory.getLogger(ConnexionController.class);

	/**
	 * Méthode qui permet de connecter l'utilisateur en vérifiant s'il est admin ou
	 * non et d'installer le cookie dans le navigateur correspondant. Elle permet
	 * aussi d'incrémenter un compteur si la tentative de connexion est échouée,
	 * bloquant celle-ci après 5 tentatives en moins de 30 minutes.
	 * 
	 * @param infos : infos de connexion (mail et mdp)
	 * @return un status/réponse différent selon la réussite ou non de connexion.
	 */
	@PostMapping(value = "/connexion")
	@Transactional
	public ResponseEntity<?> connexion(@RequestBody InfosConnexion infos) throws ConnexionInvalideException {

		ZonedDateTime date = ZonedDateTime.now();

		return this.utilisateurRepository.findByEmailIgnoreCase(infos.getEmail())
				.filter(utilisateur -> passwordEncoder.matches(infos.getMotDePasse(), utilisateur.getMotDePasse()))
				.map(utilisateur -> {
					Map<String, Object> infosSupplementaireToken = new HashMap<>();

					infosSupplementaireToken.put("statuts", utilisateur.getStatut());
					infosSupplementaireToken.put("email", utilisateur.getEmail());

					utilisateur.setCompteurTentativesConnexion(0);
					utilisateur.setDateDerniereConnexion(date);

					String jetonJTW = Jwts.builder().setSubject(utilisateur.getEmail())
							.addClaims(infosSupplementaireToken)
							.setExpiration(new Date(System.currentTimeMillis() + EXPIRES_IN * 1000))
							.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, SECRET).compact();
					ResponseCookie tokenCookie = ResponseCookie.from(TOKEN_COOKIE, jetonJTW).httpOnly(true)
							.maxAge(EXPIRES_IN * 1000).path("/").build();
					LOGGER.info("Le cookie est créé");
					return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, tokenCookie.toString()).build();
				}).orElseGet(() -> {
					Utilisateur utilisateur = utilisateurRepository.findByEmailIgnoreCase(infos.getEmail())
							.orElseThrow(() -> new ConnexionInvalideException("Erreur : utilisateur non trouvé."));

					if (!passwordEncoder.matches(infos.getMotDePasse(), utilisateur.getMotDePasse())) {
						if (date.getMinute() - utilisateur.getDateDerniereConnexion().getMinute() >= 30) {
							utilisateur.setCompteurTentativesConnexion(0);
							utilisateur.setDateDerniereConnexion(date);
						}
						if (utilisateur.getCompteurTentativesConnexion() > 4
								&& (date.getMinute() - utilisateur.getDateDerniereConnexion().getMinute() < 30)) {
							return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
						} else {
							Integer compteur = utilisateur.getCompteurTentativesConnexion();
							utilisateur.setCompteurTentativesConnexion(++compteur);

							utilisateur.setDateDerniereConnexion(date);
						}
					}
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();					
							});
	}

	/**
	 * Contrôleur gérant la requête GET permettant de valider le fait que
	 * l'utilisateur est déjà authentifié (cookie présent).
	 *
	 * @return : ResponseEntity<Void> Réponse au body vide avec statut 200.
	 */
	@GetMapping("/connexion")
	public ResponseEntity<Void> reqVerificationEstConnecte() {
		return ResponseEntity.ok().build();
	}
}
