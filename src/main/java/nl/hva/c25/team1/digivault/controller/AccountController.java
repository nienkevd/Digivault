package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

// review door Erwin, 1 december

/**
 * @author Sezi, studentnummer 500889525
 * @version 1-12-2021
 */

public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/accounts")
    public void bewaarAccount(@RequestBody Account account) {
        accountService.bewaar(account);
    }

    @PostMapping("/accounts")
    public void verversAccount(@RequestBody Account account) {
        accountService.ververs(account);
    }

    @GetMapping("/accounts/{accountId}")
    public Account geefAccountOpIdHandler(@PathVariable String gebruikersnaam) {
        return accountService.vindAccountOpGebruikersnaam(gebruikersnaam);
    }

    @GetMapping("/accounts")
    public List<Account> geefAccountsHandler() {
        return accountService.geefAlleAccounts();
    }
}
