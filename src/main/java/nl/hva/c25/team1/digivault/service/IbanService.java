package nl.hva.c25.team1.digivault.service;

import java.math.BigInteger;
import java.util.Random;

/**
 * @author Erwin, studentnummer 500889293
 * @version 8-12-2021
 */

public class IbanService {
    public static String LANDCODE = "NL";
    public static String BANKCODE = "DIVA";
    public static String CONTROLESTRING_LANDCODE = "207189";       //2321
    public static String CONTROLESTRING_BANKCODE = "11716227990";  //13183110
    public static String CONTROLESTRING_SLOT = "00";
    public static int ASCII_MINIMUM = 48;      // waarden vanaf cijfer 0
    public static int ASCII_MAXIMUM = 57;      // waarden tot en met cijfer 9
    public static int STRING_LENGTE = 10;
    public static BigInteger MODULO = BigInteger.valueOf(97);
    public static BigInteger TOTAAL = BigInteger.valueOf(98);

    public static void main(String[] args) {    // Om tijdelijk automatisch gegenereerde Ibans te laten zien
        System.out.println(IbanGenerator());
    }

    public IbanService() {
        super();
    }

    public static String IbanGenerator() {
        String bankrekeningNummer = genereerRandomBankrekeningNummer();
        String controlegetal = genereerControleGetal(bankrekeningNummer);
        StringBuilder iban = new StringBuilder(LANDCODE + controlegetal + " " + BANKCODE + " " + bankrekeningNummer);
        return iban.toString();
    }

    /**
     * Genereert een random bankrekeningNummer van 10 cijfers
     * @return random gegenereerde bankrekeningNummer als String
     */
    public static String genereerRandomBankrekeningNummer() {
        Random random = new Random();
        String bankrekeningNummer = random.ints(ASCII_MINIMUM, ASCII_MAXIMUM + 1)
                .limit(STRING_LENGTE)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return bankrekeningNummer;
    }

    public static String genereerControleGetal(String controleString) {
        StringBuilder controleStringBuilderReeks = new StringBuilder(CONTROLESTRING_BANKCODE + controleString +
                CONTROLESTRING_LANDCODE + CONTROLESTRING_SLOT);
        String controleReeksString = controleStringBuilderReeks.toString();
        BigInteger controleReeksLong = new BigInteger(controleReeksString);
        BigInteger result = controleReeksLong.mod(MODULO);
        BigInteger controleGetal = TOTAAL.subtract(result);
        return controleGetal.toString();
    }
}
