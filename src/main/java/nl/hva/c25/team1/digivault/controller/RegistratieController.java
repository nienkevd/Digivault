package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.*;
import nl.hva.c25.team1.digivault.service.RegistratieService;
import nl.hva.c25.team1.digivault.transfer.RegisterDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller voor de registratie van een Klant
 *
 * @author Erwin, studentnummer 500889293
 * @since 8-12-2021
 */

@Validated
@RestController
public class RegistratieController {

    private RegistratieService registratieService;

    /**
     * Constructor van de RegistratieController
     * @param registratieService RegistratieService
     */
    public RegistratieController(RegistratieService registratieService) {
        super();
        this.registratieService=registratieService;
    }

    /**
     * POST-en van de registratie van een Klant
     * @param registerDto RegisterDto
     * @return String met info over het slagen van de registratie
     */
    @PostMapping("/registratie")
    public String registratieHandler(@Valid @RequestBody RegisterDto registerDto) {
        Klant klant = new Klant(registerDto);
        registratieService.registratie(klant);
        return String.format("Registratie geslaagd!\nNaam: %s\nE-mailadres: %s\nIBAN: %s", klant.getNaam(),
                klant.getAccount().getEmailadres(), klant.getRekening().getIBAN());
    }
}
