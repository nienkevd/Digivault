package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.service.FinancieelOverzichtService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Erwin, studentnummer 500889293
 * @since 9-12-2021
 */

@RestController
public class FinancieelOverzichtController {

    private FinancieelOverzichtService fincancieelOverzichtService;

    public FinancieelOverzichtController() {
        super();
        this.fincancieelOverzichtService = fincancieelOverzichtService;
    }

    @GetMapping("/financieeloverzicht/{klantId}")
    public Klant vindFinancieelOverzicht(@PathVariable int klantId) {
        return fincancieelOverzichtService.vindFinancieelOverzicht(klantId);
    }
}
