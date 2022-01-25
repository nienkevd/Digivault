package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Klant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.time.LocalDate;
import java.util.List;
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
    private Klant expected = new Klant(10,"080772547", LocalDate.parse("1983-07-05"));
    private Klant update = new Klant(11, "405981028", LocalDate.parse("1990-06-20"));

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
    void vindKlantOpKlantId() {
        Klant actual = daoUnderTest.vindKlantOpKlantId(10);
        assertThat(expected).isEqualTo(actual);

    }
    @Test
    void vindAlleKlanten() {
        List<Klant> actual = daoUnderTest.vindAlleKlanten(); // bank geldt niet als klant, dus tpId > 9
        assertThat(actual).isNotNull().hasSize(3);
        assertThat(actual).contains(expected);
    }

    @Test
    void update() {
        daoUnderTest.update(update);
        Klant actual = daoUnderTest.vindKlantOpKlantId(11);
        assertThat(update).isEqualTo(actual);
    }

    @Test
    void vindKlantOpEmailadres() {
        Klant actual = daoUnderTest.vindKlantOpEmailadres("marieke@gmail.com");
        assertThat(expected).isEqualTo(actual);
    }
}