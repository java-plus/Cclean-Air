package dev.services;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.entities.Utilisateur;
import dev.repositories.IUtilisateurRepository;

/**
 * @author Guillaume
 *
 */
class UtilisateurServiceTest {

	/**
	 * Test method for
	 * {@link dev.services.UtilisateurService#isEmailExistant(java.lang.String)}.
	 */
	@Test
	void testIsEmailExistant() {
		IUtilisateurRepository mockedDao = Mockito.mock(IUtilisateurRepository.class);

		Mockito.when(mockedDao.findByEmailIgnoreCase("test@mail.com")).thenReturn(Optional.ofNullable(null));

		UtilisateurService service = new UtilisateurService(null, mockedDao, null, null, null);
		assertFalse(service.isEmailExistant("test@mail.com"));

		Utilisateur u = new Utilisateur();
		Mockito.when(mockedDao.findByEmailIgnoreCase("test2@mail.com")).thenReturn(Optional.of(u));
		assertTrue(service.isEmailExistant("test2@mail.com"));

	}

	/**
	 * Test method for {@link dev.services.UtilisateurService#visualiserProfil()}.
	 */
	@Test
	void testVisualiserProfil() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link dev.services.UtilisateurService#modifierProfil(dev.controllers.dto.ProfilModificationPost)}.
	 */
	@Test
	void testModifierProfil() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dev.services.UtilisateurService#validationAdmin()}.
	 */
	@Test
	void testValidationAdmin() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link dev.services.UtilisateurService#recupererCommunesUtilisateurCourantVeutNotifications()}.
	 */
	@Test
	void testRecupererCommunesUtilisateurCourantVeutNotifications() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link dev.services.UtilisateurService#recupererCommunesUtilisateurAvecIndicateur()}.
	 */
	@Test
	void testRecupererCommunesUtilisateurAvecIndicateur() {
		fail("Not yet implemented");
	}

}
