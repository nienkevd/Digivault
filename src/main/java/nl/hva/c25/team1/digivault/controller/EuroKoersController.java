package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.EuroKoers;
import nl.hva.c25.team1.digivault.service.EuroKoersService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Erwin, studentnummer 500889293
 * @version 2-12-2021
 */

public class EuroKoersController {

    private EuroKoersService euroKoersService;

    /**
     * Constructor van de EuroKoersController
     * @param euroKoersService
     */
    public EuroKoersController(EuroKoersService euroKoersService) {
        super();
        this.euroKoersService = euroKoersService;
    }

    /**
     *
     * @param euroKoers
     */
    @PostMapping("/eurokoersen")
    public void bewaar(@RequestBody EuroKoers euroKoers) {
        euroKoersService.bewaarEuroKoers(euroKoers);
    }

    /**
     *
     * @param euroKoersId
     * @return
     */
    @PostMapping("/eurokoersen/{eurokoers}")
    public EuroKoers vindEuroKoersOpId(@PathVariable int euroKoersId) {
        return euroKoersService.vindEuroKoersOpId(euroKoersId);
    }

    /**
     *
     * @return
     */
    @PostMapping("/eurokoersen")
    public List<EuroKoers> geefAlleEuroKoersen() {
        return euroKoersService.geefAlleEuroKoersen();
    }

    /**
     *
     * @param euroKoers
     * @return
     */
    @PostMapping("/eurokoersen/{")
    public String ververs(@RequestBody EuroKoers euroKoers) {
        return euroKoersService.verversEuroKoers(euroKoers);
    }
}
