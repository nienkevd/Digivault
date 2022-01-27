package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Asset;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

/**
 * Testen bij de JdbcAssetDAO
 *
 * @author Erwin, studentnummer 500889293
 * @since 25-1-2021
 */

@SpringBootTest
@ActiveProfiles("test")
class JdbcAssetDAOTest {
    private final JdbcAssetDAO jdbcDaoUnderTest;
    private final Asset expected = new Asset(3,"BNB", "Binance Coin");

    @Autowired
    public JdbcAssetDAOTest(JdbcAssetDAO jdbcDaoUnderTest) {
        this.jdbcDaoUnderTest = jdbcDaoUnderTest;
    }


    @Test
    public void setupTest() {
        Assertions.assertThat(this.jdbcDaoUnderTest).isNotNull();
    }

    @Test
    void geefAlleAssets() {
        List<Asset> actual = jdbcDaoUnderTest.geefAlleAssets();
        Assertions.assertThat(actual).isNotNull().contains(expected);
    }

    @Test
    void vindAssetOpId() {
        Asset actual = jdbcDaoUnderTest.vindAssetOpId(3);
        assertThat(expected).isEqualTo(actual);
    }
}
