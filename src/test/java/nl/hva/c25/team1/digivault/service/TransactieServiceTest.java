// gemaakt door Anthon van Dijk

package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.*;
import nl.hva.c25.team1.digivault.repository.RootRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactieServiceTest {

    /*
    * Met annotatie @Mock uitgeprobeerd. Werkt hetzelfde als Mockito.mock.
    * Gebruik levert een cleaner beeld op: de setup() methode zou bijna verdwijnen in dit geval!
    * */
    @Mock
    KlantService klantService;

    AssetService assetService;
    BankService bankService;
    RootRepository rootRepository;
    TransactieService transactieService; // klasse onder test
    Transactie transactie1, transactie2, transactie3, transactie4, transactie5;
    Klant koper1, koper2, verkoper;
    Bank bank;
    List<PortefeuilleItem> portefeuille;
    List<Asset> assetsVanPortefeuille;
    Asset assetVanTransactie2, assetVanTransactie5;

    @BeforeEach // voor IEDERE test itt @BeforeAll eenmalig
    void setUp() {
        assetService = Mockito.mock(AssetService.class);
        bankService = Mockito.mock(BankService.class);
        rootRepository = Mockito.mock(RootRepository.class);
        transactieService = new TransactieService(klantService, assetService, bankService, rootRepository);

        transactie1 = Mockito.mock(Transactie.class);
        transactie2 = Mockito.mock(Transactie.class);
        transactie3 = Mockito.mock(Transactie.class);
        transactie4 = Mockito.mock(Transactie.class);
        transactie5 = Mockito.mock(Transactie.class);
        koper1 = Mockito.mock(Klant.class);
        koper2 = Mockito.mock(Klant.class);
        verkoper = Mockito.mock(Klant.class);
        bank = Mockito.mock(Bank.class);
        assetVanTransactie2 = Mockito.mock(Asset.class);
        assetVanTransactie5 = Mockito.mock(Asset.class);
        portefeuille = new ArrayList<>(); // vul portefeuille met gemockte items
        for (int itemTeller = 0; itemTeller < 20; itemTeller++) {
            portefeuille.add(Mockito.mock(PortefeuilleItem.class));
        }
        assetsVanPortefeuille = new ArrayList<>();
        for (int assetTeller = 0; assetTeller < 20; assetTeller++) {
            assetsVanPortefeuille.add(Mockito.mock(Asset.class));
        }
        assetsVanPortefeuille.add(assetVanTransactie5); // toegevoegd ivm edge-case verkoperHeeftVoldoendeCrypto()
    }

    @Test
    void koperEnVerkoperVerschillen() {

        Mockito.when(transactie1.getKoper()).thenReturn(koper1);
        Mockito.when(transactie1.getVerkoper()).thenReturn(verkoper);
        Mockito.when(transactie2.getKoper()).thenReturn(koper2);
        Mockito.when(transactie2.getVerkoper()).thenReturn(verkoper);
        Mockito.when(koper1.getTransactiepartijId()).thenReturn(254);
        Mockito.when(koper2.getTransactiepartijId()).thenReturn(253);
        Mockito.when(verkoper.getTransactiepartijId()).thenReturn(254);


       // gelijke klanten
        assertEquals(false, transactieService.koperEnVerkoperVerschillen(transactie1));

        // verschillende klanten
        assertEquals(true, transactieService.koperEnVerkoperVerschillen(transactie2));
    }

    @Test
    void verkoperHeeftVoldoendeCrypto() {

        Mockito.when(transactie2.getVerkoper()).thenReturn(verkoper);
        Mockito.when(transactie5.getVerkoper()).thenReturn(verkoper);
        Mockito.when(verkoper.getPortefeuille()).thenReturn(portefeuille);
        Mockito.when(transactie2.getAsset()).thenReturn(assetVanTransactie2);
        Mockito.when(transactie5.getAsset()).thenReturn(assetVanTransactie5);
        Mockito.when(assetVanTransactie2.getAssetId()).thenReturn(19); // assetId = 19
        Mockito.when(assetVanTransactie5.getAssetId()).thenReturn(21); // assetId = NIET BESTAAND
        for (int itemTeller = 0; itemTeller < 20; itemTeller++) {
            Mockito.when(portefeuille.get(itemTeller).getAsset()).thenReturn(assetsVanPortefeuille.get(itemTeller));
        }
        for (int assetTeller = 0; assetTeller < 20; assetTeller++) {
            // assetId 19 zit in assetsVanPortefeuille.get(18)
            Mockito.when(assetsVanPortefeuille.get(assetTeller).getAssetId()).thenReturn(assetTeller + 1);
        }
        Mockito.when(transactie2.getAantalCryptos()).thenReturn(5.6);

        // assetId van crypto komt niet voor in portefeuille
        assertEquals(false, transactieService.verkoperHeeftVoldoendeCrypto(transactie5));

        // voldoende crypto aanwezig
        Mockito.when(portefeuille.get(18).getHoeveelheid()).thenReturn(10.567); // meer dan 5.6
        assertEquals(true, transactieService.verkoperHeeftVoldoendeCrypto(transactie2));

        // onvoldoende crypto
        Mockito.when(portefeuille.get(18).getHoeveelheid()).thenReturn(3.67); // minder dan 5.6
        assertEquals(false, transactieService.verkoperHeeftVoldoendeCrypto(transactie2));

        // exact gelijk
        Mockito.when(portefeuille.get(18).getHoeveelheid()).thenReturn(5.6); // exact 5.6
        assertEquals(true, transactieService.verkoperHeeftVoldoendeCrypto(transactie2));

    }

    @Test
    void setNettoTransactieWaarde() {

        Mockito.when(assetVanTransactie2.getDagKoers()).thenReturn(100.); // dagkoers = 100
        Mockito.when(bank.getTransactiePercentage()).thenReturn(2.5); // bankperc =2.5

        // transactie3: koper bank
        Mockito.when(transactie3.getAsset()).thenReturn(assetVanTransactie2);
        Mockito.when(transactie3.getAantalCryptos()).thenReturn(10.); // brutoWaarde = 1000
        Mockito.when(transactie3.getKoper()).thenReturn(bank);
        Mockito.when(transactie3.getVerkoper()).thenReturn(verkoper);
        transactieService.setNettoTransactieWaarde(transactie3);
        assertEquals(975., transactieService.getNettoTransactieWaarde(), 0.0001);


        // transactie4: verkoper bank
        Mockito.when(transactie4.getAsset()).thenReturn(assetVanTransactie2);
        Mockito.when(transactie4.getAantalCryptos()).thenReturn(10.); // brutoWaarde = 1000
        Mockito.when(transactie4.getKoper()).thenReturn(koper2);
        Mockito.when(transactie4.getVerkoper()).thenReturn(bank);
        transactieService.setNettoTransactieWaarde(transactie4);
        assertEquals(1025., transactieService.getNettoTransactieWaarde(), 0.0001);

    }
}