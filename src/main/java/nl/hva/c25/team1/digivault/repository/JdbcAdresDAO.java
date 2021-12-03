package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Adres;
import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.model.Naam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Java Database Connectivity voor DB-tabel Adres
 *
 * @author Anneke, studentnummer 500889251
 * @version 3-12-2021
 */

public class JdbcAdresDAO implements AdresDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAdresDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     *
     * slaat adres van klant op in database en genereert een surrogate key
     * @param adres welke opgeslagen moet worden
     * @return int adresID, de automatisch gegenereerde surrogate key
     */
    @Override
    public int bewaarMetSK(Adres adres) {
        String sql = "INSERT INTO Adres a JOIN Ziphuisnr j ON a.ziphuisnrId = z.ziphuisnrId " +
                "(postcode, huisnummer, toevoeging, postcode, woonplaats) VALUES (?,?);";
        KeyHolder keyholder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, "109876543");
                try {
                    ps.setDate(2, new java.sql.Date(new SimpleDateFormat("YYYY-MM-DD").parse("1986-01-26").getTime()));
                } catch (ParseException e) {
                }
                return ps;
            }
        } , keyholder);
        return keyholder.getKey().intValue();
    }


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
