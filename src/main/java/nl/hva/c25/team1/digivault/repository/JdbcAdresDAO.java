package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Adres;
import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.model.Naam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcAdresDAO implements AdresDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAdresDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void bewaarmetkey(Adres adres) {};


    @Override
    public Adres vindAdresOpAdresId(int adresId) {
        String sql = "SELECT (adresId, straat, huisnummer, toevoeging," +
                "postcode, woonplaats) FROM Adres a JOIN Ziphuisnr z WHERE adresId = ?;";
        Adres adres;
        try {
            adres = jdbcTemplate.queryForObject(sql, new AdresRowMapper(), adresId);
        } catch (EmptyResultDataAccessException noResult) {
            adres = null;
        }
        return adres;
    }

    @Override
    public List<Adres> vindAlleAdressen() {
        String sql = "SELECT (adresId, straat, huisnummer, toevoeging, postcode, woonplaats)" +
                "FROM Adres a Join Ziphuisnr z;";
        return jdbcTemplate.query(sql, new JdbcAdresDAO.AdresRowMapper());
    }

    @Override
    public void update(Adres adres) {
        String sql = "UPDATE Adres SET adresId =?, straat = ?, huisnummer = ?, toevoeging = ?," +
                "postcode = ?, woonplaats = ?,";
        jdbcTemplate.update(sql, adres.getAdresId(), adres.getStraat(),adres.getHuisnummer(),
                adres.getToevoeging(),adres.getPostcode(),adres.getWoonplaats());
    }


    private class AdresRowMapper implements RowMapper<Adres> {
        @Override
        public Adres mapRow(ResultSet resultSet, int RowNumber) throws SQLException {
            return new Adres(resultSet.getInt("adresId"), resultSet.getString("straat"),
                    resultSet.getInt("huisnummer"), resultSet.getString("toevoeging"),
                    resultSet.getString("postcode"), resultSet.getString("woonplaats"));
        }
    }
}
