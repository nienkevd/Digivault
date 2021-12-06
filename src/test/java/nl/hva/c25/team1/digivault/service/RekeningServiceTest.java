package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.model.Rekening;
import nl.hva.c25.team1.digivault.repository.JdbcRekeningDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

class RekeningServiceTest {
    private static Rekening expected = new Rekening(1,"AA00ABCD0000000000");

    @MockBean
    private static JdbcRekeningDAO mockDAO = Mockito.mock(JdbcRekeningDAO.class);
    RekeningService serviceUnderTest = new RekeningService(mockDAO);

    @Test
    void bewaarRekening() {
    }

    @Test
    void updateRekening() {
        Mockito.when(mockDAO.vindRekeningOpIBAN("AA00ABCD0000000000")).thenReturn(expected);
        String actual = serviceUnderTest.updateRekening(expected);
        assertThat(actual).contains("geslaagd");
    }

    @Test
    void vindRekeningOpIBAN() {
        Mockito.when(mockDAO.vindRekeningOpIBAN("AA00ABCD0000000000")).thenReturn(expected);
        Rekening actual = serviceUnderTest.vindRekeningOpIBAN("AA00ABCD0000000000");
        assertThat(actual).isNotNull().isEqualTo(expected);
    }

    @Test
    void geefAlleRekeningen() {
    }
}