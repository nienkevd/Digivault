// Created by antho
// Creation date 1-12-2021

package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.model.Portefeuille;
import nl.hva.c25.team1.digivault.repository.PortefeuilleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PortefeuilleService {

    private PortefeuilleDAO portefeuilleDAO;

    @Autowired
    public PortefeuilleService(PortefeuilleDAO portefeuilleDAO) {
        super();
        this.portefeuilleDAO = portefeuilleDAO;
    }

    public void bewaarPortefeuille(Portefeuille portefeuille) {
        portefeuilleDAO.bewaarPortefeuilleMetSleutel(portefeuille);
    }

    public Portefeuille vindPortefeuilleOpId(int id) {
        return portefeuilleDAO.vindPortefeuilleOpId(id);
    }

    public String updatePortefeuille(Portefeuille portefeuille) {
        if (vindPortefeuilleOpId(portefeuille.getPortefeuilleId()) == null) {
            return "Portefeuille niet gevonden!";
        } else {
            portefeuilleDAO.updatePortefeuille(portefeuille);
            return "Update geslaagd.";
        }
    }

}