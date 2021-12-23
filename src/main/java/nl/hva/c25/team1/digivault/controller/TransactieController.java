package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.authentication.TokenService;
import nl.hva.c25.team1.digivault.model.Transactie;
import nl.hva.c25.team1.digivault.model.TransactiePartij;
import nl.hva.c25.team1.digivault.service.AccountService;
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
    public ResponseEntity<Transactie> transactieHandler(@PathVariable int klantId,
                                                        @RequestHeader("Authorization") String token,
                                                        @RequestBody TransactieDTO transactieDTO) {
        boolean authorized = tokenService.getEmailadresToken(token).equals(accountService.vindAccountOpKlantId(klantId).
                getEmailadres());
        if (tokenService.valideerJWT(token) && authorized) {
            Transactie transactie = new Transactie(transactieDTO);
            transactieService.voerTransactieUit(transactie);
            if (transactie == null) {
                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
            } else {
                return ResponseEntity.ok(transactie);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
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
