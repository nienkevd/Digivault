// gemaakt door Anthon van Dijk

package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Transactie;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class JdbcTransactieDAOTest {

    // klasse onder test
    private JdbcTransactieDAO jdbcTransactieDAO;

    @Autowired
    public JdbcTransactieDAOTest(JdbcTransactieDAO jdbcTransactieDAO) {
        super();
        this.jdbcTransactieDAO = jdbcTransactieDAO;
    }

    @Test
    void vindTransactieOpTransactieId() {
        Transactie transactie = jdbcTransactieDAO.vindTransactieOpTransactieId(1);
        assertThat(transactie).isNotNull().isInstanceOf(Transactie.class);
        assertThat(transactie.getTransactieDatum()).isEqualTo(LocalDate.of(2021, 12, 20));
        assertThat(transactie.getTransactieTijd()).isEqualTo(LocalTime.of(13, 23, 44));
        assertThat(transactie.getAantalCryptos()).isEqualTo(0.524);
    }

}