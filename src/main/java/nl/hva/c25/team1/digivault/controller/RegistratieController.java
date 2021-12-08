package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.*;
import nl.hva.c25.team1.digivault.service.AssetService;
import nl.hva.c25.team1.digivault.service.RegistratieService;
import nl.hva.c25.team1.digivault.transfer.RegisterDto;
import nl.hva.c25.team1.digivault.transfer.TestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Erwin, studentnummer 500889293
 */
@RestController
public class RegistratieController {

    private RegistratieService registratieService;

    public RegistratieController(RegistratieService registratieService) {
        super();
        this.registratieService=registratieService;
    }

    @PostMapping("/registratie")
    public ResponseEntity<Klant> registratieHandler(@RequestBody RegisterDto registerDto) {
        System.out.println("Hoi!!!");
        Klant klant = new Klant(registerDto);
        registratieService.registratie(klant); //of registratieService.registratie(registerDto)
        return ResponseEntity.ok().body(klant);
    }


}
