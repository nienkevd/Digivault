package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.authentication.TokenService;
import nl.hva.c25.team1.digivault.model.*;
import nl.hva.c25.team1.digivault.service.*;
import nl.hva.c25.team1.digivault.transfer.TransactieDTO;
import nl.hva.c25.team1.digivault.transfer.TransactieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author Nienke
 * @author Anthon
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

    @PostMapping("{klantId}")
    public String transactieHandler(
            @PathVariable int klantId, @RequestHeader("Authorization") String token,
            @RequestBody TransactieDTO transactieDTO) {
        boolean authorized = tokenService.getEmailadresToken(token).equals(accountService.vindAccountOpKlantId(klantId).
                getEmailadres());
        if (tokenService.valideerJWT(token) && authorized) {
            TransactieMapper transactieMapper = new TransactieMapper();
            Transactie transactie = transactieService.voerTransactieUit(transactieMapper.toObject(transactieDTO));
            if (transactie == null) {
                return "transaction failed";
            } else {
                return "transaction executed";
            }
        } else {
            return "not authorized";
        }
    }

//    @GetMapping("/transactie/{transactieId}")
//    public Transactie vindTransactieopTransactieIdHandler(@PathVariable int transactieId) {
//        System.out.println("controller");
//        return transactieService.vindTransactieOpTransactieId(transactieId);
//    }
//
//    @GetMapping("/transactie/{verkoper}")
//    public List<Transactie> vindAlleTransactiesOpVerkoperHandler(@PathVariable TransactiePartij verkoper){
//        return transactieService.vindAlleTransactiesOpVerkoper(verkoper);
//    }
//    @GetMapping("/transactie/{koper}")
//    public List<Transactie> vindAlleTransactiesOpKoperHandler(@PathVariable TransactiePartij koper){
//        return transactieService.vindAlleTransactiesOpKoper(koper);
//    }

}
