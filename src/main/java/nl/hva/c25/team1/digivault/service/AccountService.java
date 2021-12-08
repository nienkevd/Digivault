package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.repository.JdbcAccountDAO;
import nl.hva.c25.team1.digivault.repository.RootRepository;

import java.util.List;

// review door Erwin, 1 december

/**
 * @author Sezi, studentnummer 500889525
 * @version 8-12-2021
 */

public class AccountService {

    private JdbcAccountDAO accountDAO;
    private RootRepository rootRepository;

    public AccountService(JdbcAccountDAO accountDAO/* , RootRepository rootRepository */) {
        this.accountDAO = accountDAO;
        //this.rootRepository = rootRepository;
    }

    public int bewaarAccountMetSK(Account account) {
        return accountDAO.bewaarAccountMetSK(account);
    }

    public void updateKlant(Account account) {
        accountDAO.updateAccount(account);
    }

    public Account vindAccountOpAccountId(int accountId) {
        return accountDAO.vindAccountOpAccountId(accountId);
    }

    public List<Account> geefAlleAccounts() {
        return accountDAO.geefAlleAccounts();
    }
}
