// gemaakt door Anthon van Dijk

package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Transactie;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;

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
    void vindAlleTransactiesOpKoper() {
        List<Transactie> transactieLijst = jdbcTransactieDAO.vindAlleTransactiesOpKoper(10); //  koper 10: 2 records
        assertThat(transactieLijst).isNotNull().hasSize(2);

        transactieLijst = jdbcTransactieDAO.vindAlleTransactiesOpKoper(11); // koper 11: 1 record
        assertThat(transactieLijst).isNotNull().hasSize(1);

        transactieLijst = jdbcTransactieDAO.vindAlleTransactiesOpKoper(1); // koper 1: 0 records
        assertThat(transactieLijst).isNotNull().hasSize(0);
    }

}