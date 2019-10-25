package dev.services;

import dev.entities.Polluant;
import dev.exceptions.AucuneDonneeException;
import dev.repositories.IPolluantRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class PolluantServiceTest {

    @Test
    public void sauvegarderPolluant() {

    }

    @Test
    public void recupererPolluantsDeApi() {
    }

    @Test
    public void purgerPolluant() {
    }

    @Test(expected = AucuneDonneeException.class)
    public void test_recupererNomsPolluantsDeBase_pasDeDonnees() throws AucuneDonneeException {

        IPolluantRepository mockedRepository =
                Mockito.mock(IPolluantRepository.class);

        Mockito.when(mockedRepository.findAll()).thenReturn(new ArrayList<>());

        PolluantService polluantService = new PolluantService(mockedRepository);
        polluantService.recupererNomsPolluantsDeBase();
    }

    @Test()
    public void test_recupererNomsPolluantsDeBase_donneesCorrectes() throws AucuneDonneeException {

        IPolluantRepository mockedRepository =
                Mockito.mock(IPolluantRepository.class);

        Polluant p1 = new Polluant("g/L", "Ozone", "O", 23.435);
        Polluant p2 = new Polluant("ml/m3", "Vezul", "Vz3", 0.12);

        List<Polluant> listePolluants = new ArrayList<>();
        listePolluants.add(p1);
        listePolluants.add(p2);

        Mockito.when(mockedRepository.findAll()).thenReturn(listePolluants);

        List<String> listeNomsPolluants = new ArrayList<>();
        listeNomsPolluants.add("Ozone");
        listeNomsPolluants.add("Vezul");

        PolluantService polluantService = new PolluantService(mockedRepository);
        Assert.assertEquals(polluantService.recupererNomsPolluantsDeBase(),
                listeNomsPolluants);
    }
}


