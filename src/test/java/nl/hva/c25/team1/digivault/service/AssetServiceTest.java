//package nl.hva.c25.team1.digivault.service;
//
//import nl.hva.c25.team1.digivault.model.Asset;
//import nl.hva.c25.team1.digivault.repository.JdbcAssetDAO;
//import nl.hva.c25.team1.digivault.repository.RootRepository;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import static org.assertj.core.api.Assertions.assertThat;
//
//// review door Sezi, 6 december
//
///**
// * Testen bij de AssetService
// *
// * @author Erwin, studentnummer 500889293
// * @since 5-12-2021
// */
//
//class AssetServiceTest {
//
//    @MockBean
//    private static JdbcAssetDAO mockDAO = Mockito.mock(JdbcAssetDAO.class);
//    @MockBean
//    private static RootRepository mockRoot = Mockito.mock(RootRepository.class);
//
//    private final AssetService serviceUnderTest = new AssetService(mockDAO, mockRoot);
//    private final Asset expected = new Asset(1, "BNB", "Binance_Coin", 31558.66369);
//
//    @Test
//    void vindAssetOpId() {
//        Mockito.when(mockDAO.vindAssetOpId(1)).thenReturn(expected);
//        Asset actual = serviceUnderTest.vindAssetOpId(1);
//        assertThat(actual).isNotNull();
//    }
//
//    @Test
//    void verversAsset() {
//        Mockito.when(mockDAO.vindAssetOpId(1)).thenReturn(expected);
//        String actual = serviceUnderTest.updateAsset(expected);
//        assertThat(actual).contains("geüpdatet");
//    }
//}
