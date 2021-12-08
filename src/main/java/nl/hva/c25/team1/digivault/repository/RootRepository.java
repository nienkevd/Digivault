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
//    RegistratieController registratieController;

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


//    public Klant slaKlantOp(){
//        Klant klant = registratieController.regi
//        naamDAO.bewaarNaamMetSK(klant.getNaam());
//        adresDAO.bewaarAdresMetSK(klant.getAdres());
//        //accountdao
//        klant.getRekening().setIBAN("");
//        rekeningDAO.bewaarRekeningMetSK(klant.getRekening());
//        for(PortefeuilleItem item : klant.getPortefeuille()){
//            portefeuilleItemDAO.bewaarPortefeuilleItem(item);
//        }
//        klantDAO.bewaarKlantMetSK(klant);
//    }




}
