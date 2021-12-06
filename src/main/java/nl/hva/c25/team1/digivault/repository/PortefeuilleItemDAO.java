package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.*;

/**
 * @author Anthon van Dijk (studentnummer 500889247)
 */
public interface PortefeuilleItemDAO {

    int bewaarPortefeuilleItem(PortefeuilleItem portefeuilleItem);

    Portefeuille vindPortefeuilleVanKlant(int klantId);

    void updatePortefeuilleItem(PortefeuilleItem portefeuilleItem);

}
