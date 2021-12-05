package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.EuroKoers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Java Database Connectivity voor DB-tabel EuroKoers
 *
 * @author Erwin, studentnummer 500889293
 * @version 2-12-2021
 */

@Repository
public class JdbcEuroKoersDAO implements EuroKoersDAO {

    JdbcTemplate jdbcTemplate;

    /**
     * Constructor van JdbcEuroKoersDAO
     * @param jdbcTemplate JDBC Template
     */
    @Autowired
    public JdbcEuroKoersDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Slaat EuroKoers op in Database
     * @param euroKoers de te bewaren EuroKoers
     */
    @Override
    public void bewaar(EuroKoers euroKoers) {
        String sql = "INSERT INTO EuroKoers VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, euroKoers.getEuroKoersId(), euroKoers.getDatum(), euroKoers.getKoers(),
                euroKoers.getAssetId());
    }

    /**
     * Vindt EuroKoers op euroKoersId in Database
     * @param euroKoersId waarop EuroKoers gezocht wordt
     * @return de bijbehorende Asset
     */
    @Override
    public EuroKoers vindEuroKoersOpId(int euroKoersId) {
        String sql = "SELECT * FROM EuroKoers WHERE euroKoersId = ?";
        return jdbcTemplate.queryForObject(sql, new EuroKoersRowMapper(), euroKoersId);
    }

    /**
     * Geeft een lijst van alle EuroKoersen terug
     * @return Lijst met alle EuroKoersen
     */
    @Override
    public List<EuroKoers> geefAlleEuroKoersen() {
        String sql = "SELECT * FROM EuroKoers";
        return jdbcTemplate.query(sql, new EuroKoersRowMapper());
    }

    /**
     * Ververst een bepaalde EuroKoers
     * @param euroKoers welke ververst moet worden
     */
    @Override
    public void ververs(EuroKoers euroKoers) {
        String sql = "UPDATE EuroKoers SET euroKoersId = ?, datum = ?, koers = ?, assetId = ?";
        jdbcTemplate.update(sql, euroKoers.getEuroKoersId(), euroKoers.getDatum(), euroKoers.getKoers(),
                euroKoers.getAssetId());
    }

    private class EuroKoersRowMapper implements RowMapper<EuroKoers> {
        @Override
        public EuroKoers mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new EuroKoers(resultSet.getDate("datum").toLocalDate(),
                    resultSet.getDouble("koers"));
        }
    }
}
