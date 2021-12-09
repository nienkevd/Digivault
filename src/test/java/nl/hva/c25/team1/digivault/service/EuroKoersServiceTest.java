package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.EuroKoers;
import nl.hva.c25.team1.digivault.repository.JdbcEuroKoersDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

/**
 * Testen bij EuroKoersService
 *
 * @author Erwin, studentnummer 500889293
 * @since 5-12-2021
 */

class EuroKoersServiceTest {

    private static EuroKoers expected = new EuroKoers(1, LocalDate.parse("2021-12-02"), 566.00000);

    @MockBean
    private static JdbcEuroKoersDAO mockDAO = Mockito.mock(JdbcEuroKoersDAO.class);
    EuroKoersService serviceUnderTest = new EuroKoersService(mockDAO);

    @Test
    void vindEuroKoersOpId() {
        Mockito.when(mockDAO.vindEuroKoersOpId(1)).thenReturn(expected);
        EuroKoers actual = serviceUnderTest.vindEuroKoersOpId(1);
        assertThat(actual).isNotNull().isEqualTo(expected);
    }

    @Test
    void updateEuroKoers() {
        Mockito.when(mockDAO.vindEuroKoersOpId(1)).thenReturn(expected);
        String actual = serviceUnderTest.updateEuroKoers(expected);
        assertThat(actual).contains("geüpdatet");
    }
}
