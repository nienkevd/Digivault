package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.service.KlantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller van de klasse Klant
 *
 * @author Anneke, studentnummer 500889251
 * @version 1-12-2021
 */

@RestController
public class KlantController {

    private KlantService klantService;

    public KlantController(KlantService klantService) {
        this.klantService = klantService;
    }

    @PostMapping("/klanten")
    public Klant bewaarKlant(@RequestBody Klant klant) {
        System.out.println("*** " + klant.getNaam().getAchternaam() + " ***");
        klantService.bewaarKlant(klant);
        return klant;
    }

    @GetMapping("/klanten/{klantId}")
    public Klant vindKlantopGebruikersnaamHandler(@PathVariable int klantId) {
        return klantService.vindKlantOpKlantID(klantId);
    }

    @GetMapping("/klanten")
    public List<Klant> vindKlantenHandler() {
        return klantService.vindAlleKlanten();
    }

    @PutMapping("/klanten/{klantId}")
    public String updateKlant(@RequestBody Klant klant) {
        return klantService.updateKlant(klant);
    }


}
