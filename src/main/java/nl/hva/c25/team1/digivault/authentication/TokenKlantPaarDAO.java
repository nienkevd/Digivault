package nl.hva.c25.team1.digivault.authentication;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.authentication.TokenKlantPaar;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 *
 * @author Anneke, studentnummer 500889251
 * @since 15-12-2021
 *
 */


public interface TokenKlantPaarDAO {

    Optional<TokenKlantPaar> vindPaarOpKlant(Klant klant);
    void save(TokenKlantPaar tokenKlantPaar);

    Optional<TokenKlantPaar> vindOpKey(UUID key);

    void delete(UUID uuid);





}
