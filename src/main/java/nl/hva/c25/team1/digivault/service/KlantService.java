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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if (!validatiePostcode(klant.getAdres().getPostcode())) {
            System.out.println("Er is een onjuiste postcode ingevoerd");
            return null;
        } else if (!validatieMailadres(klant.getAccount().getEmailadres())) {
            return null;
        }
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

    public boolean validatieMailadres(String emailadres) {
        for (Account account : accountDAO.geefAlleAccounts()) {
            if (Objects.equals(account.getEmailadres(), emailadres)) {
                return false;
            }
        }
        String patroon = "^.+@.+\\..+$";
        Pattern pattern = java.util.regex.Pattern.compile(patroon);
        Matcher matcher = pattern.matcher(emailadres);
        return matcher.matches();
    }

    public boolean validatiePostcode(String postcode) {
        return postcode.matches("[1-9]{1}[0-9]{3}[a-zA-Z]{2}");
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

    /**
     * methode om te checken of het wachtwoord aan de eisen voldoet
     * @param wachtwoord
     */

    public boolean validatieWachtwoord (String wachtwoord) {
        return wachtwoord.matches("(?=\\S+$).{10,}");
    }
}
