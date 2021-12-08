package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Account;

import java.util.List;

// review door Erwin, 1 december

/**
 * Interface met de te implementeren methodes voor JdbcAccountDAO
 *
 * @author Sezi, studentnummer 500889525
 * @version 6-12-2021
 */

public interface AccountDAO {

    public int bewaarAccountMetSK(Account account);

    public void updateAccount(Account account);

    public Account vindAccountOpAccountId(int accountId);

    public List<Account> geefAlleAccounts();
}
