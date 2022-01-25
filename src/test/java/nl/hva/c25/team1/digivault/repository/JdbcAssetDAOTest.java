package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Asset;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

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
}
