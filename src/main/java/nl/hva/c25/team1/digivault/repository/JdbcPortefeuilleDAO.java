// Created by antho
// Creation date 1-12-2021

package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.model.Portefeuille;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class JdbcPortefeuilleDAO implements PortefeuilleDAO{

    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcPortefeuilleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private class KlantRowMapper implements RowMapper<Portefeuille> {
        @Override
        public Portefeuille mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Portefeuille(resultSet.getInt("portefeuilleId"),
                    resultSet.getDouble("totaleWaarde"));
        }
    }

    @Override
    public void bewaarPortefeuilleMetSleutel(Portefeuille portefeuille) {
        String sql = "INSERT INTO portefeuille (totaleWaarde) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setDouble(1, portefeuille.getTotaleWaarde());
                return ps;
            }
        }, keyHolder);
        portefeuille.setPortefeuilleId(keyHolder.getKey().intValue());
    }

    @Override
    public Portefeuille vindPortefeuilleOpId(int id) {
        String sql = "SELECT * FROM portefeuille WHERE portefeuilleId = ?";
        Portefeuille portefeuille;
        try {
            portefeuille = jdbcTemplate.queryForObject(sql, new KlantRowMapper(), id);
        } catch(EmptyResultDataAccessException noResult) {
            portefeuille = null;
        }
        return portefeuille;
    }

    @Override
    public void updatePortefeuille(Portefeuille portefeuille) {
        String sql = "UPDATE portefeuille SET totaleWaarde = ? WHERE portefeuilleId = ?";
        jdbcTemplate.update(sql, portefeuille.getTotaleWaarde());
    }

}