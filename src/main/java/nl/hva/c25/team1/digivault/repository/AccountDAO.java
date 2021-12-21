package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.model.Klant;

import java.util.List;

// review door Erwin, 6 december

/**
 * Interface met de te implementeren methodes voor JdbcAccountDAO
 *
 * @author Sezi, studentnummer 500889525
 * @version 6-12-2021
 */

public interface AccountDAO {

//    Account bewaarAccountMetSK(Account account);

    void updateAccount(Klant klant);

//    Account vindAccountOpAccountId(int accountId);

    Account vindAccountOpEmailAdres(String emailAdres);

    List<Account> geefAlleAccounts();

    Account vindAccountOpKlantId(int klantId);
}
