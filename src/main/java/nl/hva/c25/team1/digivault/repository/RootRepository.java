package nl.hva.c25.team1.digivault.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RootRepository {

    JdbcTemplate jdbcTemplate;
    KlantDAO klantDAO;
    RekeningDAO rekeningDAO;
    AccountDAO accountDAO;
    PortefeuilleDAO portefeuilleDAO;

    @Autowired
    public RootRepository(JdbcTemplate jdbcTemplate, KlantDAO klantDAO, RekeningDAO rekeningDAO, AccountDAO accountDAO,
                          PortefeuilleDAO portefeuilleDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.klantDAO = klantDAO;
        this.rekeningDAO = rekeningDAO;
        this.accountDAO = accountDAO;
        this.portefeuilleDAO = portefeuilleDAO;
    }

    // TODO: bij registreer klant moet de methode public int slaLegePortefeuilleOpMetSleutel() worden gebruikt

    //    public void registreerKlant(Klant klant) {
//        String sql = "INSERT INTO Klant values(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
//        jdbcTemplate.update(sql, klant.getBsn(), klant.getVoornaam(),klant.getTussenvoegsel(),klant.getAchternaam(),
//                klant.getGeboortedatum(),klant.getStraat(),klant.getHuisnummer(),
//                klant.getToevoeging(),klant.getPostcode(),klant.getWoonplaats(),klant.getEmailadres(),
//                klant.getAccount().getGebruikersnaam(),klant.getRekening().getIBAN(),klant.getPortefeuille().getPortefeuilleId());
//    }
}
