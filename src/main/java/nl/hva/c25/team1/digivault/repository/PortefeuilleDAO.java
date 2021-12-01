package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Portefeuille;

public interface PortefeuilleDAO {

    int slaLegePortefeuilleOpMetSleutel();

    Portefeuille vindPortefeuilleOpId(int id);

    void updatePortefeuille(Portefeuille portefeuille);

}
