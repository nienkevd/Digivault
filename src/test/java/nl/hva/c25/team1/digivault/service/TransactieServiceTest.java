// gemaakt door Anthon van Dijk

package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // tbv integratie test met BankService
class TransactieServiceTest {

    private TransactieService transactieService;
    private BankService bankService;

    private Transactie transactie1, transactie2, transactie3, transactie4, transactie5;
    private TransactiePartij koper1, koper2, verkoper, bank;
    private List<PortefeuilleItem> portefeuille;
    private List<Asset> assetsVanPortefeuille;
    private Asset bestaandeAsset, nietBestaandeAsset;

    @Autowired // DI
    public TransactieServiceTest(TransactieService transactieService, BankService bankService) {
        super();
        this.transactieService = transactieService;
        this.bankService = bankService;
    }

    @BeforeEach
    void setUp() {

        transactie1 = Mockito.mock(Transactie.class);
        transactie2 = Mockito.mock(Transactie.class);
        transactie3 = Mockito.mock(Transactie.class);
        transactie4 = Mockito.mock(Transactie.class);
        transactie5 = Mockito.mock(Transactie.class);
        koper1 = Mockito.mock(Klant.class);
        koper2 = Mockito.mock(Klant.class);
        verkoper = Mockito.mock(Klant.class);
        bank = Mockito.mock(Bank.class);
        bestaandeAsset = Mockito.mock(Asset.class);
        nietBestaandeAsset = Mockito.mock(Asset.class);
        portefeuille = new ArrayList<>(); // vul portefeuille met gemockte items
        for (int itemTeller = 0; itemTeller < 20; itemTeller++) {
            portefeuille.add(Mockito.mock(PortefeuilleItem.class));
        }
        assetsVanPortefeuille = new ArrayList<>();
        for (int assetTeller = 0; assetTeller < 20; assetTeller++) {
            assetsVanPortefeuille.add(Mockito.mock(Asset.class));
        }
        assetsVanPortefeuille.add(nietBestaandeAsset); // toegevoegd ivm edge-case verkoperHeeftVoldoendeCrypto()
    }

    @Test
    void testBankServiceAvailable() {
        assertThat(bankService).isNotNull(); // assertJ
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
        Mockito.when(transactie2.getAsset()).thenReturn(bestaandeAsset);
        Mockito.when(transactie5.getAsset()).thenReturn(nietBestaandeAsset);
        Mockito.when(bestaandeAsset.getAssetId()).thenReturn(19); // assetId = 19
        Mockito.when(nietBestaandeAsset.getAssetId()).thenReturn(21); // assetId = NIET BESTAAND
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

        Mockito.when(bestaandeAsset.getDagKoers()).thenReturn(100.); // dagkoers = 100
        Mockito.when(((Bank) bank).getTransactiePercentage()).thenReturn(2.5); // bankperc =2.5

        // transactie3: koper bank
        Mockito.when(transactie3.getAsset()).thenReturn(bestaandeAsset);
        Mockito.when(transactie3.getAantalCryptos()).thenReturn(10.); // brutoWaarde = 1000
        Mockito.when(transactie3.getKoper()).thenReturn(bank);
        Mockito.when(transactie3.getVerkoper()).thenReturn(verkoper);
        transactieService.setNettoTransactieWaarde(transactie3);
        assertEquals(975., transactieService.getNettoTransactieWaarde(), 0.0001);


        // transactie4: verkoper bank
        Mockito.when(transactie4.getAsset()).thenReturn(bestaandeAsset);
        Mockito.when(transactie4.getAantalCryptos()).thenReturn(10.); // brutoWaarde = 1000
        Mockito.when(transactie4.getKoper()).thenReturn(koper2);
        Mockito.when(transactie4.getVerkoper()).thenReturn(bank);
        transactieService.setNettoTransactieWaarde(transactie4);
        assertEquals(1025., transactieService.getNettoTransactieWaarde(), 0.0001);

    }

    @Test
    void telOp() {
        assertThat(transactieService.telOp(3, 4)).isNotNull().isEqualTo(7); // fluent interface
    }

}