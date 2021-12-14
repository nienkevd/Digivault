package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.model.Transactie;

/**
 * Author Nienke
 * Version 14-12-2021
 */
public interface TransactieDAO {
    Transactie bewaarTransacktieMetSK(Transactie transactie);
    Transactie vindTrasactieopTransactieId(int transactieId);

}

