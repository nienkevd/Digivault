package nl.hva.c25.team1.digivault.authentication;

import org.springframework.stereotype.Service;

@Service
public class SecretKeyService {

    private static final String SECRET = "ItsFunToHaveASecretAndKeepItVeryWell";

    public String getSecret() {
        return SECRET;
    }
}
