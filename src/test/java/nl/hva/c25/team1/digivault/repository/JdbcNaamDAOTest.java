package nl.hva.c25.team1.digivault.repository;


import nl.hva.c25.team1.digivault.model.Naam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

/**
 * Testen bij JdbcNaamDAO
 *
 * @author Anneke, studentnummer 500889251
 */

@SpringBootTest
@ActiveProfiles("test")
class JdbcNaamDAOTest {

    private JdbcNaamDAO daoUnderTest;
    private Naam expected = new Naam("Marieke", "de", "Vries");

    @Autowired
    public JdbcNaamDAOTest(JdbcNaamDAO daoUnderTest) {
        super();
        this.daoUnderTest = daoUnderTest;
    }

    @Test
    public void setupTest() {
        assertThat(daoUnderTest).isNotNull();
    }

    @Test
    void vindNaamOpKlantId() {
        Naam actual = daoUnderTest.vindNaamOpKlantId(10);
        assertThat(expected).isNotNull().isEqualTo(actual);
    }

    @Test
    void vindAlleNamen() {
        List<Naam> actual = daoUnderTest.vindAlleNamen();
        assertThat(actual).isNotNull().hasSize(12); // 12 in db (incl. bank)
        assertThat(actual).contains(expected);
    }

}