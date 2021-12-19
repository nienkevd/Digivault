package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Bank;
import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.model.Rekening;
import nl.hva.c25.team1.digivault.model.Transactie;
import nl.hva.c25.team1.digivault.repository.JdbcRekeningDAO;
import nl.hva.c25.team1.digivault.repository.RootRepository;

import java.util.List;

// review door Erwin, 1 december

/**
 * @author Sezi, studentnummer 500889525
 * @version 1-12-2021
 */

public class RekeningService {

    private JdbcRekeningDAO jdbcRekeningDAO;
    //    private RootRepository rootRepository;
    private TransactieService transactieService;
    private Object Bank;
    private Bank bank;

    public RekeningService(JdbcRekeningDAO rekeningDAO/*, RootRepository rootRepository*/, TransactieService transactieService, Bank bank) {
        this.jdbcRekeningDAO = rekeningDAO;
//        this.rootRepository = rootRepository;
        this.transactieService = transactieService;
        this.bank = bank;
    }

    public void bewaarRekening(Rekening rekening) {
        jdbcRekeningDAO.bewaar(rekening);
    }

    public String updateRekening(Rekening rekening) {
        if (jdbcRekeningDAO.vindRekeningOpIBAN(rekening.getIBAN()) == null) {
            return "Rekening bestaat niet, update mislukt.";
        } else {
            jdbcRekeningDAO.updateRekening(rekening);
            return "Update geslaagd";
        }
    }

    public Rekening vindRekeningOpIBAN(String IBAN) {

        return jdbcRekeningDAO.vindRekeningOpIBAN(IBAN);
    }

    public Rekening vindRekeningOpId(int rekeningId) {
        return jdbcRekeningDAO.vindRekeningOpId(rekeningId);
    }

    public List<Rekening> geefAlleRekeningen() {

        return jdbcRekeningDAO.geefAlleRekeningen();
    }

    public void verlaagRekening(Transactie transactie) {
        double voorSaldoKoper = transactie.getKoper().getRekening().getSaldo();
        double transactieWaarde = transactieService.berekenWaardeTransactie(transactie);
        if (transactie.getVerkoper().getClass() == Bank) {
            transactie.getKoper().getRekening().setSaldo(voorSaldoKoper - transactieWaarde -
                    transactieWaarde * bank.getTransactiePercentage());
        } else {
            transactie.getKoper().getRekening().setSaldo(voorSaldoKoper - transactieWaarde);
        }
    }

    public void verhoogRekening(Transactie transactie) {
        double voorSaldoVerkoper = transactie.getVerkoper().getRekening().getSaldo();
        double transactieWaarde = transactieService.berekenWaardeTransactie(transactie);
        if (transactie.getKoper().getClass() == Bank) {
            transactie.getVerkoper().getRekening().setSaldo(voorSaldoVerkoper + transactieWaarde -
                    transactieWaarde * bank.getTransactiePercentage());
        } else {
            transactie.getVerkoper().getRekening().setSaldo(voorSaldoVerkoper + transactieWaarde);
        }
    }
}
