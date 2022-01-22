// gemaakt door Anthon van Dijk

package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Transactie;
import nl.hva.c25.team1.digivault.model.TransactiePartij;
import org.junit.jupiter.api.*;
import org.mockito.*;
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

    @Mock
    private TransactiePartij transactiePartij1, transactiePartij2, transactiePartij3;

    @Autowired
    public JdbcTransactieDAOTest(JdbcTransactieDAO jdbcTransactieDAO) {
        super();
        this.jdbcTransactieDAO = jdbcTransactieDAO;
    }

    @Test
    void vindAlleTransactiesOpKoper() {
        Mockito.when(transactiePartij1.getTransactiepartijId()).thenReturn(10); // 2 records
        List<Transactie> transactieLijst = jdbcTransactieDAO.vindAlleTransactiesOpKoper(transactiePartij1);
        assertThat(transactieLijst).isNotNull().hasSize(2);

        Mockito.when(transactiePartij2.getTransactiepartijId()).thenReturn(11); // 1 record
        transactieLijst = jdbcTransactieDAO.vindAlleTransactiesOpKoper(transactiePartij2);
        assertThat(transactieLijst).isNotNull().hasSize(1);

        Mockito.when(transactiePartij3.getTransactiepartijId()).thenReturn(1); // 0 records
        transactieLijst = jdbcTransactieDAO.vindAlleTransactiesOpKoper(transactiePartij3);
        assertThat(transactieLijst).isNotNull().hasSize(0);

    }
}