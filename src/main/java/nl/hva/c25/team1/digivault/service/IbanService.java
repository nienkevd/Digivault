package nl.hva.c25.team1.digivault.service;

import java.math.BigInteger;
import java.util.Random;

/**
 * Service die volgens de 11-proef DIVA-IBAN-nummers genereert
 *
 * @author Erwin, studentnummer 500889293
 * @since 8-12-2021
 */

public class IbanService {
    public static String LANDCODE = "NL";
    public static String BANKCODE = "DIVA";
    public static String CONTROLESTRING_LANDCODE = "2321";
    public static String CONTROLESTRING_BANKCODE = "13183110";
    public static String CONTROLESTRING_SLOT = "00";
    public static int ASCII_MINIMUM = 48;      // ASCII-waarden vanaf cijfer 0
    public static int ASCII_MAXIMUM = 57;      // ASCII-waarden tot en met cijfer 9
    public static int STRING_LENGTE = 10;      // gewenste lengte van bankrekeningNummer
    public static BigInteger MODULO = BigInteger.valueOf(97);
    public static BigInteger BRUTO = BigInteger.valueOf(98);

    public static void main(String[] args) {    // Om tijdelijk automatisch gegenereerde Ibans te laten zien
        System.out.println(IbanGenerator());
    }

    /**
     * Constructor van de IbanService
     */
    public IbanService() {
        super();
    }

    /**
     * Genereert een IBAN die voldoet aan de 11-proef; Eerst wordt een random bankrekeningnummer van 10 cijfers
     * gegenereerd, hier worden vervolgens de landcode, het gegenereerde controlegetal en de bankcode aan toegevoegd
     * @return de gegenereerde IBAN als String
     */
    public static String IbanGenerator() {
        String bankrekeningNummer = genereerRandomBankrekeningNummer();
        String controlegetal = genereerControleGetal(bankrekeningNummer);
        StringBuilder iban = new StringBuilder(LANDCODE + controlegetal  + BANKCODE +  bankrekeningNummer);
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

    /**
     * Deze methode voegt een gegenereerd bankrekeningnummer samen met de numerieke waarden van bankcode, de landcode
     * en twee nullen aan het eind. Deze StringBuilder wordt een String, en daarna omgezet naar een BigInteger om de
     * bijbehorende modulo 97 berekening te kunnen doen en dit af te trekken van 98. De uitkomst (=controlegetal) wordt
     * als String teruggegeven
     * @param bankrekeningNummer het gegenereerde bankrekeningnummer waarvoor een controlegetal berekend wordt
     * @return controlegetal als String
     */
    public static String genereerControleGetal(String bankrekeningNummer) {
        StringBuilder controleStringBuilderReeks = new StringBuilder(CONTROLESTRING_BANKCODE + bankrekeningNummer +
                CONTROLESTRING_LANDCODE + CONTROLESTRING_SLOT);
        String controleReeksString = controleStringBuilderReeks.toString();
        BigInteger controleReeksLong = new BigInteger(controleReeksString);
        BigInteger result = controleReeksLong.mod(MODULO);
        BigInteger controleGetal = BRUTO.subtract(result);
        return controleGetal.toString();
    }
}
