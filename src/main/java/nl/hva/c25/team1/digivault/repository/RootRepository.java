package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.*;
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


    @Autowired
    public RootRepository(KlantDAO klantDAO, RekeningDAO rekeningDAO, AccountDAO accountDAO,
                          PortefeuilleItemDAO portefeuilleItemDAO, NaamDAO naamDAO, AdresDAO adresDAO, AssetDAO assetDAO) {
        this.klantDAO = klantDAO;
        this.rekeningDAO = rekeningDAO;
        this.accountDAO = accountDAO;
        this.portefeuilleItemDAO = portefeuilleItemDAO;
        this.naamDAO = naamDAO;
        this.adresDAO = adresDAO;
        this.assetDAO = assetDAO;
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
        naamDAO.bewaarNaamMetSK(klant.getNaam());
        adresDAO.bewaarAdresMetSK(klant.getAdres());
        accountDAO.bewaarAccountMetSK(klant.getAccount());
        rekeningDAO.bewaarRekeningMetSK(klant.getRekening());
        klantDAO.bewaarKlantMetSK(klant);
        klant.getAccount().setKlant(klant);//java

        for(PortefeuilleItem item : klant.getPortefeuille()){
            item.getKlant().setKlantId(klant.getKlantId());
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
     * @author Anthon
     *
     * Methode gemaakt om functionaliteit rootrepo uit te proberen.
     *
     * @param itemId Id van het portefeuilleitem.
     * @return complete portefeuilleitem.
     */
    public PortefeuilleItem vindPortefeuilleItemOpId(int itemId) {
        PortefeuilleItem portefeuilleItem = portefeuilleItemDAO.vindItemMetId(itemId);
        portefeuilleItem.setKlant(klantDAO.vindKlantOpKlantId(portefeuilleItem.getKlant().getKlantId()));
        portefeuilleItem.setAsset(assetDAO.vindAssetOpId(portefeuilleItem.getAsset().getAssetId()));
        return portefeuilleItem;
    }

    public FinancieelOverzicht vindFinancieelOverzichtOpId(int klantId) {
        System.out.println("root");
        FinancieelOverzicht financieelOverzicht = new FinancieelOverzicht(klantId);
        Rekening rekening = rekeningDAO.vindRekeningOpId(klantId);
        financieelOverzicht.setIban(rekening.getIBAN());
        financieelOverzicht.setSaldo(rekening.getSaldo());
        financieelOverzicht.setPortefeuille(genereerPortefeuilleOverzicht(klantId));
        return financieelOverzicht;
    }

    public List<PortefeuilleItemOverzicht> genereerPortefeuilleOverzicht(int klantId) {
        List<PortefeuilleItemOverzicht> portefeuilleOverzicht = new ArrayList<>();
        for (Asset asset: assetDAO.geefAlleAssets()) {
            PortefeuilleItemOverzicht overzicht = new PortefeuilleItemOverzicht();
            overzicht.setAfkorting(asset.getAfkorting());
            overzicht.setNaam(asset.getNaam());
            overzicht.setDagkoers(asset.getDagKoers());
            portefeuilleOverzicht.add(overzicht);
        }
        return portefeuilleOverzicht;
    }
}
