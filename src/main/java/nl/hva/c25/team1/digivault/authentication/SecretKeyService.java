package nl.hva.c25.team1.digivault.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecretKeyService {

    private static final String SECRET = "ItsFunToHaveASecretAndKeepItVeryWell";

    @Autowired
    public SecretKeyService() {
    }

    public static String getSecret() {
        return SECRET;
    }


}
