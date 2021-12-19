package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Transactie;
import nl.hva.c25.team1.digivault.model.TransactiePartij;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Author Nienke
 * Version 14-12-2021
 */


public class JdbcTransactieDAO implements TransactieDAO {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTransactieDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

/*sla een transactie op met koper en verkoper die nummer krijgen van transactiepartij.*/

    @Override
    public Transactie bewaarTransacktieMetSK(Transactie transactie) {
        String sql = "INSERT INTO transactie(koper, verkoper, transactieDatum, transactieTijd, asset, aantalCryptos) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, transactie.getKoper().getTransactiePartijId());
                ps.setInt(2,transactie.getVerkoper().getTransactiePartijId());
                ps.setDate(3, Date.valueOf(transactie.getTransactieDatum()));
                ps.setTime(4,Time.valueOf(transactie.getTransactieTijd()));
                ps.setInt(5,transactie.getAsset().getAssetId());
                ps.setDouble(6,transactie.getAantalCryptos());
                return ps;
            }
        } , keyHolder);
        transactie.setTransactieId(keyHolder.getKey().intValue());
        return transactie;
    }
/*haal een transactie op aan de hand van transactieId*/
    @Override
    public Transactie vindTrasactieopTransactieId(int transactieId) {
        String sql = "SELECT * FROM transactie WHERE transactieId = ? ";
        Transactie transactie;
        try {
            transactie = jdbcTemplate.queryForObject(sql, new TransactieRowMapper(), transactieId);
        } catch (EmptyResultDataAccessException noRestult) {
            transactie = null;
        }
        return transactie;
    }
// geprobeerd te maken; geeft een verkoper mee, en krijgt terug een lijst met transacites,
    //snap niet hoe hij een lijst kan terug geven/of lijst maken... help..
    @Override
    public List<Transactie> vindAlleTransactiesOpVerkoper(TransactiePartij verkoper){
        String sql = "SELECT * FROM transactie WHERE verkoper = ? ";
        try {
            return jdbcTemplate.query(sql, new TransactieRowMapper(), verkoper);
        } catch (EmptyResultDataAccessException noResult) {
            return null;
        }
    }
    // geprobeerd te maken; geeft een koper mee, en krijgt terug een lijst met transacites,
    //snap niet hoe hij een lijst kan terug geven. help..

    @Override
    public List<Transactie> vindAlleTransactiesOpKoper(TransactiePartij koper){
        String sql = "SELECT * FROM transactie WHERE koper = ? ";

        try {
            return jdbcTemplate.query(sql, new TransactieRowMapper(), koper);
        } catch (EmptyResultDataAccessException noResult) {
            return null;
        }
    }

        private class TransactieRowMapper implements RowMapper<Transactie> {
            @Override
            public Transactie mapRow(ResultSet resultSet, int RowNumber) throws SQLException {
                return new Transactie(resultSet.getInt("transactieId"),
                        LocalDate.parse(resultSet.getString("transactieDatum")),
                        LocalTime.parse(resultSet.getString("transactieTijd")),
                                resultSet.getDouble("aantalCryptos"));
            }
        }
    }

