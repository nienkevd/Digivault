package nl.hva.c25.team1.digivault.service;

import java.util.Random;

/**
 * @author Erwin, studentnummer 500889293
 * @version 8-12-2021
 */

public class IbanService {
    public static String LANDCODE = "NL";
    public static String BANKCODE = "DIVA";
    public static int ASCII_MINIMUM = 48;      // waarden vanaf cijfer 0
    public static int ASCII_MAXIMUM = 57;     // waarden tot en met cijfer 9
    public static int STRING_LENGTE = 10;

    public static void main(String[] args) {
        System.out.println(IbanGenerator());
    }

    public IbanService() {
        super();
    }

    public static String IbanGenerator() {
        String bankrekeningNummer = genereerRandomBankrekeningNummer();
        String controlegetal = genereerControleGetal();

        StringBuilder iban = new StringBuilder(LANDCODE + controlegetal + " " + BANKCODE + " " + bankrekeningNummer);
        return iban.toString();
    }

    public static String genereerRandomBankrekeningNummer() {
        Random random = new Random();

        String bankrekeningNummer = random.ints(ASCII_MINIMUM, ASCII_MAXIMUM + 1)
                .limit(STRING_LENGTE)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return bankrekeningNummer;
    }

    public static String genereerControleGetal() {
        String controleGetal = "20";
        return controleGetal;
    }
}
