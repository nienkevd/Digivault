package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.EuroKoers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Erwin, studentnummer 500889293
 * @version 2-12-2021
 *
 * Java Database Connectivity voor DB-tabel EuroKoers
 */

public class JdbcEuroKoersDAO implements EuroKoersDAO {

    JdbcTemplate jdbcTemplate;

    public JdbcEuroKoersDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void bewaar(EuroKoers euroKoers) {
        String sql = "INSERT INTO EuroKoers VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, euroKoers.getEuroKoersId(), euroKoers.getDatum(), euroKoers.getKoers(),
                euroKoers.getAssetId());

    }

    @Override
    public EuroKoers vindEuroKoersOpId(int euroKoersId) {
        String sql = "SELECT * FROM EuroKoers WHERE euroKoersId = ?";
        return jdbcTemplate.queryForObject(sql, new EuroKoersRowMapper(), euroKoersId);
    }

    @Override
    public List<EuroKoers> geefAlleEuroKoersen() {
        String sql = "SELECT * FROM EuroKoers";
        return jdbcTemplate.query(sql, new EuroKoersRowMapper());
    }

    @Override
    public void ververs(EuroKoers euroKoers) {
        String sql = "UPDATE EuroKoers SET euroKoersId = ?, datum = ?, koers = ?, assetId = ?";
        jdbcTemplate.update(sql, euroKoers.getEuroKoersId(), euroKoers.getDatum(), euroKoers.getKoers(),
                euroKoers.getAssetId());
    }

    private class EuroKoersRowMapper implements RowMapper<EuroKoers> {
        @Override
        public EuroKoers mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new EuroKoers(resultSet.getInt("euroKoersId"), resultSet.getDate("datum")
                    .toLocalDate(), resultSet.getDouble("koers"), resultSet.getInt("assetId"));
        }
    }
}
