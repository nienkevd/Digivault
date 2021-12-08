// Created by antho
// Creation date 1-12-2021

package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.PortefeuilleItem;
import nl.hva.c25.team1.digivault.service.PortefeuilleItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PortefeuilleItemController {

    private PortefeuilleItemService portefeuilleItemService;

    @Autowired
    public PortefeuilleItemController(PortefeuilleItemService portefeuilleItemService) {
        this.portefeuilleItemService = portefeuilleItemService;
    }

    @GetMapping("/portefeuilles/{id}")
    public ResponseEntity<PortefeuilleItem> vindPortefeuilleItemOpIdHandler(@PathVariable int id) {
        PortefeuilleItem portefeuilleItem = portefeuilleItemService.vindItemMetId(id);
        if (portefeuilleItem == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(portefeuilleItem);
        }
    }

    // Tijdelijk uit-gecomment door Erwin, updatePortefeuilleItem bestaat nog niet in Service   // TODO check PI-Controller
//    @PutMapping("/portefeuilles")
//    public String updatePortefeuilleItemHandler(@RequestBody PortefeuilleItem portefeuilleItem) {
//        return portefeuilleItemService.updatePortefeuilleItem(portefeuilleItem);
//    }

}