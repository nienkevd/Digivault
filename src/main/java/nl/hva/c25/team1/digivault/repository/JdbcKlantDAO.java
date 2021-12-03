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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

/**
 * Java Database Connectivity voor DB-tabel Klant
 *
 * @author Anneke, studentnummer 500889251
 * @version 3-12-2021
 */

@Repository
public class JdbcKlantDAO implements KlantDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcKlantDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    public void bewaar(Klant klant) {
//        String sql = "INSERT INTO Klant values(?,?,?);";
//        jdbcTemplate.update(sql, klant.getKlantId(),klant.getBsn(),klant.getGeboortedatum());
//    }

    /**
     *
     * slaat klant op in database en genereert een surrogate key
     * @param klant die opgeslagen moet worden
     * @return int klantID, de automatisch gegenereerde surrogate key
     */
    @Override
    public int bewaarMetSK(Klant klant) {
        String sql = "INSERT INTO Klant (bsn, geboortedatum) VALUES (?,?);";
        KeyHolder keyholder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, "109876543");
                try {
                    ps.setDate(2, new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("1986-01-26").getTime()));
                } catch (ParseException e) {
                    System.out.println("ParseException");
                }
                return ps;
            }
        } , keyholder);
        return keyholder.getKey().intValue();
    }

    /**
     *
     * vind een klant in database adhv klantID
     * @param klantId
     * @return Klant
     */
    public Klant vindKlantOpKlantId(int klantId) {
        String sql = "SELECT * FROM Klant WHERE klantId = ? ;";
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
     * @return List<Klant> geeft lijst van alle klanten in DB terug
     */
    @Override
    public List<Klant> vindAlleKlanten() {
        String sql = "SELECT * FROM Klant;";
        return jdbcTemplate.query(sql, new KlantRowMapper());
    }

    /**
     *
     * update gegevens van klant
     * @param klant
     */
    @Override
    public void update(Klant klant) {
        String sql = "UPDATE Klant SET bsn = ?, geboortedatum = ? WHERE klantId = ?;";
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
