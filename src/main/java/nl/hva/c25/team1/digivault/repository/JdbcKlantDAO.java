package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Java Database Connectivity voor DB-tabel Klant
 *
 * @author Anneke, studentnummer 500889251
 * @since 3-12-2021
 *
 * Review Anthon 7-12-2021
 */

@Repository
public class JdbcKlantDAO implements KlantDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcKlantDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     *
     * slaat klant op in database en genereert een surrogate key
     * @param klant die opgeslagen moet worden
     * @return int klantID, de automatisch gegenereerde surrogate key
     */
    @Override
    public Klant bewaarKlantMetSK(Klant klant) {
        String sql = "INSERT INTO klant (bsn, geboortedatum, naamId, adresId, rekeningId, accountId) VALUES (?,?,?,?,?,?);";
        KeyHolder keyholder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, klant.getBsn());
                ps.setDate(2, java.sql.Date.valueOf(klant.getGeboortedatum()));
                ps.setInt(3, klant.getNaam().getNaamId());
                ps.setInt(4, klant.getAdres().getAdresId());
                ps.setInt(5, klant.getRekening().getRekeningId());
                ps.setInt(6, klant.getAccount().getAccountId());
                return ps;
            }
        } , keyholder);
        klant.setKlantId(keyholder.getKey().intValue());
        return klant;
    }

    /**
     *
     * vindt een klant in database adhv klantID
     * @param klantId van klant die je wilt vinden
     * @return Klant
     */
    public Klant vindKlantOpKlantId(int klantId) {
        String sql = "SELECT * FROM klant WHERE klantId = ? ;";
        Klant klant;
        try {
            klant = jdbcTemplate.queryForObject(sql, new KlantRowMapper(), klantId);
        } catch (EmptyResultDataAccessException noResult) {
            klant = null;
        }
        return klant;
    }

    /**
     *
     * @return List<Klant> geeft lijst van alle klanten uit DB terug
     */
    @Override
    public List<Klant> vindAlleKlanten() {
        String sql = "SELECT * FROM klant;";
        return jdbcTemplate.query(sql, new KlantRowMapper());
    }

    /**
     *
     * update gegevens van klant
     * @param klant van klant die je wilt updaten
     */
    @Override
    public void update(Klant klant) {
        String sql = "UPDATE klant SET bsn = ?, geboortedatum = ? WHERE klantId = ?;";
        jdbcTemplate.update(sql, klant.getBsn(),klant.getGeboortedatum(), klant.getKlantId());
    }

    private class KlantRowMapper implements RowMapper<Klant> {
        @Override
        public Klant mapRow(ResultSet resultSet, int RowNumber) throws SQLException {
            return new Klant(resultSet.getInt("klantId"), resultSet.getString("bsn"),
                    LocalDate.parse(resultSet.getString("geboortedatum")));
        }
    }
}
