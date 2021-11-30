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
    public void createKlant(@RequestBody Klant klant) {
        klantService.saveKlant(klant);
    }

    @GetMapping("/klanten/{klantId}")
    public Klant getKlantByIdHandler(@PathVariable String gebruikersnaam) {
        return klantService.getKlantByUsername(gebruikersnaam);
    }

    @GetMapping("/klanten")
    public List<Klant> getKlantenHandler() {
        return klantService.getAlleKlanten();
    }



}
