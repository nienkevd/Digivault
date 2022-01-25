package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.EuroKoers;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")

class JdbcEuroKoersDAOTest {
//    private final JdbcEuroKoersDAO jdbcDaoUnderTest;
//    private final EuroKoers expected = new EuroKoers(1, LocalDate.of(2022, 01, 01), 41703.42520);
//
//    @Autowired
//    public JdbcEuroKoersDAO(JdbcEuroKoersDAO jdbcDaoUnderTest) {
//        this.jdbcDaoUnderTest = jdbcDaoUnderTest;
//    }

//    @Test
//    public void setupTest() {
//        Assertions.assertThat(this.jdbcDaoUnderTest).isNotNull();
//    }
//
//    @Test
//    public void geefAlleEurkoersen() {
//        List<EuroKoers> actual = jdbcDaoUnderTest.geefAlleEuroKoersen();
//        Assertions.assertThat(actual).isNotNull().contains(expected);
//    }
}
