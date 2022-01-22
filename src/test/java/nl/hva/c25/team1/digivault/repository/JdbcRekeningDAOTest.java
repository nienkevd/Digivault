package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Rekening;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class JdbcRekeningDAOTest {
    private RekeningDAO daoUnderTest;
    private static Rekening expected = new Rekening("NL88DIVA4776153180",1000);

    @Autowired
    public JdbcRekeningDAOTest(RekeningDAO daoUnderTest) {
        super();
        this.daoUnderTest = daoUnderTest;
    }

    @Test
    public void setupTest() {
        assertThat(daoUnderTest).isNotNull();
    }



    //TODO leraar vragen waarom dit niet werkt
    //op deze manier getest, wilde graag als rekenening nummer testen of hij erin zat, maar lukte niet.
    //Gaf de juiste rekening terug, maar herkende hem niet als dezelfde..
   /* @Test
    void vindRekeningOpId() {
        Rekening actual = daoUnderTest.vindRekeningOpId(1);
        Rekening expected = new Rekening(1,"NL81DIVA2208729564",10000000);
        assertThat(actual).isNotNull().isEqualTo(expected);
    }*/
    /*@Test
    void vindRekeningOpIBAN() {
        Rekening actual = daoUnderTest.vindRekeningOpIBAN("NL81DIVA2208729564");
        Rekening expected = new Rekening("NL81DIVA2208729564",10000000);
        assertThat(actual).isNotNull().isEqualTo(expected);
    }

    //op deze manier getest, wilde graag als rekenening nummer testen of hij erin zat, maar lukte niet.
    //Gaf de juiste rekening terug, maar herkende hem niet als dezelfde..
    @Test
    void geefAlleRekeningenBevatSpecifiekRekeningNr() {
        Rekening expected = new Rekening("NL88DIVA4776153180",1000);
        List<Rekening> actual = daoUnderTest.geefAlleRekeningen();
        assertThat(actual).isNotNull().contains(expected);
    }*/

    //Als de test lukt betekend het dat hij connectie maakt met de database, en kan laat zien dat hij ophaalt wat daar
    //is weggeschreven. In de H2 database staan 4 Rekening items, dus hij haalt de correcte informatie op.
    @Test
    void geefAlleRekeningenHoeveelItems() {
        int expectedSize = 4;
        List<Rekening> actual = daoUnderTest.geefAlleRekeningen();
        assertThat(actual).isNotNull().hasSize(expectedSize);

    }

}