package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Asset;
import nl.hva.c25.team1.digivault.repository.JdbcAssetDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.assertj.core.api.Assertions.assertThat;

// review door Sezi, 6 december

class AssetServiceTest {

    private static Asset expected = new Asset(1, "BNB", "Binance_Coin");

    @MockBean
    private static JdbcAssetDAO mockDAO = Mockito.mock(JdbcAssetDAO.class);
    AssetService serviceUnderTest = new AssetService(mockDAO);

    @Test
    void vindAssetOpId() {
        Mockito.when(mockDAO.vindAssetOpId(1)).thenReturn(expected);
        Asset actual = serviceUnderTest.vindAssetOpId(1);
        assertThat(actual).isNotNull().isEqualTo(expected);
    }

    @Test
    void verversAsset() {
        Mockito.when(mockDAO.vindAssetOpId(1)).thenReturn(expected);
        String actual = serviceUnderTest.updateAsset(expected);
        assertThat(actual).contains("ververst");
    }
}
