package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.*;
import nl.hva.c25.team1.digivault.service.RekeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RootRepository {
    KlantDAO klantDAO;
    RekeningDAO rekeningDAO;
    AccountDAO accountDAO;
    PortefeuilleItemDAO portefeuilleItemDAO;
    NaamDAO naamDAO;
    AdresDAO adresDAO;
    AssetDAO assetDAO;
    TransactieDAO transactieDAO;

    @Autowired
    public RootRepository(KlantDAO klantDAO, RekeningDAO rekeningDAO, AccountDAO accountDAO,
                          PortefeuilleItemDAO portefeuilleItemDAO, NaamDAO naamDAO, AdresDAO adresDAO,
                          AssetDAO assetDAO, TransactieDAO transactieDAO) {
        this.klantDAO = klantDAO;
        this.rekeningDAO = rekeningDAO;
        this.accountDAO = accountDAO;
        this.portefeuilleItemDAO = portefeuilleItemDAO;
        this.naamDAO = naamDAO;
        this.adresDAO = adresDAO;
        this.assetDAO = assetDAO;
        this.transactieDAO = transactieDAO;
    }

    // checked: Anthon 8-12-2021
    /**
     *
     * @author Anneke
     * @author Anthon
     * Deze methode wordt aangeroepen in registratieservice registratie(Klant klant)
     * De objecten van de klant worden afzonderlijk opgeslagen en de
     * klant kan daarna met alle id's volledig worden opgeslagen
     * @param klant klant zonder id's
     * @return volledig geregistreerd klant object
     */
    public Klant slaKlantOp(Klant klant){
        adresDAO.bewaarAdresMetSK(klant.getAdres());
        rekeningDAO.bewaarRekeningMetSK(klant.getRekening());
        klantDAO.bewaarKlantMetSK(klant);
        klant.getAccount().setKlant(klant);//java

        for(PortefeuilleItem item : klant.getPortefeuille()){
            item.getKlant().setTransactiepartijId(klant.getTransactiepartijId());
            portefeuilleItemDAO.bewaarPortefeuilleItemMetKey(item);
        }
        return klant;
    }

    public Klant vindKlantOpId(int klantId) {
        Klant klant = klantDAO.vindKlantOpKlantId(klantId);
        if (klant == null) {
            return klant;
        }
        List<PortefeuilleItem> itemsKlant = portefeuilleItemDAO.genereerPortefeuilleVanKlantMetId(klantId);
        for (PortefeuilleItem portefeuilleItem: itemsKlant) {
            portefeuilleItem.setKlant(klant);
        }
        klant.setPortefeuille(itemsKlant);
        return klant;
    }

    public PortefeuilleItem vindItemOpId(int itemId) {
        PortefeuilleItem portefeuilleItem = portefeuilleItemDAO.vindItemMetId(itemId);
        Klant klant = klantDAO.vindKlantOpKlantId(portefeuilleItemDAO.vindKlantIdVanPortefeuilleitem(portefeuilleItem));
        portefeuilleItem.setKlant(klant);
        return portefeuilleItem;
    }

    /**
     * Methode die een financieel overzicht (overzicht rekening + portefeuille) genereert op basis van klantId
     *
     * @author Nienke en Erwin
     * @param klantId klantId waarop gezocht wordt
     * @return het gegenereerde financiële overzicht
     */
    public FinancieelOverzicht genereerFinancieelOverzichtOpId(int klantId) {
        FinancieelOverzicht financieelOverzicht = new FinancieelOverzicht(klantId);
        Rekening rekening = rekeningDAO.vindRekeningOpId(klantId);
        financieelOverzicht.setIban(rekening.getIBAN());
        financieelOverzicht.setSaldo(rekening.getSaldo());
        financieelOverzicht.setAssetMetAantal(genereerPortefeuilleOverzicht(klantId));
        return financieelOverzicht;
    }

    /**
     * Methode die genereerFinancieelOverzichtOpId() voorziet van een lijst met assets en bijbehorende hoeveelheid
     *
     * @author Erwin
     * @param klantId klantId waarop gezocht wordt
     * @return de lijst met asset-parameters + hoeveelheid
     */
    public List<AssetMetAantal> genereerPortefeuilleOverzicht(int klantId) {
        List<AssetMetAantal> portefeuilleOverzicht = new ArrayList<>();
        for (PortefeuilleItem portefeuilleItem : portefeuilleItemDAO.genereerPortefeuilleVanKlantMetId(klantId)) {
            portefeuilleItem.setAsset(assetDAO.vindAssetOpId(portefeuilleItemDAO.vindAssetIdVanPortefeuilleItem
                    (portefeuilleItem)));
            AssetMetAantal overzicht = new AssetMetAantal();
            overzicht.setAssetId(portefeuilleItem.getAsset().getAssetId());
            overzicht.setAfkorting(portefeuilleItem.getAsset().getAfkorting());
            overzicht.setNaam(portefeuilleItem.getAsset().getNaam());
            overzicht.setDagkoers(portefeuilleItem.getAsset().getDagKoers());
            overzicht.setAantal(portefeuilleItem.getHoeveelheid());
            portefeuilleOverzicht.add(overzicht);
        }
        return portefeuilleOverzicht;
    }

    public Transactie voerTransactieUit(Transactie transactie) {
        TransactiePartij koper = transactie.getKoper();
        TransactiePartij verkoper = transactie.getVerkoper();
        rekeningDAO.updateRekening(verkoper.getRekening());
        rekeningDAO.updateRekening(koper.getRekening());
        for (PortefeuilleItem portefeuilleItem : koper.getPortefeuille()) {
            if (portefeuilleItem.getAsset().getAfkorting().equals(transactie.getAsset().getAfkorting())) {
                portefeuilleItemDAO.updatePortefeuilleItem(portefeuilleItem);
            }
        }
        for (PortefeuilleItem portefeuilleItem : verkoper.getPortefeuille()) {
            if (portefeuilleItem.getAsset().getAfkorting().equals(transactie.getAsset().getAfkorting())) {
                portefeuilleItemDAO.updatePortefeuilleItem(portefeuilleItem);
            }
        }
        return transactieDAO.bewaarTransacktieMetSK(transactie);
    }

}
