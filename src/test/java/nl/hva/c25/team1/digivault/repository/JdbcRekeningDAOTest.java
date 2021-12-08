package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Rekening;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class JdbcRekeningDAOTest {
   // private static Rekening expected = new Rekening(1,"AA00ABCD0000000000");
/*    private RekeningDAO daoUnderTest;

    @Autowired
    public JdbcRekeningDAOTest(RekeningDAO daoUnderTest) {
        this.daoUnderTest = daoUnderTest;
    }

    @Test
    public void setupTest() {
        assertThat(daoUnderTest).isNotNull();
    }

    @Test
    void bewaar() {
    }

    @Test
    void bewaarRekeningMetSK() {
    }

    @Test
    void updateRekening() {
    }

    @Test
    void vindRekeningOpIBAN() {
        Rekening actual = daoUnderTest.vindRekeningOpIBAN("AA00ABCD0000000000");
        Rekening expected = new Rekening(1,"AA00ABCD0000000000");
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void geefAlleRekeningen() {
    }

 */
}