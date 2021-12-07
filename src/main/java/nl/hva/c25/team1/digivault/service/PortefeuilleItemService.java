// Created by antho
// Creation date 7-12-2021

package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.repository.PortefeuilleItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Anthon van Dijk (studentnummer 500889247)
 */
@Service
public class PortefeuilleItemService {

    private PortefeuilleItemDAO portefeuilleItemDAO;

    @Autowired
    public PortefeuilleItemService(PortefeuilleItemDAO portefeuilleItemDAO) {
        this.portefeuilleItemDAO = portefeuilleItemDAO;
    }

}