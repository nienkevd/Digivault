package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.authentication.HashService;
import nl.hva.c25.team1.digivault.model.*;
import nl.hva.c25.team1.digivault.repository.AccountDAO;
import nl.hva.c25.team1.digivault.repository.JdbcAccountDAO;
import nl.hva.c25.team1.digivault.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Service voor het registreren van een Klant
 *
 * @author Erwin, studentnummer 500889293
 * @since 8-12-2021
 */

@Service
public class RegistratieService {
    private RootRepository rootRepository;
    private AssetService assetService;
    private HashService hashService;
    private JdbcAccountDAO accountDAO;

    public static int BEGINSALDO = 100000;      // Saldo dat een nieuwe klant meekrijgt op zijn rekening

    /**
     * Constructor van de RegistratieService
     * @param rootRepository RootRepository
     * @param assetService AssetService
     * @param hashService HashService
     */
    @Autowired
    public RegistratieService(RootRepository rootRepository, AssetService assetService, HashService hashService, JdbcAccountDAO accountDAO) {
        super();
        this.rootRepository = rootRepository;
        this.assetService = assetService;
        this.hashService = hashService;
        this.accountDAO = accountDAO;
    }

    /**
     * Methode voor het registreren van een klant, de klant krijgt een goed gevulde rekening en lege portefeuille mee:
     * een lijst van 20 assets die op 0 zijn gezet
     *
     * @author Anneke en Erwin
     *
     * @param klant Klant die geregistreerd moet worden
     * @return de geregistreerde Klant
     */
    public Klant registratie(Klant klant) {
        try {
            Rekening rekening = new Rekening(0, genereerIban());
            rekening.setSaldo(BEGINSALDO);
            klant.setRekening(rekening);
            klant.setPortefeuille(aanmaakLegePortefeuille());
            Account account = klant.getAccount();
            account.setWachtwoord(hashService.hash(account.getWachtwoord()));
            if (
                    validateBsn(klant.getBsn()) &&
                    validatieGeboortedatum(klant.getGeboortedatum())  &&
                    validatieMailadres(klant.getAccount().getEmailadres())
            ) {
                return rootRepository.slaKlantOp(klant);
            } else {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
            }
        }
            catch(HttpClientErrorException e){
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
            }
        }

    /**
     * Methode die een lege portefeuille aanmaakt: een lijst van 20 assets die op 0 zijn gezet
     * @return de aangemaakte lege portefeuillelijst
     */
    public List<PortefeuilleItem> aanmaakLegePortefeuille() {
        List<PortefeuilleItem> legePortefeuille = new ArrayList<>();
        for (Asset asset: assetService.geefAlleAssets()) {
            PortefeuilleItem portefeuilleItem = new PortefeuilleItem(0);
            portefeuilleItem.setAsset(asset);
            portefeuilleItem.setKlant(new Klant(0));
            legePortefeuille.add(portefeuilleItem);
        }
        return legePortefeuille;
    }

    /**
     * Methode die doorverwijst naar de IbanService voor het aanmaken van een nieuwe DIVA-Iban
     * @return gegenereerde IBAN als String
     */
    public String genereerIban() {
        return IbanService.IbanGenerator();
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
