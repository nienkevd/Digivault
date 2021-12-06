package nl.hva.c25.team1.digivault.controller;


import nl.hva.c25.team1.digivault.model.Rekening;
import nl.hva.c25.team1.digivault.service.RekeningService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

// review door Erwin, 1 december

/**
 * Controller van de klasse Rekening
 *
 * @author Sezi, studentnummer 500889525
 * @version 1-12-2021
 */

public class RekeningController {

    private RekeningService rekeningService;

    public RekeningController(RekeningService rekeningService) {
        this.rekeningService = rekeningService;
    }

    @PostMapping("/rekeningen")
    public void bewaarRekening(@RequestBody Rekening rekening) {

        rekeningService.bewaarRekening(rekening);
    }

    @PostMapping("/rekeningen")
    public void verversRekening(@RequestBody Rekening rekening) {
        rekeningService.updateRekening(rekening);
    }

    @GetMapping("/rekeningen/{IBAN}")
    public Rekening vindRekeningOpIBAN(@PathVariable String IBAN) {
        return rekeningService.vindRekeningOpIBAN(IBAN);
    }

    @GetMapping("/rekeningen")
    public List<Rekening> geefRekeningenHandler() {
        return rekeningService.geefAlleRekeningen();
    }
}
