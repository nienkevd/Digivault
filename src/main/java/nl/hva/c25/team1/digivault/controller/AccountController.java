package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// review door Erwin, 6 december

/**
 * Controller van de klasse Account
 *
 * @author Sezi, studentnummer 500889525
 * @version 8-12-2021
 */
@RestController
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/accounts")
    public Account bewaarAccount(@RequestBody Account account) {return accountService.bewaarAccountMetSK(account);}

    @PostMapping("/accounts/{accountId}")
    public void updateAccount(@RequestBody Account account) {
        accountService.updateAccount(account);
    }

    @GetMapping("/accounts/{accountId}")
    public Account geefAccountOpAccountId(@PathVariable int accountId) {
        return accountService.vindAccountOpAccountId(accountId);
    }

    @GetMapping("/accounts")
    public List<Account> geefAccountsHandler() {
        return accountService.geefAlleAccounts();
    }
}
