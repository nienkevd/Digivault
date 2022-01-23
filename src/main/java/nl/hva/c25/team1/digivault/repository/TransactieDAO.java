package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Transactie;
import java.util.List;

/**
 * @author Nienke
 * @author Anthon
 */
public interface TransactieDAO {

    Transactie bewaarTransacktieMetSK(Transactie transactie);

    List<Transactie> vindAlleTransactiesOpKoper(int koperId);

}

