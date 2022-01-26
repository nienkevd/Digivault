package nl.hva.c25.team1.digivault.service;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testen bij de IbanService
 *
 * @author Erwin, studentnummer 500889293
 * @since 25-1-2022
 */

class IbanServiceTest {

    private final IbanService serviceUnderTest = new IbanService();
    private final String iban = "080772547";

    @Test
    void testIbanServiceBeschikbaar() {
        assertThat(serviceUnderTest).isNotNull();
    }
}