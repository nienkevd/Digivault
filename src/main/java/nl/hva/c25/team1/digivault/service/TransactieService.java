package nl.hva.c25.team1.digivault.service;


import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.model.Transactie;
import nl.hva.c25.team1.digivault.model.TransactiePartij;
import nl.hva.c25.team1.digivault.repository.JdbcTransactieDAO;
import nl.hva.c25.team1.digivault.repository.TransactieDAO;

import java.util.List;

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

    public List<Transactie> vindAlleTransactiesOpVerkoper(TransactiePartij verkoper) {
        return jdbcTransactieDAO.vindAlleTransactiesOpVerkoper(verkoper);
    }
    public List<Transactie> vindAlleTransactiesOpKoper(TransactiePartij koper) {
        return jdbcTransactieDAO.vindAlleTransactiesOpVerkoper(koper);
    }
}
