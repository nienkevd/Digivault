package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Klant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

/**
 * Testen bij KlantService
 *
 * @author Anneke, studentnummer 500889251
 * @since 9-12-2021
 */

@SpringBootTest
@ActiveProfiles("test")
class JdbcKlantDAOTest {

    private JdbcKlantDAO daoUnderTest;

    @Autowired
    public JdbcKlantDAOTest(JdbcKlantDAO daoUnderTest) {
        super();
        this.daoUnderTest = daoUnderTest;
    }

    @Test
    public void setupTest() {
        assertThat(daoUnderTest).isNotNull();
    }

    @Test
    void bewaarKlantMetSK() {
    }

//    @Test
//    void vindKlantOpKlantId() {
//        Klant actual = daoUnderTest.vindKlantOpKlantId(2);
//        Klant expected = new Klant(2,"080772547", LocalDate.parse("1983-07-05"));
//        assertThat(expected).isEqualTo(actual);
//
//    }
    @Test
    void vindAlleKlanten() {
    }

    @Test
    void update() {
    }
}