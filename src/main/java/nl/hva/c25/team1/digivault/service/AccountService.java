package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.repository.JdbcAccountDAO;
import nl.hva.c25.team1.digivault.repository.RootRepository;

import java.util.List;

public class AccountService {

    private JdbcAccountDAO accountDAO;
    private RootRepository rootRepository;

    public AccountService(JdbcAccountDAO accountDAO, RootRepository rootRepository) {
        this.accountDAO = accountDAO;
        this.rootRepository = rootRepository;
    }

    public void bewaar(Account account) {
        accountDAO.bewaar(account);
    }

    public void ververs(Account account) {
        accountDAO.ververs(account);

    }

    public Account vindAccountOpGebruikersnaam(String gebruikersnaam) {
        return accountDAO.vindAccountOpGebruikersnaam(gebruikersnaam);
    }

    public List<Account> geefAlleAccounts() {
        return accountDAO.geefAlleAccounts();
    }
}
