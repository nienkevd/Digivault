package nl.hva.c25.team1.digivault.service;


import nl.hva.c25.team1.digivault.model.Transactie;
import nl.hva.c25.team1.digivault.repository.JdbcTransactieDAO;

/**
 * Author Nienke
 * Version 14-12-2021
 */

public class TransactieService {
    private JdbcTransactieDAO jdbcTransactieDAO;

    public TransactieService(JdbcTransactieDAO jdbcTransactieDAO) {
        this.jdbcTransactieDAO = jdbcTransactieDAO;
    }

    public Transactie bewaarTransactie(Transactie transactie){
        return jdbcTransactieDAO.bewaarTransacktieMetSK(transactie);
    }
    public Transactie vindTrasactieopTransactieId(int transactieId) {

        return jdbcTransactieDAO.vindTrasactieopTransactieId(transactieId);
    }
}
