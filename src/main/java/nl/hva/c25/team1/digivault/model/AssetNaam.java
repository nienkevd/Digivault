package nl.hva.c25.team1.digivault.model;

/**
 * @Author: Erwin, studentnummer 500889293
 * @Version: 1-12-2021
 *
 * Enum met de namen van de top 20 populairste assets, peildatum 1 december 2021
 */

public enum AssetNaam {
    BITCOIN("Bitcoin (BTC)"),
    ETHEREUM("Ethereum (ETH)"),
    BINANCE_COIN("Binance Coin (BNB)"),
    SOLANA("Solana (SOL)"),
    CARDANO("Cardano (ADA)"),
    XRP("XRP (XRP)"),
    POLKADOT("Polkadot (DOT)"),
    DOGECOIN("Dogecoin (DOGE)"),
    AVALANCHE("Avalanche (AVAX)"),
    TERRA("Terra (LUNA)"),
    LITECOIN("Litecoin (LTC)"),
    POLYGON("Polygon (MATIC)"),
    ALGORAND("Algorand (ALGO)"),
    BITCOIN_CASH("Bitcoin Cash (BCH)"),
    STELLAR("Stellar (XLM)"),
    VECHAIN("VeChain (VET)"),
    INTERNET_COMPUTER("Internet Computer (ICP)"),
    TERRA_USD("TerraUSD (UST)"),
    ELROND("Elrond (EGLD)"),
    FILECOIN("Filecoin (FIL)");

    private String naamAlsString;

    AssetNaam(String naamAlsString) {
        this.naamAlsString = naamAlsString;
    }

    @Override
    public String toString() {
        return naamAlsString;
    }
}
