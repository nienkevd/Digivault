package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RootRepository {

    private KlantDAO klantDAO;
    private RekeningDAO rekeningDAO;
    private PortefeuilleItemDAO portefeuilleItemDAO;
    private AdresDAO adresDAO;
    private AssetDAO assetDAO;
    private TransactieDAO transactieDAO;
    private BankDAO bankDAO;
    private EuroKoersDAO euroKoersDAO;

    @Autowired
    public RootRepository(KlantDAO klantDAO, RekeningDAO rekeningDAO, PortefeuilleItemDAO portefeuilleItemDAO,
                          AdresDAO adresDAO, AssetDAO assetDAO, TransactieDAO transactieDAO, BankDAO bankDAO,
                          EuroKoersDAO euroKoersDAO) {
        this.klantDAO = klantDAO;
        this.rekeningDAO = rekeningDAO;
        this.portefeuilleItemDAO = portefeuilleItemDAO;
        this.adresDAO = adresDAO;
        this.assetDAO = assetDAO;
        this.transactieDAO = transactieDAO;
        this.bankDAO = bankDAO;
        this.euroKoersDAO = euroKoersDAO;
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
            item.getTransactiePartij().setTransactiepartijId(klant.getTransactiepartijId());
            portefeuilleItemDAO.bewaarPortefeuilleItemMetKey(item);
        }
        return klant;
    }

    public Klant vindKlantOpId(int klantId) {
        Klant klant = klantDAO.vindKlantOpKlantId(klantId);
        if (klant == null) return null;
        List<PortefeuilleItem> itemsKlant = genereerPortefeuilleVanTransactiepartijMetId(klantId);
        for (PortefeuilleItem portefeuilleItem: itemsKlant) {
            portefeuilleItem.setTransactiePartij(klant);
        }
        klant.setPortefeuille(itemsKlant);
        klant.setRekening(rekeningDAO.vindRekeningOpId(klantDAO.vindRekeningIdVanKlant(klant)));
        return klant;
    }

    public List<PortefeuilleItem> genereerPortefeuilleVanTransactiepartijMetId(int tpId) {
        List<PortefeuilleItem> itemsTransactiepartij = portefeuilleItemDAO
                .genereerPortefeuilleVanTransactiepartijMetId(tpId);
        for (PortefeuilleItem item: itemsTransactiepartij) {
            Asset asset = assetDAO.vindAssetOpId(portefeuilleItemDAO.vindAssetIdVanPortefeuilleItem(item));
            asset.setDagKoers(euroKoersDAO.vindMeestRecenteKoersAsset(asset).getKoers());
            item.setAsset(asset);
        }
        return itemsTransactiepartij;
    }

    public Bank vindBankOpId(int bankId) {
        Bank bank = bankDAO.vindBankOpId(bankId);
        if (bank == null) return null;
        List<PortefeuilleItem> itemsBank = genereerPortefeuilleVanTransactiepartijMetId(bankId);
        for (PortefeuilleItem portefeuilleItem: itemsBank) {
            portefeuilleItem.setTransactiePartij(bank);
        }
        bank.setPortefeuille(itemsBank);
        bank.setRekening(rekeningDAO.vindRekeningOpId(bankDAO.vindRekeningIdVanBank(bank)));
        return bank;
    }

    public Asset vindAssetOpId(int assetId) {
        Asset asset = assetDAO.vindAssetOpId(assetId);
        if (asset == null) return null;
        asset.setDagKoers(euroKoersDAO.vindMeestRecenteKoersAsset(asset).getKoers());
        return asset;
    }

    public PortefeuilleItem vindItemOpId(int itemId) {
        PortefeuilleItem portefeuilleItem = portefeuilleItemDAO.vindItemMetId(itemId);
        Klant klant = klantDAO.vindKlantOpKlantId(portefeuilleItemDAO.vindKlantIdVanPortefeuilleitem(portefeuilleItem));
        portefeuilleItem.setTransactiePartij(klant);
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
        for (PortefeuilleItem portefeuilleItem : portefeuilleItemDAO.genereerPortefeuilleVanTransactiepartijMetId(klantId)) {
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
