package nl.hva.c25.team1.digivault.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Test;


import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testen bij TokenService
 *
 * @author Anneke, studentnummer 500889251
 */

class TokenServiceTest {

    String testJWT;
    private final String KEY = SecretKeyService.getSecret();
    private SecretKeyService secretKeyService;
    private TokenService tokenServiceTest = new TokenService(secretKeyService);
    private final String ISSUER = "Digivault";
    private final String TEST_EMAIL = "anneke@hva.nl";


    @Test
    void maakEenLegeHMAC512SignedToken() {
        String signed = JWT.create().sign(Algorithm.HMAC256(KEY));
        assertThat(signed).isNotNull();
        String[] parts = signed.split("\\.");
        String headerJson = new String(Base64.decodeBase64(parts[0]), StandardCharsets.UTF_8);
        assertThat(headerJson).contains("alg", "HS256");
        assertThat(headerJson).contains("typ", "JWT");
        JWTVerifier verified = JWT.require(Algorithm.HMAC256(KEY))
                .build();
        assertThat(verified).isNotNull();
    }

    @Test
    void maakVolledigTokenEnCheckInhoud() {
        testJWT = tokenServiceTest.maakJWT(TEST_EMAIL);
        DecodedJWT decodedJWT = JWT.decode(testJWT);
        assertThat(decodedJWT).isNotNull();
        assertThat(decodedJWT.getClaims().get("emailadres").asString()).isEqualTo(TEST_EMAIL);
        assertThat(decodedJWT.getIssuer()).isEqualTo(ISSUER);
    }

}