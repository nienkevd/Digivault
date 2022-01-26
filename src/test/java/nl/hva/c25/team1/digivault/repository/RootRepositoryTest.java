//// gemaakt door Anthon van Dijk
//
//package nl.hva.c25.team1.digivault.repository;
//
//import nl.hva.c25.team1.digivault.model.*;
//import org.junit.jupiter.api.*;
//import org.mockito.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import static org.assertj.core.api.Assertions.*;
//
//@SpringBootTest
//class RootRepositoryTest {
//
//    RootRepository rootRepository;
//
//    @MockBean
//    JdbcAssetDAO jdbcAssetDAO;
//
////    @Mock
////    EuroKoers euroKoers;
////    @Mock
////    EuroKoersDAO euroKoersDAO;
////
////    Asset asset;
////    double dagkoers;
//
//    @Autowired
//    public RootRepositoryTest(RootRepository rootRepository) {
//        super();
//        this.rootRepository = rootRepository;
//    }
//
//    @BeforeEach
//    void setUp() {
////        asset = new Asset(1);
////        dagkoers = 30582.458;
//        Mockito.when(jdbcAssetDAO.vindAssetOpId(0)).thenReturn(null);
////        Mockito.when(assetDAO.vindAssetOpId(1)).thenReturn(asset);
////        Mockito.when(euroKoersDAO.vindMeestRecenteKoersAsset(asset)).thenReturn(euroKoers);
////        Mockito.when(euroKoers.getKoers()).thenReturn(dagkoers);
//    }
//
//    @Test
//    void vindAssetOpId() {
//        System.out.println(jdbcAssetDAO);
//        // niet bestaande asset
//        Asset actual = rootRepository.vindAssetOpId(7);
//        assertThat(actual).isNull();
//
//        // bekende asset
////        Asset actual = rootRepository.vindAssetOpId(1);
////        assertThat(actual).isNotNull().isEqualTo(asset);
////        assertThat(actual.getAssetId()).isEqualTo(1);
////        assertThat(actual.getDagKoers()).isEqualTo(dagkoers);
//    }
//}