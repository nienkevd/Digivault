package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// review door Sezi, 1 december

/**
 * Java Database Connectivity voor DB-tabel Asset
 *
 * @author Erwin, studentnummer 500889293
 * @version 2-12-2021
 */

@Repository
public class JdbcAssetDAO implements AssetDAO {

    JdbcTemplate jdbcTemplate;

    /**
     * Constructor van JdbcAssetDAO
     * @param jdbcTemplate Jdbc Template
     */
    @Autowired
    public JdbcAssetDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Slaat Asset op in Database
     * @param asset de te bewaren Asset
     */
    @Override
    public void bewaar(Asset asset) {
        String sql = "INSERT INTO Asset VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, asset.getAssetId(), asset.getAfkorting(), asset.getNaam(), asset.getDagKoers());
    }

    /**
     * Vindt Asset op assetId in Database
     * @param assetId waarop Asset gezocht wordt
     * @return de bijbehorende Asset
     */
    @Override
    public Asset vindAssetOpId(int assetId) {
        String sql = "SELECT * FROM Asset WHERE assetId = ?";
        return jdbcTemplate.queryForObject(sql, new AssetRowMapper(), assetId);
    }

    /**
     * Geeft een lijst van alle Assets terug
     * @return lijst met alle Assets
     */
    @Override
    public List<Asset> geefAlleAssets() {
        String sql = "SELECT * FROM Asset";
        return jdbcTemplate.query(sql, new AssetRowMapper());
    }

    /**
     * Ververst een bepaalde Asset
     * @param asset welke ververst moet worden
     */
    @Override
    public void ververs(Asset asset) {
        String sql = "UPDATE asset SET assetId = ?, afkorting = ?, naam = ?, dagKoers = ? ";
        jdbcTemplate.update(sql, asset.getAfkorting(), asset.getNaam(), asset.getDagKoers());
    }

    private class AssetRowMapper implements RowMapper<Asset> {
        @Override
        public Asset mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Asset(resultSet.getString("afkorting"), resultSet.getString("naam"));
        }
    }
}
