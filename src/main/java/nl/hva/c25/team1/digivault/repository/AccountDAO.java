package nl.hva.c25.team1.digivault.repository;


import nl.hva.c25.team1.digivault.model.Account;

import java.util.List;

public interface AccountDAO {

    public void bewaar(Account account);

    public void ververs(Account account);

    public Account vindAccountOpGebruikersnaam(String gebruikersnaam);

    public List<Account> geefAlleAccounts();
}
