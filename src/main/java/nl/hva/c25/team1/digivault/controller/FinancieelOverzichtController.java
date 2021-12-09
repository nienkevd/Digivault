package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.FinancieelOverzicht;
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

    private FinancieelOverzichtService financieelOverzichtService;

    public FinancieelOverzichtController(FinancieelOverzichtService financieelOverzichtService) {
        super();
        this.financieelOverzichtService = financieelOverzichtService;
    }

    @GetMapping("/financieeloverzicht/{klantId}")
    public FinancieelOverzicht vindFinancieelOverzicht(@PathVariable int klantId) {
        return financieelOverzichtService.genereerFinancieelOverzicht(klantId);
    }
}
