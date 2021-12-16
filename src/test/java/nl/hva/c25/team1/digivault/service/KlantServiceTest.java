package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.model.Adres;
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

/**
 * Testen bij KlantService
 *
 * @author Anneke, studentnummer 500889251
 * @since 5-12-2021
 */

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

    @Test
    void validatieMailadres() {
        Account bestaandAccount = new Account("gwkroeze@outlook.com", null);

    }

    @Test
    void validatiePostcode() {
        Adres adres = new Adres(null, 0, null, "2717HH", null);
        String postcode = adres.getPostcode();
    }

    @Test
    void validateBsn() {
        //checken voor minder dan 8 digits
        String bsn = "1234567";
        boolean actual = serviceUnderTest.validateBsn(bsn);
        assertThat(actual).isFalse();

        //checken voor meer dan 9 digits
        String bsn1 = "1234567890";
        boolean actual1 = serviceUnderTest.validateBsn(bsn1);
        assertThat(actual1).isFalse();

        //checken voor een bestaande bsn
        String bsn2 = "635569139";
        boolean actual2 = serviceUnderTest.validateBsn(bsn2);
        assertThat(actual2).isTrue();

        //checken voor een niet bestaande bsn die niet voldoet aan elf-proef
        String bsn3 = "635569138";
        boolean actual3 = serviceUnderTest.validateBsn(bsn3);
        assertThat(actual3).isFalse();

    }

    @Test
    void validatieGeboortedatum() {
    }

    @Test
    void validatieWachtwoord() {
    }

//    @Test
//    void bewaarKlant() {
//        Klant nieuw = new Klant("267827227", LocalDate.now());
//        Mockito.when(mockDAO.bewaarKlantMetSK(nieuw)).thenReturn(nieuw);
//        Klant actual = serviceUnderTest.bewaarKlant(nieuw);
//        assertThat(actual.getKlantId()).isPositive();
//    }

}