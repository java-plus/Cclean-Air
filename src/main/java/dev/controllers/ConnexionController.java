package dev.controllers;

import dev.controllers.dto.InfosConnexion;
import dev.entities.Utilisateur;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Cécile controller qui permet de gérer la connexion sur l'application
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
	 * Méthode qui permet de se connecter l'utilisateur en vérifiant s'il est admin
	 * ou non et d'installer le cookie dans le navigateur correspondant dans le
	 * navigateur
	 *
	 * @param infos
	 * @return
	 */
	@PostMapping(value = "/connexion")
	public ResponseEntity<?> connexion(@RequestBody InfosConnexion infos) {

//		return this.utilisateurRepository.findByEmailIgnoreCase(infos.getEmail())
//				.filter(utilisateur -> passwordEncoder.matches(infos.getMotDePasse(), utilisateur.getMotDePasse()))
//				.map(utilisateur -> {
//					Map<String, Object> infosSupplementaireToken = new HashMap<>();
//					infosSupplementaireToken.put("statuts", utilisateur.getStatut());
//					infosSupplementaireToken.put("email", utilisateur.getEmail());
//					String jetonJTW = Jwts.builder().setSubject(utilisateur.getEmail())
//							.addClaims(infosSupplementaireToken)
//							.setExpiration(new Date(System.currentTimeMillis() + EXPIRES_IN * 1000))
//							.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, SECRET).compact();
//					ResponseCookie tokenCookie = ResponseCookie.from(TOKEN_COOKIE, jetonJTW).httpOnly(true)
//							.maxAge(EXPIRES_IN * 1000).path("/").build();
//					LOGGER.info("Le cookie est créé");
//					return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, tokenCookie.toString()).build();
//				}).orElseGet(() -> {
//					Utilisateur utilisateur = utilisateurRepository.findByEmailIgnoreCase(infos.getEmail())
//							.orElseThrow(() -> new UtilisateurInvalideException("Erreur : utilisateur non trouvé."));
//					if (!passwordEncoder.matches(infos.getMotDePasse(), utilisateur.getMotDePasse())) {
//						Integer compteur = utilisateur.getCompteurTentativesConnexion();
//						utilisateur.setCompteurTentativesConnexion(++compteur);
//
//						// return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//					}
//
//					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//				});
	}
}