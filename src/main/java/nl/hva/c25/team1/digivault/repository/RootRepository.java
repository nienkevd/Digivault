package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.model.PortefeuilleItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
        klant.getAccount().setKlant(klant); //  Anthon: beter naar einde?
        rekeningDAO.bewaarRekeningMetSK(klant.getRekening());
        for(PortefeuilleItem item : klant.getPortefeuille()){
            portefeuilleItemDAO.bewaarPortefeuilleItemMetKey(item);
        }
        return klantDAO.bewaarKlantMetSK(klant);
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



}
