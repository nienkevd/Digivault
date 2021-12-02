package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.model.Naam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class JdbcNaamDAO implements NaamDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcNaamDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void bewaar(Naam naam) {
        String sql = "INSERT INTO Naam VALUES(?,?,?,?);";
        jdbcTemplate.update(sql, naam.getNaamId(),naam.getVoornaam(),naam.getTussenvoegsel(),
                naam.getAchternaam());
    }

    //    public void saveWithKey(Naam naam)

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

    @Override
    public List<Naam> vindAlleNamen() {
        String sql = "SELECT * FROM Naam;";
        return jdbcTemplate.query(sql, new JdbcNaamDAO.NaamRowMapper());
    }

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
