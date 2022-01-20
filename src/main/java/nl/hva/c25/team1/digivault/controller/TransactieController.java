package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.authentication.TokenService;
import nl.hva.c25.team1.digivault.model.*;
import nl.hva.c25.team1.digivault.service.*;
import nl.hva.c25.team1.digivault.transfer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Deze controller handelt requests af met betrekking tot transacties.
 *
 * @author Nienke
 * @author Anthon
 *
 * bugfix door Anneke en Anthon
 */
@RestController
@RequestMapping("/transactie/")
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

    /*
     * Deze request-handler verwerkt een transactie. Eerst worden authenticatie en autorisatie gecheckt; vervolgens
     * wordt de DTO doorgezet voor verwerking.
     */
    @PostMapping("{klantId}")
    public String transactieHandler(
            @PathVariable int klantId, @RequestHeader("Authorization") String token,
            @RequestBody TransactieDTO transactieDTO) {
        boolean authorized = tokenService.getEmailadresToken(token).equals(accountService.vindAccountOpKlantId(klantId).
                getEmailadres());
        if (tokenService.valideerJWT(token) && authorized)
            return probeerTransactieOmTeZettenEnUitTeVoeren(transactieDTO);
        return "not authorized";
    }

    /*
     * Deze methode probeert de DTO om te zetten naar een transactie-object. Vervolgens wordt deze transactie
     * doorgegeven aan de service-laag.
     */
    String probeerTransactieOmTeZettenEnUitTeVoeren(TransactieDTO transactieDTO) {
        TransactieMapper transactieMapper = new TransactieMapper();
        Transactie transactie;
        try {
            transactie = transactieMapper.toObject(transactieDTO);
        }
        catch(IllegalArgumentException illegalArgumentException) {
            return illegalArgumentException.getMessage();
        }
        if (transactie != null) {
            transactie = transactieService.voerTransactieUit(transactie);
        }
        if (transactie == null) {
            return "transaction failed";
        }
        return "transaction executed";
    }
}
