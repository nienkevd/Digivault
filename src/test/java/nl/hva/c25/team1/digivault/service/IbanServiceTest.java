package nl.hva.c25.team1.digivault.service;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testen bij de IbanService
 *
 * @author Erwin, studentnummer 500889293
 * @since 25-1-2022
 */

class IbanServiceTest {

    private final IbanService serviceUnderTest = new IbanService();
    public static final int MIN_SIZE = 15;
    public static final int MAX_SIZE = 34;
    public static final BigInteger MAGIC_NUMBER = new BigInteger("97");

    @Test
    void testIbanServiceBeschikbaar() {
        assertThat(serviceUnderTest).isNotNull();
    }

    @Test
    void IbanGenerator() {
        boolean expected = true;
        String[] ibans = new String[10];
        String ongeldigeIban = "123456789";

        // Voer tien keer een elfproef-test uit op nieuw gegenereerde Iban
        for (int i = 0; i < ibans.length; i++) {
            ibans[i] = IbanService.IbanGenerator();
            assertThat(doeIbanCheck(ibans[i])).isEqualTo(expected);
        }

        // Controlecheck: een ongeldig IBAN-nummer geeft een false
        System.out.println();
        assertThat(doeIbanCheck(ongeldigeIban)).isNotEqualTo(expected);
    }

    boolean doeIbanCheck(String iban) {
        String nieuweIban = iban.trim();

        // Controleert of IBAN-lengte goed is
        if (nieuweIban.length() < MIN_SIZE || nieuweIban.length() > MAX_SIZE) {
            return false;
        }

        // Verplaats de eerste vier tekens naar het einde van de tekenreeks
        nieuweIban = nieuweIban.substring(4) + nieuweIban.substring(0, 4);

        // Vervang elke letter in de string door twee cijfers
        StringBuilder ibanAlsGetal = new StringBuilder();
        for (int i = 0;i < nieuweIban.length();i++) {
            ibanAlsGetal.append(Character.getNumericValue(nieuweIban.charAt(i)));
        }

        // Interpreteer de string als een decimaal geheel getal en berekent de remainder bij deling door 97
        BigInteger ibanNumber = new BigInteger(ibanAlsGetal.toString());
        boolean resultaat = ibanNumber.mod(MAGIC_NUMBER).intValue() == 1;
        return resultaat;
    }
}
