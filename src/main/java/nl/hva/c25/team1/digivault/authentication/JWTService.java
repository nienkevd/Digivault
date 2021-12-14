package nl.hva.c25.team1.digivault.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import nl.hva.c25.team1.digivault.model.Klant;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTService {
    private SecretKeyService secretKeyService;

    public JWTService(SecretKeyService secretKeyService) {
        this.secretKeyService = secretKeyService;
    }


    private final String KEY = secretKeyService.getSecret();//!!warning: getSecret will product NullPointerException!!
    private Algorithm algorithm = Algorithm.HMAC256(KEY);
    private String token;
    private Map<String, Object> headerClaims = new HashMap<>();
    private final String ALG = "HS256";
    private final String TYP = "JWT";
    private Date issueTime = Date.from(Instant.now());
    private Instant expirationTime = Instant.now().plus(1, ChronoUnit.HOURS);
    private Date expirationDate = Date.from(expirationTime);



    public String maakJWT(Klant klant) {
        headerClaims.put("alg", ALG);
        headerClaims.put("typ", TYP);
        try {
            token = JWT.create()
                    .withIssuer("Digivault") //kan dit zo?
                    .withClaim("emailadres", klant.getAccount().getEmailadres())
                    .withClaim("klantId", klant.getKlantId())
                    .withIssuedAt(issueTime)
                    .withExpiresAt(expirationDate)
                    .withHeader(headerClaims)
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error tijdens het maken van een token voor: " + klant.getAccount().getEmailadres());
        }
        return token;
    }

    public boolean authenticateJWT(String token){
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("Digivault")
//                    .acceptExpiresAt() : moet long zijn.. hoe?
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception){
            return false;
        }
    }

    public String getClaimFromToken(String token, String claimKey) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaims().get(claimKey).toString();
    }

}
