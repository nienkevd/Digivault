package nl.hva.c25.team1.digivault.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.repository.KlantDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 *
 * @author Anneke, studentnummer 500889251
 * @since 14-12-2021
 *
 */

@Service
public class TokenService {
    private SecretKeyService secretKeyService;

    @Autowired
    public TokenService(SecretKeyService secretKeyService) {
        this.secretKeyService = secretKeyService;
    }

    private String token;
    private final String KEY = SecretKeyService.getSecret();
    private Algorithm algorithm = Algorithm.HMAC256(KEY);
    private final String ISSUER = "Digivault";
    private final String AUDIENCE = "https://www.digivault.com";
    private Map<String, Object> headerClaims = new HashMap<>();
    private final String ALG = "HS256";
    private final String TYP = "JWT";
    private Instant now;

    public String maakJWT(String emailadres) {
        now = Instant.now();
        headerClaims.put("alg", ALG);
        headerClaims.put("typ", TYP);
        try {
            token = JWT.create()
                    .withIssuer(ISSUER)
                    .withAudience(AUDIENCE)
                    .withClaim("emailadres", emailadres)
                    .withIssuedAt(Date.from(now))
                    .withExpiresAt(Date.from(now.plus(2 ,ChronoUnit.MINUTES)))
                    .withHeader(headerClaims)
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error tijdens het maken van een token voor: " + emailadres);
        }
        return token;
    }

    public boolean valideerJWT(String token){
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .withAudience(AUDIENCE)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            Date expirationTime = jwt.getExpiresAt();
            Date now = Date.from(Instant.now());
            if (expirationTime != null && now.after(expirationTime)) {
                return false;
            }
            return true;
        } catch (JWTVerificationException exception){
            return false;
        }
    }       // if false: vraag klant om refreshtoken en valideer deze
    // check refresh en als correct --> geef klant nieuwe JWT en nieuwe refresh

//    public void genereerNieuweTokens(UUID token, Klant klant){
//        // JWT is verlopen, klant krijgt 401
//        // vanuit de controller wordt om refresh token van klant gevraagd
//        // valideer deze refresh token, als hij niet leeg terug komt dan krijgt klant 2 nieuwe tokens
//        if (!(valideer(token).isEmpty())){
//            // maak nieuwe jwt:
//            String jwt = maakJWT(klant.getAccount().getEmailadres());
//            // maak nieuwe refresh
//            TokenKlantPaar tokenKlantPaar = authoriseer(klant);
//        }
//        /* TODO: geeft deze methode iets terug? TokenKlantPaar? JWT komt in header getPortefeuille */
//    }

//    public TokenKlantPaar authoriseer(Klant klant) {
//        Optional<TokenKlantPaar> paarOptie = tokenKlantPaarDAO.vindPaarOpKlant(klant);
//        if (paarOptie.isPresent()) {
//            tokenKlantPaarDAO.delete(paarOptie.get().getKey());
//        }
//        UUID token = UUID.randomUUID();
//        TokenKlantPaar tokenKlantPaar = new TokenKlantPaar(token, klant);
//        tokenKlantPaarDAO.save(tokenKlantPaar);
//        return tokenKlantPaar;
//    }
//
//    public Optional<Klant> valideer(UUID token) {
//        Optional<TokenKlantPaar> paarOptie = tokenKlantPaarDAO.vindOpKey(token);
//        if (paarOptie.isPresent()) {
//            return Optional.of(paarOptie.get().getKlant());
//        }
//        return Optional.empty();
//    }



}

