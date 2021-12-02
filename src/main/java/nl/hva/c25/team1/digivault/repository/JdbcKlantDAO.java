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

//    public void saveWithKey(Klant klant) {
//        String sql = "Insert into Klant(voorletters, voorvoegsels, achternaam, telefoon) values (?,?,?,?)";
//
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//                ps.setString(1, klant.getVoorletters());
//                ps.setString(2, klant.getVoorvoegsels());
//                ps.setString(3, klant.getAchternaam());
//                ps.setString(4, klant.getTelefoon());
//                return ps;
//            }
//        }, keyHolder);
//        klant.setKlantnummer(keyHolder.getKey().intValue());
//    }

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
        jdbcTemplate.update(sql, klant.getKlantId(),klant.getBsn(),klant.getGeboortedatum());
    }

    private class KlantRowMapper implements RowMapper<Klant> {
        @Override
        public Klant mapRow(ResultSet resultSet, int RowNumber) throws SQLException {
            return new Klant(resultSet.getInt("klantId"), resultSet.getString("bsn"),
                    LocalDate.parse(resultSet.getString("geboortedatum")));
        }
    }
}
