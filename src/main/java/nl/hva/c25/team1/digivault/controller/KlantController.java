package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.model.transfer.KlantDto;
import nl.hva.c25.team1.digivault.service.KlantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KlantController {

    private KlantService klantService;

    public KlantController(KlantService klantService) {
        this.klantService = klantService;
    }

    @PostMapping("/klanten")
    public Klant bewaarKlant(@RequestBody KlantDto klantDto) {
        Klant klant = new Klant(klantDto);
        System.out.println("*** " + klantDto.getAchternaam() + " ***");
        klantService.bewaarKlant(klant);
        return klant;
    }

    @GetMapping("/klanten/{gebruikersnaam}")
    public Klant vindKlantopGebruikersnaamHandler(@PathVariable String gebruikersnaam) {
        return klantService.vindKlantOpGebruikersnaam(gebruikersnaam);
    }

    @GetMapping("/klanten")
    public List<Klant> vindKlantenHandler() {
        return klantService.vindAlleKlanten();
    }



}
