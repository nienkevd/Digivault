package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Portefeuille;

public interface PortefeuilleDAO {

    public void savePortefeuille(Portefeuille portefeuille);

    public Portefeuille getPortefeuilleById(int id);

    public void updatePortefeuille(Portefeuille portefeuille);

}
