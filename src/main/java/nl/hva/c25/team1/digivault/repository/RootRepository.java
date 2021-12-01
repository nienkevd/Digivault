package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Klant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RootRepository {

    JdbcKlantDAO jdbcKlantDAO;
    JdbcRekeningDAO jdbcRekeningDAO;
    JdbcAccountDAO jdbcAccountDAO;
    JdbcPortefeuilleDAO jdbcPortefeuilleDAO;


//    public void registreerKlant(Klant klant) {
//        String sql = "INSERT INTO Klant values(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
//        jdbcTemplate.update(sql, klant.getBsn(), klant.getVoornaam(),klant.getTussenvoegsel(),klant.getAchternaam(),
//                klant.getGeboortedatum(),klant.getStraat(),klant.getHuisnummer(),
//                klant.getToevoeging(),klant.getPostcode(),klant.getWoonplaats(),klant.getEmailadres(),
//                klant.getAccount().getGebruikersnaam(),klant.getRekening().getIBAN(),klant.getPortefeuille().getPortefeuilleId());
//    }
}
