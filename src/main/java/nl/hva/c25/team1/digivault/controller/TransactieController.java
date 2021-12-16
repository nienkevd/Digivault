package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.model.Transactie;
import nl.hva.c25.team1.digivault.model.TransactiePartij;
import nl.hva.c25.team1.digivault.service.KlantService;
import nl.hva.c25.team1.digivault.service.TransactieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Author Nienke
 * Version 14-12-2021
 */

public class TransactieController {

    private TransactieService transactieService;

    public TransactieController(TransactieService transactieService) {
        this.transactieService = transactieService;
    }

    @PostMapping("/transactie")
    public Transactie bewaarTransactie(@RequestBody Transactie transactie) {
        transactieService.bewaarTransactie(transactie);
        return transactie;
    }

    @GetMapping("/transactie/{transactieId}")
    public Transactie vindTrasactieopTransactieIdHandler(@PathVariable int transactieId) {
        return transactieService.vindTrasactieopTransactieId(transactieId);
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
