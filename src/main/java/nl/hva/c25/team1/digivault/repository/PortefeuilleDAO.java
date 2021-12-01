package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Portefeuille;

public interface PortefeuilleDAO {

    void bewaarPortefeuilleMetSleutel(Portefeuille portefeuille);

    Portefeuille vindPortefeuilleOpId(int id);

    void updatePortefeuille(Portefeuille portefeuille);

}
