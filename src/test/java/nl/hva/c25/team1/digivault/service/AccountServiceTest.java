package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.repository.JdbcAccountDAO;
import nl.hva.c25.team1.digivault.repository.KlantDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

class AccountServiceTest {

    private Klant klant = new Klant(10,"080772547", LocalDate.parse("1983-07-05"));
    private Account expected = new Account("annie@gmail.com", "Annie7890", klant);

    @MockBean
    private static JdbcAccountDAO mockDAO = Mockito.mock(JdbcAccountDAO.class);
    @MockBean
    private static KlantDAO mockKlantDAO = Mockito.mock(KlantDAO.class);
    AccountService serviceUnderTest = new AccountService(mockDAO, mockKlantDAO);

    @Test
    void vindAccountOpKlantId() {
        Mockito.when(mockDAO.vindAccountOpKlantId(10)).thenReturn(expected);
        Account actual = serviceUnderTest.vindAccountOpKlantId(10);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void vindAccountOpEmailAdres() {
        Mockito.when(mockDAO.vindAccountOpEmailAdres("annie@gmail.com")).thenReturn(expected);
        Account actual = serviceUnderTest.vindAccountOpEmailadres("annie@gmail.com");
        assertThat(actual).isEqualTo(expected);
    }
}
