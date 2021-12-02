package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Asset;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// review door Sezi, 1 december

/**
 * @author Erwin, studentnummer 500889293
 * @version 2-12-2021
 *
 * Java Database Connectivity voor DB-tabel Asset
 */

@Repository
public class JdbcAssetDAO implements AssetDAO {

    JdbcTemplate jdbcTemplate;

    public JdbcAssetDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void bewaar(Asset asset) {
        String sql = "INSERT INTO Asset VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, asset.getAssetId(), asset.getAfkorting(), asset.getNaam(), asset.getDagKoers());
    }

    @Override
    public Asset vindAssetOpId(int assetId) {
        String sql = "SELECT * FROM Asset WHERE assetId = ?";
        return jdbcTemplate.queryForObject(sql, new AssetRowMapper(), assetId);
    }

    @Override
    public List<Asset> geefAlleAssets() {
        String sql = "SELECT * FROM Asset";
        return jdbcTemplate.query(sql, new AssetRowMapper());
    }

    @Override
    public void ververs(Asset asset) {
        String sql = "UPDATE asset SET assetId = ?, afkorting = ?, naam = ?, dagKoers = ? ";
        jdbcTemplate.update(sql, asset.getAfkorting(), asset.getNaam(), asset.getDagKoers());
    }

    private class AssetRowMapper implements RowMapper<Asset> {
        @Override
        public Asset mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Asset(resultSet.getInt("assetId"), resultSet.getString("afkorting"),
                    resultSet.getString("naam"), resultSet.getDouble("dagKoers"));
        }
    }
}
