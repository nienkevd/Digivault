package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.*;

import java.util.List;

/**
 * @author Anthon van Dijk (studentnummer 500889247)
 */
public interface PortefeuilleItemDAO {

    PortefeuilleItem bewaarPortefeuilleItemMetKey(PortefeuilleItem portefeuilleItem);

    void updatePortefeuilleItem(PortefeuilleItem portefeuilleItem);

    List<PortefeuilleItem> genereerPortefeuilleVanKlantMetId(int klantId);

    PortefeuilleItem vindItemMetId(int itemId);

    int vindKlantIdVanPortefeuilleitem(PortefeuilleItem portefeuilleItem);

}
