package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.repository.JdbcAccountDAO;
import nl.hva.c25.team1.digivault.repository.JdbcKlantDAO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

/**
 * Service van de klasse Klant
 *
 * @author Anneke, studentnummer 500889251
 * @version 1-12-2021
 */

@Service
public class KlantService {

    private JdbcKlantDAO klantDAO;
    private JdbcAccountDAO accountDAO;

    /**
     *
     * @param klantDAO interface klantDAO
     */
    public KlantService(JdbcKlantDAO klantDAO) {
        this.klantDAO = klantDAO;
    }

    /**
     *
     * @param klant opslaan
     */
    public Klant bewaarKlant(Klant klant) {
        return klantDAO.bewaarKlantMetSK(klant);
    }

    /**
     *
     * @param klandId van klant die gevonden moet worden
     * @return Klant
     */
    public Klant vindKlantOpKlantID(int klandId) {
        return klantDAO.vindKlantOpKlantId(klandId);
    }

    /**
     *
     * @return List<Klant>
     */
    public List<Klant> vindAlleKlanten() {
        return klantDAO.vindAlleKlanten();
    }

    /**
     *
     * update een bestaande klant uit de database
     * @param klant die geupdate moet worden
     * @return String melding die aangeeft of update geslaagd is
     */
    public String updateKlant(Klant klant) {
        if (klantDAO.vindKlantOpKlantId(klant.getKlantId()) == null ) {
            return "Klant bestaat niet, update mislukt.";
        } else {
            klantDAO.update(klant);
            return "Update geslaagd";
        }
    }

    /**
     * Check of mailadres al in database zit
     * @param emailadres te checken mailadres
     * @return boolean
     */
    public boolean validatieMailadres(String emailadres) {
        for (Account account : accountDAO.geefAlleAccounts()) {
            if (Objects.equals(account.getEmailadres(), emailadres)) {
                return false;
            }
        }
        return true;
    }

    /**
     * methode om te checken of de ingevoerde bsn correct is
     * @param bsn
     */

    public boolean validateBsn (String bsn ){
        int bsnInt = Integer.valueOf(bsn);

        if (bsnInt <= 9999999 || bsnInt > 999999999) {
            return false;
        }
        int sum = -1 * bsnInt % 10;

        for (int multiplier = 2; bsnInt > 0; multiplier++) {
            int val = (bsnInt /= 10) % 10;
            sum += multiplier * val;
        }

        return sum != 0 && sum % 11 == 0;
    }

    /**
     * methode om te checken of de klant 18 jaar of ouder is
     * @param geboortedatum
     */

    public boolean validatieGeboortedatum (LocalDate geboortedatum) {
        return Period.between(geboortedatum, LocalDate.now()).getYears()>=18;
    }
}
