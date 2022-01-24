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
    private static Account expected = new Account("marieke@gmail.com", "$2a$10$FdzlhHHLg2ciuK6WMK21TudBBzDu9ybfWdQVH96sko/AjvkhBa53.");

    @Autowired
    public JdbcAccountDAOTest(AccountDAO daoUnderTest) {
        super();
        this.daoUnderTest = daoUnderTest;
    }
    @Test
    public void setupTest() {
    Assertions.assertThat(this.daoUnderTest).isNotNull();
    }

    @Test
    void geefAlleAccounts() {
        List<Account> actual = daoUnderTest.geefAlleAccounts();
        Assertions.assertThat(actual).isNotNull().contains(expected);
    }
}