package nl.hva.c25.team1.digivault.repository;


import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.model.Naam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

/**
 * Java Database Connectivity voor DB-tabel Naam
 *
 * @author Anneke, studentnummer 500889251
 * @since 3-12-2021
 */

@Repository
public class JdbcNaamDAO implements NaamDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcNaamDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     *
     * slaat naam op in database en genereert een surrogate key
     * @param naam die opgeslagen moet worden
     * @return int naamID, de automatisch gegenereerde surrogate key
     */
//    @Override
//    public Naam bewaarNaamMetSK(Naam naam) {
//        String sql = "INSERT INTO naam (voornaam,tussenvoegsel,achternaam) VALUES (?,?,?);";
//        KeyHolder keyholder = new GeneratedKeyHolder();
//        jdbcTemplate.update(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//                ps.setString(1, naam.getVoornaam());
//                ps.setString(2, naam.getTussenvoegsel());
//                ps.setString(3, naam.getAchternaam());
//                return ps;
//            }
//        } , keyholder);
//        naam.setNaamId(keyholder.getKey().intValue());
//        return naam;
//    }

    /**
     *
     * vindt een klant in database adhv klantID
     * @param klantId van klant
     * @return Naam
     */
    @Override
    public Naam vindNaamOpKlantId(int klantId) {
        String sql = "SELECT * FROM transactiepartij WHERE tpId = ?;";
        Naam naam;
        try {
            naam = jdbcTemplate.queryForObject(sql, new JdbcNaamDAO.NaamRowMapper(), klantId);
        } catch (EmptyResultDataAccessException noResult) {
            naam = null;
        }
        return naam;
    }

    /**
     *
     * geeft lijst van alle klanten uit DB terug
     * @return List<Naam>
     */
    @Override
    public List<Naam> vindAlleNamen() {
        String sql = "SELECT * FROM transactiepartij;";
        return jdbcTemplate.query(sql, new JdbcNaamDAO.NaamRowMapper());
    }

    /**
     *
     * update gegevens van naam
     * @param  klant die geupdate moet worden
     */
    @Override
    public void update(Klant klant) {
        String sql = "UPDATE transactiepartij SET  voornaam = ?, tussenvoegsel = ?, achternaam = ?;";
        jdbcTemplate.update(sql, klant.getNaam().getVoornaam(), klant.getNaam().getTussenvoegsel(),
                klant.getNaam().getAchternaam());
    }

    private class NaamRowMapper implements RowMapper<Naam> {
        @Override
        public Naam mapRow(ResultSet resultSet, int RowNumber) throws SQLException {
            return new Naam(resultSet.getString("voornaam"),
                    resultSet.getString("tussenvoegsel"), resultSet.getString("achternaam"));
        }
    }
}
