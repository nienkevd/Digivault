package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.repository.AccountDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountService {

    @Test
    void vindAccountOpGebruikersnaam() {
        Account expected = new Account("Annie7890", "w8even*76");
        AccountDAO mockDao = Mockito.mock(AccountDAO.class);
        Mockito.when(mockDao.vindAccountOpGebruikersnaam("Annie7890")).thenReturn(expected);

        AccountService serviceUnderTest = new AccountService();
        Account actual = serviceUnderTest.vindAccountOpGebruikersnaam("Annie7890");
        assertThat(actual).isNotNull().isEqualTo(expected);
    }
}
