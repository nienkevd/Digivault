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
    protected SecretKeyService secretKeyService;
    protected final TokenKlantPaarDAO tokenKlantPaarDAO;
    private Klant klant;
    private KlantDAO klantDAO;

    @Autowired
    public TokenService(SecretKeyService secretKeyService, TokenKlantPaarDAO tokenKlantPaarDAO) {
        this.secretKeyService = secretKeyService;
        this.tokenKlantPaarDAO = tokenKlantPaarDAO;
    }

    private String token;
    private final String KEY = secretKeyService.getSecret();
    private Algorithm algorithm = Algorithm.HMAC256(KEY);
    private final String ISSUER = "Digivault";
    private final String AUDIENCE = "https://www.digivault.com";
    private Map<String, Object> headerClaims = new HashMap<>();
    private final String ALG = "HS256";
    private final String TYP = "JWT";
    private Instant now;

    public String maakJWT(int klantId) {
        now = Instant.now();
        headerClaims.put("alg", ALG);
        headerClaims.put("typ", TYP);
        try {
            token = JWT.create()
                    .withIssuer(ISSUER)
                    .withAudience(AUDIENCE)
                    .withClaim("klantId", klantId)
                    .withIssuedAt(Date.from(now))
                    .withExpiresAt(Date.from(now.plus(1,ChronoUnit.HOURS)))
                    .withHeader(headerClaims)
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error tijdens het maken van een token voor: " + klantId);
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
//                Map<String,Integer> klantIdMap = jwt.getClaim("klantId");
//                Klant klant = klantDAO.vindKlantOpKlantId(klantIdMap.get(0));
                // vraag klant om refreshtoken en valideer deze
                // methode authoriseer(Klant klant)? --> waar haal ik klant vandaan?
                // of gaat dit via de controller?
                // if refresh = correct --> geef klant nieuwe JWT en nieuwe refresh
//                return false;
            }
            return true;
        } catch (JWTVerificationException exception){
            return false;
        }
    }

    public TokenKlantPaar authoriseer (Klant klant) {
        Optional<TokenKlantPaar> paarOptie = tokenKlantPaarDAO.vindPaarOpKlant(klant);
        if (paarOptie.isPresent()) {
            tokenKlantPaarDAO.delete(paarOptie.get().getKey());
        }
        UUID token = UUID.randomUUID();
        TokenKlantPaar tokenKlantPaar = new TokenKlantPaar(token, klant);
        tokenKlantPaarDAO.save(tokenKlantPaar);
        return tokenKlantPaar;
    }

    public Optional<Klant> valideer(UUID token) {
        Optional<TokenKlantPaar> paarOptie = tokenKlantPaarDAO.vindOpKey(token);
        if (paarOptie.isPresent()) {
            return Optional.of(paarOptie.get().getKlant());
        }
        return Optional.empty();
    }

}

