package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Klant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class JdbcKlantDAO implements KlantDAO {

    JdbcTemplate jdbcTemplate;

    public JdbcKlantDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;
    private LocalDate geboortedatum;
    private String bsn;
    private String straat;
    private int huisnummer;
    private String toevoeging;
    private String postcode;
    private String woonplaats;
    private String emailadres;


    @Override
    public void save(Klant klant) {
        String sql = "INSERT INTO Klant values(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        jdbcTemplate.update(sql, klant.getVoornaam(),klant.getTussenvoegsel(),klant.getAchternaam(),
                klant.getGeboortedatum(),klant.getBsn(),klant.getStraat(),klant.getHuisnummer(),
                klant.getToevoeging(),klant.getPostcode(),klant.getWoonplaats(),klant.getEmailadres());
    }

    @Override
    public Klant findKlantById(int klantnummer) {
        return null;
    }

    @Override
    public List<Klant> findAlleKlanten() {
        String sql = "SELECT * FROM Klant;";
        return jdbcTemplate.query(sql, new KlantRowMapper());
    }

    private class KlantRowMapper implements RowMapper<Klant> {
        @Override
        public Klant mapRow(ResultSet resultSet, int RowNumber) throws SQLException {
            return new Klant(resultSet.getString("voornaam"), resultSet.getString("tussenvoegsel"),
                    resultSet.getString("achternaam"), LocalDate.parse(resultSet.getString("geboortedatum")),
                    resultSet.getString("bsn"), resultSet.getString("straat"),
                    resultSet.getInt("huisnummer"), resultSet.getString("toevoeging"),
                    resultSet.getString("postcode"), resultSet.getString("woonplaats"),
                    resultSet.getString("emailadres"));
        }
    }
}
