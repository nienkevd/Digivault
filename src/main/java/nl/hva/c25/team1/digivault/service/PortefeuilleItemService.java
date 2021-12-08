// Created by antho
// Creation date 7-12-2021

package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.PortefeuilleItem;
import nl.hva.c25.team1.digivault.repository.PortefeuilleItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Anthon van Dijk (studentnummer 500889247)
 */
@Service
public class PortefeuilleItemService {

    private PortefeuilleItemDAO portefeuilleItemDAO;

    @Autowired
    public PortefeuilleItemService(PortefeuilleItemDAO portefeuilleItemDAO) {
        super();
        this.portefeuilleItemDAO = portefeuilleItemDAO;
    }

    /**
     * Genereert gehele portefeuille van klant.
     *
     * @param klantId KlantId van de betreffende klant.
     * @return De gehele portefeuille.
     */
    public List<PortefeuilleItem> genereerPortefeuilleVanKlantMetId(int klantId) {
        return portefeuilleItemDAO.genereerPortefeuilleVanKlantMetId(klantId);
    }

    /**
     * Geeft portefeuilleitem met bepaald id.
     *
     * @param itemId Het id van het portefeuilleitem.
     * @return Het portefeuilleitem.
     */
    public PortefeuilleItem vindItemMetId(int itemId) {
        return portefeuilleItemDAO.vindItemMetId(itemId);
    }

}