package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.service.KlantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KlantController {

    private KlantService klantService;

    public KlantController(KlantService klantService) {
        this.klantService = klantService;
    }

    @PostMapping("/klanten")
    public void bewaarKlant(@RequestBody Klant klant) {
        klantService.bewaarKlant(klant);
    }

    @GetMapping("/klanten/{klantId}")
    public Klant vindKlantopGebruikersnaamHandler(@PathVariable String gebruikersnaam) {
        return klantService.vindKlantOpGebruikersnaam(gebruikersnaam);
    }

    @GetMapping("/klanten")
    public List<Klant> vindKlantenHandler() {
        return klantService.vindAlleKlanten();
    }



}
