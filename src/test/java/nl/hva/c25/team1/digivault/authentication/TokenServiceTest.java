package nl.hva.c25.team1.digivault.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import nl.hva.c25.team1.digivault.repository.JdbcKlantDAO;
import nl.hva.c25.team1.digivault.service.KlantService;
import org.apache.commons.codec.binary.Base64;
import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;



class TokenServiceTest {

    private TokenService tokenServiceTest;
    String testToken;



    @Test
    void maakEenLegeHMAC512SignedToken() throws Exception {
        String signed = JWT.create().sign(Algorithm.HMAC256("secret"));
        assertThat(signed).isNotNull();
        String[] parts = signed.split("\\.");
        String headerJson = new String(Base64.decodeBase64(parts[0]), StandardCharsets.UTF_8);
        assertThat(headerJson).contains("alg", "HS256");
        assertThat(headerJson).contains("typ", "JWT");
        JWTVerifier verified = JWT.require(Algorithm.HMAC256("secret"))
                .build();
        assertThat(verified).isNotNull();
    }

//    @Test
//    void valideerAccessTokenMetVerkeerdEmailadres() {
//        testToken= tokenServiceTest.maakJWT("anneke@hva.nl");
//        boolean returnedBooleanValue = tokenServiceTest.valideerJWT(testToken);
//        // print alle eigenschappen van het token
//        DecodedJWT decodedJwt = JWT.decode(testToken);
////        printOutComesTokenValidation(decodedJwt);
//        // als de boolean false is is de test geslaagd
//        if(!returnedBooleanValue){
//            System.out.println("test geslaagd");
//        } else {
//            fail("Moet een JWTVerificationException gooien met InvalidClaimException en false teruggeven");
//        }
//    }



    @Test
    void maakJWTTest(){


    }



    @Test
    void valideerJWT() {
    }
}