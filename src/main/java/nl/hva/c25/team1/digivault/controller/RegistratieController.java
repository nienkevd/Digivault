package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.*;
import nl.hva.c25.team1.digivault.service.AssetService;
import nl.hva.c25.team1.digivault.service.RegistratieService;
import nl.hva.c25.team1.digivault.transfer.RegisterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Erwin, studentnummer 500889293
 */

public class RegistratieController {

    private RegistratieService registratieService;

    public RegistratieController(RegistratieService registratieService) {
        super();
    }

    @PostMapping("/registratie")
    public ResponseEntity<Klant> registratieHandler(@RequestBody RegisterDto registerDto) {
        Klant klant = new Klant(registerDto);
        registratieService.registratie(klant);
        return ResponseEntity.ok().body(klant);
    }
}
