package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Asset;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Erwin, studentnummer 500889293
 * @version 1-12-2021
 *
 * Java Database Connectivity voor DB-tabel Asset
 */

@Repository
public class JdbcAssetDAO implements AssetDAO{

    JdbcTemplate jdbcTemplate;

    public JdbcAssetDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void bewaar(Asset asset) {
        String sql = "INSERT INTO Asset VALUES (?,?,?)";
        jdbcTemplate.update(sql, asset.getAfkorting(), asset.getNaam(), asset.getEuroKoers());
    }

    @Override
    public Asset vindAssetOpAfkorting(String afkorting) {
        String sql = "SELECT * FROM Asset WHERE afkorting = ?";
        return jdbcTemplate.queryForObject(sql, new AssetRowMapper(), afkorting);
    }

    @Override
    public List<Asset> geefAlleAssets() {
        String sql = "SELECT * FROM Asset";
        return jdbcTemplate.query(sql, new AssetRowMapper());
    }

    @Override
    public void ververs(Asset asset) {

    }

    private class AssetRowMapper implements RowMapper<Asset> {
        @Override
        public Asset mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Asset(resultSet.getString("afkorting"), resultSet.getString("naam"),
                    resultSet.getDouble("euroKoers"));
        }
    }
}
