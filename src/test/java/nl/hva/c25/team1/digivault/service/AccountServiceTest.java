package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.repository.AccountDAO;
import nl.hva.c25.team1.digivault.repository.JdbcAccountDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountServiceTest {

//    @MockBean
//    private static JdbcAccountDAO mockDAO = Mockito.mock(JdbcAccountDAO.class);
//    AccountService serviceUnderTest = new AccountService(mockDAO);
//
//    @Test
//    void vindAccountOpEmailadres() {
//        Account expected = new Account("annie@gmail.com", "Annie7890");
//        AccountDAO mockDao = Mockito.mock(AccountDAO.class);
//        Mockito.when(mockDao.vindAccountOpEmailadres("annie@gmail.com")).thenReturn(expected);
//
//        Account actual = serviceUnderTest.vindAccountOpEmailadres("annie@gmail.com");
//        assertThat(actual).isNotNull().isEqualTo(expected);
//    }
}
