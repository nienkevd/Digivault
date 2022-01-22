package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Rekening;
import nl.hva.c25.team1.digivault.repository.JdbcRekeningDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.assertj.core.api.Assertions.assertThat;
/**
 * Testen bij KlantService
 *
 * @author Nienke
 * @since 6-12-2021
 */


//TODO kleiner maken, herschrijven, met een setup.. voor maintainence..
class RekeningServiceTest {

    @MockBean
    private static JdbcRekeningDAO mockRekeningDAO = Mockito.mock(JdbcRekeningDAO.class);
    private static TransactieService mockTransactieService = Mockito.mock(TransactieService.class);
    private static RekeningService serviceUnderTest = new RekeningService(mockRekeningDAO);
    private Rekening expected = new Rekening(1,"AA00ABCD0000000000", 500);
    private static Rekening rekening2 = new Rekening(2,"AA00ABCD0000000001",500);
    private static Rekening rekening3 = new Rekening(3,"AA00ABCD0000000002",500);


    @Test
    void vindRekeningOpIBAN() {
        Mockito.when(mockRekeningDAO.vindRekeningOpIBAN("AA00ABCD0000000000")).thenReturn(expected);
        Rekening actual = serviceUnderTest.vindRekeningOpIBAN("AA00ABCD0000000000");
        assertThat(actual).isNotNull().isEqualTo(expected);
    }
    @Test
    void vindRekeningOpId() {
        Mockito.when(mockRekeningDAO.vindRekeningOpId(2)).thenReturn(rekening2);
        Rekening actual = serviceUnderTest.vindRekeningOpId(2);
        assertThat(actual).isNotNull().isEqualTo(rekening2);
    }

    @Test
    void updateBestaandeRekening() {
        //maakt een rekening aan,  wil de rekning ophalen. test slaagt omdat de juiste rekening kan
        //worden gevonden.
        Rekening bestaandeRekening = new Rekening(2,"AA00ABCD0000000001",50);
        Mockito.when(mockRekeningDAO.vindRekeningOpIBAN("AA00ABCD0000000001")).thenReturn(bestaandeRekening);
        
        String actual = serviceUnderTest.updateRekening(bestaandeRekening);
        //String expected = "Update geslaagd";
        assertThat(actual).isNotNull().contains("geslaagd");
    }

    @Test
    void updateNietBestaandeRekening(){
        // maak een rekening aan, wil de rekening ophalen maar typt een foute bsn in, waardoor de rekening
        //dus niet bestaat. test slaagt want kande rekening niet vinden, dus update mislukt.
        Rekening bestaandeRekening = new Rekening(2,"AA00ABCD0000000001");
        Mockito.when(mockRekeningDAO.vindRekeningOpIBAN("AA00ABCD0000000002")).thenReturn(bestaandeRekening);
        String actual = serviceUnderTest.updateRekening(bestaandeRekening);
        assertThat(actual).isNotNull().contains("mislukt");

    }
}
