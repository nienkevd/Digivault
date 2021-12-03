package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.repository.JdbcKlantDAO;
import nl.hva.c25.team1.digivault.repository.KlantDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class KlantServiceTest {

    @Test
    void bewaarKlant() {
        Klant nieuweKlant = new Klant(6, "122334778", LocalDate.parse("1985-12-07"));
        JdbcKlantDAO mockDAO = Mockito.mock(JdbcKlantDAO.class);
    }

    @Test
    void vindKlantOpKlantID() {
        Klant expected = new Klant(5,"132456789", LocalDate.parse("1955-01-01"));
        JdbcKlantDAO mockDAO = Mockito.mock(JdbcKlantDAO.class);
        Mockito.when(mockDAO.vindKlantOpKlantId(5)).thenReturn(expected);

        KlantService serviceUnderTest = new KlantService(mockDAO);
        Klant actual = serviceUnderTest.vindKlantOpKlantID(5);
        assertThat(actual).isNotNull().isEqualTo(expected);
    }

    @Test
    void vindAlleKlanten() {
    }

    @Test
    void updateKlant() {
        Klant bestaandeKlant = new Klant(5,"132456789", LocalDate.parse("1955-02-01"));
        JdbcKlantDAO mockDAO = Mockito.mock(JdbcKlantDAO.class);
        Mockito.when(mockDAO.vindKlantOpKlantId(5)).thenReturn(bestaandeKlant);

        KlantService serviceUnderTest = new KlantService(mockDAO);
        String actual = serviceUnderTest.updateKlant(bestaandeKlant);
        assertThat(actual).contains("geslaagd");
    }

}