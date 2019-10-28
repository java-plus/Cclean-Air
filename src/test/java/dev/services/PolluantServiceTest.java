package dev.services;

import java.util.ArrayList;

import org.junit.Test;
import org.mockito.Mockito;

import dev.exceptions.AucuneDonneeException;
import dev.repositories.IPolluantRepository;

public class PolluantServiceTest {

	@Test(expected = AucuneDonneeException.class)
	public void testRecupererNomsPolluantsDeBase() throws AucuneDonneeException {
		IPolluantRepository mockedRepo = Mockito.mock(IPolluantRepository.class);

		Mockito.when(mockedRepo.findAll()).thenReturn(new ArrayList<>());

		PolluantService polluantService = new PolluantService(mockedRepo);

		polluantService.setPolluantRepository(mockedRepo);

		polluantService.recupererNomsPolluantsDeBase();

	}

}
