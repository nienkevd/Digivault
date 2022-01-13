package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.authentication.TokenService;
import nl.hva.c25.team1.digivault.model.FinancieelOverzicht;
import nl.hva.c25.team1.digivault.service.AccountService;
import nl.hva.c25.team1.digivault.service.FinancieelOverzichtService;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Controller van de klasse FinancieelOverzicht
 *
 * @author Erwin en Nienke
 * @since 9-12-2021
 */

@RestController
public class FinancieelOverzichtController {

    private FinancieelOverzichtService financieelOverzichtService;
    private TokenService tokenService;
    private AccountService accountService;

    /**
     * Constructor van de FinancieelOverzichtController
     * @param financieelOverzichtService FinancieelOverzichtService
     */
    public FinancieelOverzichtController(FinancieelOverzichtService financieelOverzichtService,
                                         TokenService tokenService, AccountService accountService) {
        super();
        this.financieelOverzichtService = financieelOverzichtService;
        this.tokenService = tokenService;
        this.accountService = accountService;
    }

    /**
     * Genereren van een financieel overzicht (rekening/portefeuille) op klantId
     * @param klantId id waarop gezocht wordt
     * @return een FinancieelOverzicht
     */
    @CrossOrigin
    @GetMapping("/financieeloverzicht/{klantId}")
    public ResponseEntity<FinancieelOverzicht> vindFinancieelOverzicht(@PathVariable int klantId,
                                                                       @RequestHeader("Authorization") String token) {
//        boolean authorized = tokenService.getEmailadresToken(token).equals(accountService.vindAccountOpKlantId(klantId).
//                getEmailadres());
        if (/*tokenService.valideerJWT(token) && authorized*/true) {
            return ResponseEntity.ok(financieelOverzichtService.genereerFinancieelOverzicht(klantId));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
