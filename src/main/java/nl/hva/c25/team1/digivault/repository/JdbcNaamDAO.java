package nl.hva.c25.team1.digivault.repository;


import nl.hva.c25.team1.digivault.model.Naam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.List;

public class JdbcNaamDAO implements NaamDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcNaamDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    public void bewaar(Naam naam) {
//        String sql = "INSERT INTO Naam VALUES(?,?,?,?);";
//        jdbcTemplate.update(sql, naam.getNaamId(),naam.getVoornaam(),naam.getTussenvoegsel(),
//                naam.getAchternaam());
//    }

    /**
     *
     * slaat naam op in database en genereert een surrogate key
     * @param naam die opgeslagen moet worden
     * @return int naamID, de automatisch gegenereerde surrogate key
     */
    @Override
    public int bewaarNaamMetSK(Naam naam) {
        String sql = "INSERT INTO Naam (voornaam,tussenvoegsel,achternaam) VALUES (?,?,?);";
        KeyHolder keyholder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, "Jan");
                ps.setString(2,"de");
                ps.setString(3, "Wit");
                return ps;
            }
        } , keyholder);
        naam.setNaamId(keyholder.getKey().intValue());
        return naam.getNaamId();
    }

    /**
     *
     * vindt een klant in database adhv klantID
     * @param naamId
     * @return Naam
     */
    @Override
    public Naam vindNaamOpNaamId(int naamId) {
        String sql = "SELECT * FROM Naam WHERE naamId = ?;";
        Naam naam;
        try {
            naam = jdbcTemplate.queryForObject(sql, new JdbcNaamDAO.NaamRowMapper(), naamId);
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
        String sql = "SELECT * FROM Naam;";
        return jdbcTemplate.query(sql, new JdbcNaamDAO.NaamRowMapper());
    }

    /**
     *
     * update gegevens van naam
     * @param naam
     */
    @Override
    public void update(Naam naam) {
        String sql = "UPDATE Naam SET naamId = ?, voornaam = ?, tussenvoegsel = ?, achternaam = ?;";
        jdbcTemplate.update(sql, naam.getNaamId(),naam.getVoornaam(),naam.getTussenvoegsel(),
                naam.getAchternaam());
    }

    private class NaamRowMapper implements RowMapper<Naam> {
        @Override
        public Naam mapRow(ResultSet resultSet, int RowNumber) throws SQLException {
            return new Naam(resultSet.getInt("naamId"), resultSet.getString("voornaam"),
                    resultSet.getString("tussenvoegsel"), resultSet.getString("achternaam"));
        }
    }
}
