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
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Nienke
 * @author Anthon
 */

@Repository
public class JdbcTransactieDAO implements TransactieDAO {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTransactieDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

/*sla een transactie op met koper en verkoper die nummer krijgen van transactiepartij.*/

    @Override
    public Transactie bewaarTransacktieMetSK(Transactie transactie) {
        String sql = "INSERT INTO transactie(koperId, verkoperId, assetId, aantal, datum, tijdstip) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, transactie.getKoper().getTransactiepartijId());
                ps.setInt(2,transactie.getVerkoper().getTransactiepartijId());
                ps.setInt(3,transactie.getAsset().getAssetId());
                ps.setDouble(4,transactie.getAantalCryptos());
                ps.setDate(5, Date.valueOf(transactie.getTransactieDatum()));
                ps.setTime(6,Time.valueOf(transactie.getTransactieTijd()));
                return ps;
            }
        } , keyHolder);
        transactie.setTransactieId(keyHolder.getKey().intValue());
        return transactie;
    }
/*haal een transactie op aan de hand van transactieId*/
    @Override
    public Transactie vindTransactieOpTransactieId(int transactieId) {
        String sql = "SELECT * FROM transactie WHERE transactieId = ? ";
        Transactie transactie;
        System.out.println("transactie start");
        try {
            transactie = jdbcTemplate.queryForObject(sql, new TransactieRowMapper(), transactieId);
        } catch (EmptyResultDataAccessException noResult) {
            transactie = null;
        }
        System.out.println("transactie eind");
        return transactie;
    }

    @Override
    public List<Transactie> vindAlleTransactiesOpVerkoper(TransactiePartij verkoper){
        String sql = "SELECT * FROM transactie WHERE verkoper = ? ";
        try {
            return jdbcTemplate.query(sql, new TransactieRowMapper(), verkoper);
        } catch (EmptyResultDataAccessException noResult) {
            return null;
        }
    }

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
                Transactie transactie = new Transactie(
                        LocalDate.parse(resultSet.getString("transactieDatum")),
                        LocalTime.parse(resultSet.getString("transactieTijd")),
                        resultSet.getDouble("aantalCryptos"));
                transactie.setTransactieId(resultSet.getInt("transactieId"));
                return transactie;
            }
        }
    }
