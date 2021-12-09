package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.repository.JdbcAccountDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;


class AccountServiceTest {


//    private static  Account expected = new Account(1, "annie@gmail.com", "Annie7890");
//
//    @MockBean
//    private static JdbcAccountDAO mockDAO = Mockito.mock(JdbcAccountDAO.class);
//    AccountService serviceUnderTest = new AccountService(mockDAO);
//
//    @Test
//    void bewaarAccountMetSK() {
//        Account newAccount = new Account("Jan", "123jan");
//        Mockito.when(mockDAO.bewaarAccountMetSK(newAccount)).thenReturn(newAccount);
//        Account actual = serviceUnderTest.bewaarAccountMetSK(newAccount);
//        assertThat(actual.getAccountId()).isPositive();
//
//    }
//
//    @Test
//    void updateBestaandeAccount() {
//        Mockito.when(mockDAO.vindAccountOpAccountId(1)).thenReturn(expected);
//        String actual = serviceUnderTest.updateAccount(expected);
//        assertThat(actual).contains("geslaagd");
//    }
//
//    @Test
//    void updateNietBestaandeAccount() {
//        Account accountBestaatNiet = new Account (50, "Jan@gmail.com", "123jan");
//        Mockito.when(mockDAO.vindAccountOpAccountId(50)).thenReturn(null);
//        String actual = serviceUnderTest.updateAccount(accountBestaatNiet);
//        assertThat(actual).startsWith("Account").contains("mislukt");
//    }
//
//    @Test
//    void vindAccountOpAccountId() {
//        Mockito.when(mockDAO.vindAccountOpAccountId(1)).thenReturn(expected);
//        Account actual = serviceUnderTest.vindAccountOpAccountId(1);
//        assertThat(actual).isNotNull().isEqualTo(expected);
//    }
//
//    @Test
//    void geefAlleAccounts() {
//    }
}