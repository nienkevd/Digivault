package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.repository.JdbcKlantDAO;
import nl.hva.c25.team1.digivault.repository.KlantDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class KlantServiceTest {

    @MockBean
    private static JdbcKlantDAO mockDAO = Mockito.mock(JdbcKlantDAO.class);
    KlantService serviceUnderTest = new KlantService(mockDAO);


    @Test
    void vindKlantOpKlantID() {
        Klant expected = new Klant(5,"132456789", LocalDate.parse("1955-01-01"));
        Mockito.when(mockDAO.vindKlantOpKlantId(5)).thenReturn(expected);

        Klant actual = serviceUnderTest.vindKlantOpKlantID(5);
        assertThat(actual).isNotNull().isEqualTo(expected);
    }

    @Test
    void updateKlant() {
        Klant bestaandeKlant = new Klant(5,"132456789", LocalDate.parse("1955-02-01"));
        Mockito.when(mockDAO.vindKlantOpKlantId(5)).thenReturn(bestaandeKlant);

        String actual = serviceUnderTest.updateKlant(bestaandeKlant);
        assertThat(actual).contains("geslaagd");
    }

//    @Test
//    void bewaarKlant() {
//        Klant nieuw = new Klant("267827227", LocalDate.now());
//        Mockito.when(mockDAO.bewaarKlantMetSK(nieuw)).thenReturn(nieuw);
//        Klant actual = serviceUnderTest.bewaarKlant(nieuw);
//        assertThat(actual.getKlantId()).isPositive();
//    }

}