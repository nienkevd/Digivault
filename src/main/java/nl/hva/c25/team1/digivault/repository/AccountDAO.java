package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Account;

import java.util.List;

// review door Erwin, 1 december

/**
 * Interface met de te implementeren methodes voor JdbcAccountDAO
 *
 * @author Sezi, studentnummer 500889525
 * @version 4-12-2021
 */

public interface AccountDAO {

    public void bewaar(Account account);

    public void ververs(Account account);

    public Account vindAccountOpEmailadres(String emailadres);

    public List<Account> geefAlleAccounts();
}
