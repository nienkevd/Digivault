package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.service.FinancieelOverzichtService;
import nl.hva.c25.team1.digivault.transfer.FinancieelOverzichtDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @author Erwin, studentnummer 500889293
 * @since 9-12-2021
 */

public class FinancieelOverzichtController {

    private FinancieelOverzichtService fincancieelOverzichtService;


    @Autowired
    public FinancieelOverzichtController(FinancieelOverzichtService fincancieelOverzichtService) {
        super();
        this.fincancieelOverzichtService = fincancieelOverzichtService;
    }

    @GetMapping("/financieeloverzicht/{klantId}")
    public ResponseEntity<Klant> overzichtHanlder(@RequestBody FinancieelOverzichtDto financieelOverzichtDto) {
        Klant klant = new Klant(financieelOverzichtDto);
        return ResponseEntity.ok().body(klant);
    }
}
