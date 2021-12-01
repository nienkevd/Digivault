// Created by antho
// Creation date 1-12-2021

package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.service.PortefeuilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortefeuilleController {

    private PortefeuilleService portefeuilleService;

    @Autowired
    public PortefeuilleController(PortefeuilleService portefeuilleService) {
        this.portefeuilleService = portefeuilleService;
    }



}