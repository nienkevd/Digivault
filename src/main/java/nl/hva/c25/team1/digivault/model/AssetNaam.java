package nl.hva.c25.team1.digivault.model;

// review door Sezi, 1 december

/**
 * Enum met de namen van de top 20 populairste assets, peildatum 1 december 2021
 *
 * @author Erwin, studentnummer 500889293
 * @version 2-12-2021
 */

public enum AssetNaam {
    BITCOIN("Bitcoin"),
    ETHEREUM("Ethereum"),
    BINANCE_COIN("Binance Coin"),
    SOLANA("Solana"),
    CARDANO("Cardano"),
    XRP("XRP"),
    POLKADOT("Polkadot"),
    DOGECOIN("Dogecoin"),
    AVALANCHE("Avalanche"),
    TERRA("Terra"),
    LITECOIN("Litecoin"),
    POLYGON("Polygon"),
    ALGORAND("Algorand"),
    BITCOIN_CASH("Bitcoin Cash"),
    STELLAR("Stellar"),
    VECHAIN("VeChain"),
    INTERNET_COMPUTER("Internet Computer"),
    TERRA_USD("TerraUSD"),
    ELROND("Elrond"),
    FILECOIN("Filecoin");

    private String naamAlsString;

    /**
     *
     * @param naamAlsString
     */
    AssetNaam(String naamAlsString) {
        this.naamAlsString = naamAlsString;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return naamAlsString;
    }
}
