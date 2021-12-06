// Created by antho
// Creation date 6-12-2021

package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Portefeuille;
import nl.hva.c25.team1.digivault.model.PortefeuilleItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;

/**
 * @author Anthon van Dijk (studentnummer 500889247)
 */
@Repository
public class JdbcPortefeuilleItemDAO implements PortefeuilleItemDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcPortefeuilleItemDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private class PortefeuilleItemRowMapper implements RowMapper<PortefeuilleItem> {
        @Override
        public PortefeuilleItem mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new PortefeuilleItem(resultSet.getInt("itemId"), resultSet.getDouble("aantal"));
        }
    }

    @Override
    public int bewaarPortefeuilleItem(PortefeuilleItem portefeuilleItem) {
        String sql = "INSERT INTO portefeuille_item (aantal) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setDouble(1, portefeuilleItem.getAantal());
                return ps;
            }
        }, keyHolder);
        int key = keyHolder.getKey().intValue();
        portefeuilleItem.setPortefeuilleItemId(key);
        return key;
    }

    @Override
    public Portefeuille vindPortefeuilleVanKlant(int klantId) {
        return null;
    }

    @Override
    public void updatePortefeuilleItem(PortefeuilleItem portefeuilleItem) {
        String sql = "UPDATE portefeuille_item SET aantal = ? WHERE itemId = ?";
        jdbcTemplate.update(sql, portefeuilleItem.getAantal(), portefeuilleItem.getPortefeuilleItemId());

    }

}