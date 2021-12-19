package nl.hva.c25.team1.digivault.service;


import nl.hva.c25.team1.digivault.model.Bank;
import nl.hva.c25.team1.digivault.model.PortefeuilleItem;
import nl.hva.c25.team1.digivault.model.Transactie;
import nl.hva.c25.team1.digivault.model.TransactiePartij;
import nl.hva.c25.team1.digivault.repository.JdbcTransactieDAO;

import java.util.List;

/**
 * Author Nienke
 * Version 14-12-2021
 */

public class TransactieService {
    private JdbcTransactieDAO jdbcTransactieDAO;
    private RekeningService rekeningService;
    private PortefeuilleItemService portefeuilleItemService;
    private Object Bank;
    private Bank bank;

    public TransactieService(JdbcTransactieDAO jdbcTransactieDAO, RekeningService rekeningService, PortefeuilleItemService portefeuilleItemService, Bank bank) {
        this.jdbcTransactieDAO = jdbcTransactieDAO;
        this.rekeningService = rekeningService;
        this.portefeuilleItemService = portefeuilleItemService;
        this.bank = bank;
    }

    public Transactie bewaarTransactie(Transactie transactie) {
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

    public double berekenWaardeTransactie(Transactie transactie) {
            return transactie.getAsset().getDagKoers() * transactie.getAantalCryptos();
    }

    public boolean checkKoper(Transactie transactie) {
        return transactie.getKoper().getRekening().getSaldo() >= berekenWaardeTransactie(transactie);
    }

    public boolean checkVerkoper(Transactie transactie) {
        for (PortefeuilleItem portefeuilleItem : transactie.getVerkoper().getPortefeuille()) {
            if (portefeuilleItem.getAsset() == transactie.getAsset()) {
                return portefeuilleItem.getHoeveelheid() >= transactie.getAantalCryptos();
            }
        }
        return false;
    }
    public boolean checkAccounts (Transactie transactie) {
        return !(transactie.getVerkoper().getRekening() == transactie.getKoper().getRekening());
    }
    public void maakTransactie(Transactie transactie) {
        if (checkKoper(transactie) && checkVerkoper(transactie) && checkAccounts(transactie)) {
            rekeningService.verhoogRekening(transactie);
            rekeningService.verlaagRekening(transactie);
            portefeuilleItemService.verhoogPortefeuilleItem(transactie);
            portefeuilleItemService.verlaagPortefeuilleItem(transactie);
        }
    }
}


