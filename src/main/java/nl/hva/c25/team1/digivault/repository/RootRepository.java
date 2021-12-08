package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.model.PortefeuilleItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RootRepository {

    JdbcTemplate jdbcTemplate;
    KlantDAO klantDAO;
    RekeningDAO rekeningDAO;
    AccountDAO accountDAO;
    PortefeuilleItemDAO portefeuilleItemDAO;
    NaamDAO naamDAO;
    AdresDAO adresDAO;

    @Autowired
    public RootRepository(JdbcTemplate jdbcTemplate, KlantDAO klantDAO, RekeningDAO rekeningDAO, AccountDAO accountDAO,
                          PortefeuilleItemDAO portefeuilleItemDAO, NaamDAO naamDAO, AdresDAO adresDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.klantDAO = klantDAO;
        this.rekeningDAO = rekeningDAO;
        this.accountDAO = accountDAO;
        this.portefeuilleItemDAO = portefeuilleItemDAO;
        this.naamDAO = naamDAO;
        this.adresDAO = adresDAO;
    }


    /**
     *
     * Deze methode wordt aangeroepen in registratieservice registratie(Klant klant)
     * @param klant klant zonder id's
     * @return volledig geregistreerd klant object
     */
    public Klant slaKlantOp(Klant klant){
        naamDAO.bewaarNaamMetSK(klant.getNaam());
        adresDAO.bewaarAdresMetSK(klant.getAdres());
        klant.getAccount().setKlant(klant);

        rekeningDAO.bewaarRekeningMetSK(klant.getRekening());
        for(PortefeuilleItem item : klant.getPortefeuille()){
            portefeuilleItemDAO.bewaarPortefeuilleItemMetKey(item);
        }
        return klantDAO.bewaarKlantMetSK(klant);
    }




}
