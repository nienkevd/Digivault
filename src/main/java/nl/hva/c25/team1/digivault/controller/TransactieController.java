package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.authentication.TokenService;
import nl.hva.c25.team1.digivault.model.*;
import nl.hva.c25.team1.digivault.service.AccountService;
import nl.hva.c25.team1.digivault.service.AssetService;
import nl.hva.c25.team1.digivault.service.TransactieService;
import nl.hva.c25.team1.digivault.transfer.TransactieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Author Nienke
 * Version 14-12-2021
 */

@RestController
public class TransactieController {

    private TransactieService transactieService;
    private TokenService tokenService;
    private AccountService accountService;

    @Autowired
    public TransactieController(TransactieService transactieService, TokenService tokenService,
                                AccountService accountService) {
        this.transactieService = transactieService;
        this.tokenService = tokenService;
        this.accountService = accountService;
    }

    @PostMapping("/transactie/{klantId}")
    public String transactieHandler(@PathVariable int klantId,
                                                        @RequestHeader("Authorization") String token,
                                                        @RequestBody TransactieDTO transactieDTO) {
//        boolean authorized = tokenService.getEmailadresToken(token).equals(accountService.vindAccountOpKlantId(klantId).
//                getEmailadres());
        if (true) { //tokenService.valideerJWT(token) && authorized
            Transactie transactie = transactieService.voerTransactieUit(transactieDTO);
            System.out.println(transactie);
            if (transactie == null) {
                return "failed";
            } else {
                return "geslaagd";
            }
        } else {
            return "not authorized";
        }
    }

    @GetMapping("/transactie/{transactieId}")
    public Transactie vindTransactieopTransactieIdHandler(@PathVariable int transactieId) {
        System.out.println("controller");
        return transactieService.vindTransactieOpTransactieId(transactieId);
    }

    @GetMapping("/transactie/{verkoper}")
    public List<Transactie> vindAlleTransactiesOpVerkoperHandler(@PathVariable TransactiePartij verkoper){
        return transactieService.vindAlleTransactiesOpVerkoper(verkoper);
    }
    @GetMapping("/transactie/{koper}")
    public List<Transactie> vindAlleTransactiesOpKoperHandler(@PathVariable TransactiePartij koper){
        return transactieService.vindAlleTransactiesOpKoper(koper);
    }
}
