package nl.hva.c25.team1.digivault.service;


import nl.hva.c25.team1.digivault.model.*;
import nl.hva.c25.team1.digivault.repository.JdbcTransactieDAO;
import nl.hva.c25.team1.digivault.repository.TransactieDAO;
import nl.hva.c25.team1.digivault.transfer.TransactieDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author Nienke
 * Version 14-12-2021
 */
@Service
public class TransactieService {

    private TransactieDAO transactieDAO;
    private RekeningService rekeningService;
    private PortefeuilleItemService portefeuilleItemService;
    private KlantService klantService;
    private AssetService assetService;
    private BankService bankService;


    public TransactieService(TransactieDAO transactieDAO, RekeningService rekeningService,
                             PortefeuilleItemService portefeuilleItemService, KlantService klantService,
                             AssetService assetService, BankService bankService) {
        this.transactieDAO = transactieDAO;
        this.rekeningService = rekeningService;
        this.portefeuilleItemService = portefeuilleItemService;
        this.klantService = klantService;
        this.assetService = assetService;
        this.bankService = bankService;
    }

    public Transactie voerTransactieUit(TransactieDTO transactieDTO) {
        Transactie transactie = zetDtoOm(transactieDTO);
        return null; // null als niet gelukt!
    }

    private Transactie zetDtoOm(TransactieDTO transactieDTO) {
        Transactie transactie = new Transactie(transactieDTO);
        int koperId = transactieDTO.getKoperId();
        int verkoperId = transactieDTO.getVerkoperId();
        TransactiePartij koper, verkoper;
        if (koperId < 10) { // bank is koper
            koper = bankService.vindBankOpId(koperId);
            verkoper = klantService.vindKlantOpKlantID(verkoperId);
        } else if (verkoperId < 10) { // bank is verkoper
            koper = klantService.vindKlantOpKlantID(koperId);
            verkoper = bankService.vindBankOpId(verkoperId);
        } else { // transactie tussen 2 klanten
            koper = klantService.vindKlantOpKlantID(koperId);
            verkoper = klantService.vindKlantOpKlantID(verkoperId);
        }
        transactie.setKoper(koper);
        transactie.setVerkoper(verkoper);
        transactie.setAsset(assetService.vindAssetOpId(transactieDTO.getAssetId()));
        return transactie;
    }


    public Transactie bewaarTransactie(Transactie transactie) {
        return transactieDAO.bewaarTransacktieMetSK(transactie);
    }

    public Transactie vindTransactieOpTransactieId(int transactieId) {
        return transactieDAO.vindTransactieOpTransactieId(transactieId);
    }

    public List<Transactie> vindAlleTransactiesOpVerkoper(TransactiePartij verkoper) {
        return transactieDAO.vindAlleTransactiesOpVerkoper(verkoper);
    }

    public List<Transactie> vindAlleTransactiesOpKoper(TransactiePartij koper) {
        return transactieDAO.vindAlleTransactiesOpVerkoper(koper);
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


