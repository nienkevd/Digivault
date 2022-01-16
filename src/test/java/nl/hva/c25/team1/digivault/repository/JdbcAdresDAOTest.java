package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Adres;
import nl.hva.c25.team1.digivault.model.Naam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

/**
 * Testen bij JdbcAdresDAO
 *
 * @author Anneke, studentnummer 500889251
 */

@SpringBootTest
@ActiveProfiles("test")
class JdbcAdresDAOTest {

    private JdbcAdresDAO daoUnderTest;
    private Adres expected = new Adres(1,"1051NJ",24,"a");
    private Adres nieuw = new Adres(0, "1050JJ", 3,null);

    @Autowired
    public JdbcAdresDAOTest(JdbcAdresDAO daoUnderTest) {
        super();
        this.daoUnderTest = daoUnderTest;
    }

    @Test
    public void setupTest() {
        assertThat(daoUnderTest).isNotNull();
    }

    @Test
    void vindAlleAdressen() {
        List<Adres> actual = daoUnderTest.vindAlleAdressen();
        assertThat(actual).isNotNull().hasSize(3);
        assertThat(actual).contains(expected);
    }

    @Test
    void bewaarAdresMetSK() {
        Adres actual = daoUnderTest.bewaarAdresMetSK(nieuw);
        assertThat(actual).isNotNull();
        // adresId auto increment, hiervoor 1 t/m 3 in db
        assertThat(actual.getAdresId()).isEqualTo(4);
        // of:
        assertThat(actual.getAdresId()).isGreaterThan(0);
    }

    @Test
    void vindAdresOpAdresId() {
        Adres actual = daoUnderTest.vindAdresOpAdresId(1);
        assertThat(expected).isEqualTo(actual);
    }




}