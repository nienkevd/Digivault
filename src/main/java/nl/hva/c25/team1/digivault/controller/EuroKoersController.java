package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.EuroKoers;
import nl.hva.c25.team1.digivault.service.EuroKoersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Erwin, studentnummer 500889293
 * @version 2-12-2021
 */

@RestController
public class EuroKoersController {

    private EuroKoersService euroKoersService;

    /**
     * Constructor van de EuroKoersController
     * @param euroKoersService EuroKoersService
     */
    @Autowired
    public EuroKoersController(EuroKoersService euroKoersService) {
        super();
        this.euroKoersService = euroKoersService;
    }

    /**
     * Slaat EuroKoers op
     * @param euroKoers de te bewaren EuroKoers
     */
    @PostMapping("/eurokoersen")
    public void bewaar(@RequestBody EuroKoers euroKoers) {
        euroKoersService.bewaarEuroKoers(euroKoers);
    }

    /**
     * Vindt EuroKoers op assetId
     * @param euroKoersId waarop EuroKoers gezocht wordt
     * @return doorgeefluik
     */
    @PostMapping("/eurokoersen/{eurokoers}")
    public EuroKoers vindEuroKoersOpId(@PathVariable int euroKoersId) {
        return euroKoersService.vindEuroKoersOpId(euroKoersId);
    }

    /**
     * Geeft een lijst van alle EuroKoersen terug
     * @return doorgeefluik
     */
    @PostMapping("/eurokoersen")
    public List<EuroKoers> geefAlleEuroKoersen() {
        return euroKoersService.geefAlleEuroKoersen();
    }

    /**
     * Ververst een bepaalde EuroKoers
     * @param euroKoers welke ververst moet worden
     * @return doorgeefluik
     */
    @PostMapping("/eurokoersen/{")
    public String ververs(@RequestBody EuroKoers euroKoers) {
        return euroKoersService.verversEuroKoers(euroKoers);
    }
}
