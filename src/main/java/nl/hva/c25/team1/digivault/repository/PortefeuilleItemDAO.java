package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.*;

import java.util.List;
import java.util.Map;

/**
 * @author Anthon van Dijk (studentnummer 500889247)
 */
public interface PortefeuilleItemDAO {

    int bewaarPortefeuilleItem(PortefeuilleItem portefeuilleItem);

    void updatePortefeuilleItem(PortefeuilleItem portefeuilleItem);

    List<PortefeuilleItem> vindAlleItemsVanKlantMetId(int klantId);

    PortefeuilleItem vindItemMetId(int itemId);

}
