// Created by antho
// Creation date 1-12-2021

package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.model.Portefeuille;
import nl.hva.c25.team1.digivault.service.PortefeuilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PortefeuilleController {

    private PortefeuilleService portefeuilleService;

    @Autowired
    public PortefeuilleController(PortefeuilleService portefeuilleService) {
        this.portefeuilleService = portefeuilleService;
    }

    @PostMapping("/portefeuilles")
    public void maakPortefeuilleHandler(@RequestBody Portefeuille portefeuille) {
        portefeuilleService.bewaarPortefeuille(portefeuille);
    }

    @GetMapping("/portefeuilles/{id}")
    public ResponseEntity<Portefeuille> vindPortefeuilleOpIdHandler(@PathVariable int id) {
        Portefeuille portefeuille = portefeuilleService.vindPortefeuilleOpId(id);
        if (portefeuille == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(portefeuille);
        }
    }

    @PutMapping("/portefeuilles")
    public String updatePortefeuilleHandler(@RequestBody Portefeuille portefeuille) {
        return portefeuilleService.updatePortefeuille(portefeuille);
    }

}