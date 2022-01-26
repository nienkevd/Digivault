package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Asset;
import nl.hva.c25.team1.digivault.model.EuroKoers;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Testen bij de JdbcEuroKoersDAO
 *
 * @author Erwin, studentnummer 500889293
 * @since 25-1-2021
 */

@SpringBootTest
@ActiveProfiles("test")
class JdbcEuroKoersDAOTest {
    private final JdbcEuroKoersDAO jdbcDaoUnderTest;
    private final EuroKoers expected1 = new EuroKoers(20, LocalDate.of(2021, 12, 7),
            35.14);
    private final EuroKoers expected2 = new EuroKoers(3, LocalDate.of(2021, 12, 7),
            35.14);
    private final LocalDate expected3 = LocalDate.of(2021, 12, 07);
    private final Asset expectedAsset = new Asset(3,"BNB", "Binance Coin");

    @Autowired
    public JdbcEuroKoersDAOTest(JdbcEuroKoersDAO jdbcDaoUnderTest) {
        this.jdbcDaoUnderTest = jdbcDaoUnderTest;
    }

    @Test
    public void setupTest() {
        Assertions.assertThat(this.jdbcDaoUnderTest).isNotNull();
    }

    @Test
    void vindAssetOpId() {
        EuroKoers actual = jdbcDaoUnderTest.vindEuroKoersOpId(20);
        assertThat(expected1).isEqualTo(actual);
    }

    @Test
    void geefAlleEuroKoersen() {
        List<EuroKoers> actual = jdbcDaoUnderTest.geefAlleEuroKoersen();
        Assertions.assertThat(actual).isNotNull().contains(expected1);
    }

    @Test
    void vindMeestRecenteKoersAsset() {
        EuroKoers actual = jdbcDaoUnderTest.vindMeestRecenteKoersAsset(expectedAsset);
        assertThat(expected2).isEqualTo(actual);
    }

    @Test
    void vindDatumMeestRecenteKoersAsset() {
        LocalDate actual = jdbcDaoUnderTest.vindDatumMeestRecenteKoersAsset(expectedAsset);
        assertThat(expected3).isEqualTo(actual);
    }
}
