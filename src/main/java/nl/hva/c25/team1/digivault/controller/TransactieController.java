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
    private AssetService assetService;

    @Autowired
    public TransactieController(TransactieService transactieService, TokenService tokenService,
                                AccountService accountService, AssetService assetService) {
        this.transactieService = transactieService;
        this.tokenService = tokenService;
        this.accountService = accountService;
        this.assetService = assetService;
    }

    @PostMapping("/transactie/{klantId}")
    public ResponseEntity<Transactie> transactieHandler(@PathVariable int klantId,
                                                        @RequestHeader("Authorization") String token,
                                                        @RequestBody TransactieDTO transactieDTO) {
        boolean authorized = tokenService.getEmailadresToken(token).equals(accountService.vindAccountOpKlantId(klantId).
                getEmailadres());
        if (tokenService.valideerJWT(token) && authorized) {
            Transactie transactie = zetDtoOm(transactieDTO);
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

    private Transactie zetDtoOm(TransactieDTO transactieDTO) {
        Transactie transactie = new Transactie(transactieDTO);
        int koperId = transactieDTO.getKoperId();
        int verkoperId = transactieDTO.getVerkoperId();
        TransactiePartij koper, verkoper;
        if (koperId < 10) { // bank is koper
            koper = new Bank(koperId);
            verkoper = new Klant(verkoperId);
        } else if (verkoperId < 10) { // bank is verkoper
            koper = new Klant(koperId);
            verkoper = new Bank(verkoperId);
        } else { // transactie tussen 2 klanten
            koper = new Klant(koperId);
            verkoper = new Klant(verkoperId);
        }
        transactie.setKoper(koper);
        transactie.setVerkoper(verkoper);
        transactie.setAsset(new Asset(transactieDTO.getAssetId()));
        return transactie;
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
