package nl.hva.c25.team1.digivault.service;


import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.repository.JdbcKlantDAO;
import nl.hva.c25.team1.digivault.repository.RootRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * Testen bij KlantService
 *
 * @author Anneke, studentnummer 500889251
 * @since 5-12-2021
 */

class KlantServiceTest {

    @MockBean
    private static JdbcKlantDAO mockDAO = Mockito.mock(JdbcKlantDAO.class);
    @MockBean
    private static RootRepository mockRoot = Mockito.mock(RootRepository.class);

    private KlantService serviceUnderTest = new KlantService(mockDAO, mockRoot);
    private Klant expected = new Klant(10,"080772547", LocalDate.parse("1983-07-05"));

    @Test
    void vindKlantOpKlantID() {
        Mockito.when(mockRoot.vindKlantOpId(10)).thenReturn(expected);

        Klant actual = serviceUnderTest.vindKlantOpKlantID(10);
        assertThat(actual).isNotNull().isEqualTo(expected);
    }

    @Test
    void vindKlantOpEmail() {
        Mockito.when(mockDAO.vindKlantOpEmailadres("marieke@gmail.com")).thenReturn(expected);

        Klant actual = serviceUnderTest.vindKlantOpEmail("marieke@gmail.com");
        assertThat(actual).isNotNull().isEqualTo(expected);
    }

    @Test
    void updateNietBestaandeKlant() {
        Klant nietBestaandeKlant = new Klant(25,"132456789", LocalDate.parse("1955-02-01"));
        Mockito.when(mockDAO.vindKlantOpKlantId(25)).thenReturn(null);

        String actual = serviceUnderTest.updateKlant(nietBestaandeKlant);
        String expected = "Klant bestaat niet, update mislukt.";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void updateBestaandeKlant() {
        Klant bestaandeKlant = new Klant(10,"080772547", LocalDate.parse("1984-07-05"));
        Mockito.when(mockDAO.vindKlantOpKlantId(10)).thenReturn(bestaandeKlant);

        String actual = serviceUnderTest.updateKlant(bestaandeKlant);
        String expected = "Update geslaagd";
        assertThat(actual).isEqualTo(expected);
    }

    // TODO: staan deze validatiestesten op de juiste plek?
//    @Test
//    void validatieMailadres() {
//        System.out.println();
//        boolean expected = false;
//        boolean actual = validatieMailadres("gwkroeze@outlook.com");
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    boolean validatieMailadres(String mailadres) {
////        boolean expected = false;
////        boolean actual = validatieMailadres("gwkroeze@outlook.com");
////        assertEquals(expected, actual);
//        return false;
//    }
//
//    @Test
//    boolean validatiePostcode(String postcode) {
//        String postcode1 = "2717HH";
//        String postcode2 = "2717 HH";
//        String postcode3 = "A123AA";
//        String postcode4 = "@234HH";
//        String postcode5 = "2717#H";
//        boolean expected1 = true;
//        boolean expected2 = false;
//        boolean actual1 = validatiePostcode(postcode1);
//        boolean actual2 = validatiePostcode(postcode2);
//        boolean actual3 = validatiePostcode(postcode3);
//        boolean actual4 = validatiePostcode(postcode4);
//        boolean actual5 = validatiePostcode(postcode5);
//        assertEquals(expected1, actual1);
//        assertEquals(expected2, actual2);
//        assertEquals(expected2, actual3);
//        assertEquals(expected2, actual4);
//        assertEquals(expected2, actual5);
//        return false;
//    }

//    @Test
//    void validateBsn() {
//        //checken voor minder dan 8 digits
//        String bsn = "1234567";
//        boolean actual = serviceUnderTest.validateBsn(bsn);
//        assertThat(actual).isFalse();
//
//        //checken voor meer dan 9 digits
//        String bsn1 = "1234567890";
//        boolean actual1 = serviceUnderTest.validateBsn(bsn1);
//        assertThat(actual1).isFalse();
//
//        //checken voor een bestaande bsn
//        String bsn2 = "635569139";
//        boolean actual2 = serviceUnderTest.validateBsn(bsn2);
//        assertThat(actual2).isTrue();
//
//        //checken voor een niet bestaande bsn die niet voldoet aan elf-proef
//        String bsn3 = "635569138";
//        boolean actual3 = serviceUnderTest.validateBsn(bsn3);
//        assertThat(actual3).isFalse();
//
//    }
//
//    @Test
//    void validatieGeboortedatum() {
//    }
//
//    @Test
//    void validatieWachtwoord() {
//    }


}