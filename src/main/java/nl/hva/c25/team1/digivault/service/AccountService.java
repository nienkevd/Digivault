package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.repository.AccountDAO;
import nl.hva.c25.team1.digivault.repository.JdbcAccountDAO;
import nl.hva.c25.team1.digivault.repository.KlantDAO;
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

    private AccountDAO accountDAO;
    private KlantDAO klantDAO;

    public AccountService(AccountDAO accountDAO, KlantDAO klantDAO) {
        this.accountDAO = accountDAO;
        this.klantDAO = klantDAO;
    }

//    public Account bewaarAccountMetSK(Account account) {
//        return accountDAO.bewaarAccountMetSK(account);
//    }

    public String updateAccount(Klant klant) {
        if (klantDAO.vindKlantOpKlantId(klant.getTransactiepartijId()) == null) {
            return "Klant bestaat niet, update mislukt.";
        } else {
            accountDAO.updateAccount(klant);
            return "Update geslaagd";
        }
    }

//    public Account vindAccountOpAccountId(int accountId) {
//        return accountDAO.vindAccountOpAccountId(accountId);
//    }

    public List<Account> geefAlleAccounts() {
        return accountDAO.geefAlleAccounts();
    }

    public Account vindAccountOpKlantId(int klantId){
        return accountDAO.vindAccountOpKlantId(klantId);
    }
}
