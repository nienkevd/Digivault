package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Account;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")

class JdbcAccountDAOTest {
    private AccountDAO daoUnderTest;

    private static Account expected = new Account(1, "annie@gmail.com", "Annie7890");

    @Autowired
    public JdbcAccountDAOTest(AccountDAO daoUnderTest) {
        this.daoUnderTest = daoUnderTest;
    }

    @Test
    public void setupTest() {
        Assertions.assertThat(this.daoUnderTest).isNotNull();
    }

    @Test
    void bewaarAccountMetSK() {
        Account newAccount = new Account("Jan", "123jan");
        Account actual = this.daoUnderTest.bewaarAccountMetSK(newAccount);
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getAccountId()).isGreaterThan(0);
    }

    @Test
    void updateAccount() {
    }

    @Test
    void geefAlleAccounts() {
        List<Account> actual = this.daoUnderTest.geefAlleAccounts();
        Assertions.assertThat(actual).isNotNull().contains(expected);
    }

    @Test
    void vindAccountOpAccountId() {
        Account actual = this.daoUnderTest.vindAccountOpAccountId(1);
        Assertions.assertThat(expected).isEqualTo(actual);
    }
}