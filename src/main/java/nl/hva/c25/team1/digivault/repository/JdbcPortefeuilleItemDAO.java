// Created by antho
// Creation date 6-12-2021

package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.model.Portefeuille;
import nl.hva.c25.team1.digivault.model.PortefeuilleItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Map;

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

    /**
     * Deze methode slaat 1 item van een portefeuille op en geeft de sleutel terug.
     *
     * @param portefeuilleItem Het item dat opgeslagen moet worden.
     * @return De waarde van de surrogate key uit de DB.
     */
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

    /**
     * Deze methode geeft de lijst terug van alle items uit de portefeuille van de klant met opgegeven Id.
     *
     * @param klantId Het Id van de betreffende klant.
     * @return De complete portefeuille als lijst van items.
     */
    @Override
    public List<PortefeuilleItem> vindAlleItemsVanKlantMetId(int klantId) {
        String sql = "SELECT * FROM portefeuille_item WHERE klantId = ?";
        return jdbcTemplate.query(sql, new PortefeuilleItemRowMapper(), klantId);
    }

    /**
     * Deze methode past het aantal (double) van een bepaalde asset aan.
     *
     * @param portefeuilleItem Het betreffende item waarvan het aantal moet worden aangepast.
     */
    @Override
    public void updatePortefeuilleItem(PortefeuilleItem portefeuilleItem) {
        String sql = "UPDATE portefeuille_item SET aantal = ? WHERE itemId = ?";
        jdbcTemplate.update(sql, portefeuilleItem.getAantal(), portefeuilleItem.getPortefeuilleItemId());
    }

    /**
     * Deze methode geeft het portefeuilleitem van de meegegeven id.
     *
     * @param itemId Het Id van het item.
     * @return Het portefeuilleitem.
     */
    @Override
    public PortefeuilleItem vindItemMetId(int itemId) {
        String sql = "SELECT * FROM portefeuille_item WHERE itemId = ?";
        PortefeuilleItem portefeuilleItem;
        try {
            portefeuilleItem = jdbcTemplate.queryForObject(sql, new PortefeuilleItemRowMapper(), itemId);
        } catch(EmptyResultDataAccessException noResult) {
            portefeuilleItem = null;
        }
        return portefeuilleItem;
    }

}