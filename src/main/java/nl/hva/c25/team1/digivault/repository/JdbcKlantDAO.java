package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Autowired
    public JdbcKlantDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     *
     * @param klant die opgeslagen moet worden
     */


    @Override
    public void bewaar(Klant klant) {
        String sql = "INSERT INTO Klant values(?,?,?);";
        jdbcTemplate.update(sql, klant.getKlantId(),klant.getBsn(),klant.getGeboortedatum());
    }

    /**
     *
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
     * @return List<Klant>
     */
    @Override
    public List<Klant> vindAlleKlanten() {
        String sql = "SELECT * FROM Klant;";
        return jdbcTemplate.query(sql, new KlantRowMapper());
    }

    /**
     *
     * @param klant
     */
    @Override
    public void update(Klant klant) {
        String sql = "UPDATE Klant SET klantId = ?, bsn = ?, geboortedatum = ? WHERE klantId = ?;";
        jdbcTemplate.update(sql, jdbcTemplate.update(sql, klant.getKlantId(),klant.getBsn(),klant.getGeboortedatum()));
    }

    private class KlantRowMapper implements RowMapper<Klant> {
        @Override
        public Klant mapRow(ResultSet resultSet, int RowNumber) throws SQLException {
            return new Klant(resultSet.getInt("klantId"), resultSet.getString("bsn"),
                    LocalDate.parse(resultSet.getString("geboortedatum")));
        }
    }
}
