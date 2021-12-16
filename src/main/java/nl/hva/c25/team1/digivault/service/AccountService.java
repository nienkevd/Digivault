package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.repository.JdbcAccountDAO;
import nl.hva.c25.team1.digivault.repository.RootRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// review door Erwin, 6 december

/**
 * @author Sezi, studentnummer 500889525
 * @version 9-12-2021
 */

@Service
public class AccountService {

    private JdbcAccountDAO accountDAO;
    private RootRepository rootRepository;

    public AccountService(JdbcAccountDAO accountDAO/* , RootRepository rootRepository */) {
        this.accountDAO = accountDAO;
        //this.rootRepository = rootRepository;
    }

    public Account bewaarAccountMetSK(Account account) {
        return accountDAO.bewaarAccountMetSK(account);
    }

    public String updateAccount(Account account) {
        if (accountDAO.vindAccountOpAccountId(account.getAccountId()) == null ) {
            return "Account bestaat niet, update mislukt.";
        } else {
            accountDAO.updateAccount(account);
            return "Update geslaagd";
        }
    }

    public Account vindAccountOpAccountId(int accountId) {
        return accountDAO.vindAccountOpAccountId(accountId);
    }

    public List<Account> geefAlleAccounts() {
        return accountDAO.geefAlleAccounts();
    }
}
