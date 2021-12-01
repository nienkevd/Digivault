package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Rekening;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// review door Erwin, 1 december

/**
 * @author Sezi, studentnummer 500889525
 * @version 1-12-2021
 */

@Repository
public class JdbcRekeningDAO implements RekeningDAO{

    JdbcTemplate jdbcTemplate;

    public JdbcRekeningDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void bewaar(Rekening rekening) {
        String sql = "Insert into rekening values (?,?)";
        jdbcTemplate.update(sql, rekening.getIBAN(), rekening.getSaldo());
    }

    @Override
    public void ververs(Rekening rekening) {
        String sql = "Update rekening Set IBAN = ?, saldo = ? ;";
        jdbcTemplate.update(sql, rekening.getIBAN(), rekening.getSaldo());
    }

    @Override
    public Rekening vindRekeningOpIBAN(String IBAN) {
        String sql = "Select * From rekening Where IBAN = ?";
        return jdbcTemplate.queryForObject(sql, new JdbcRekeningDAO.RekeningRowMapper(), IBAN);
    }

    @Override
    public List<Rekening> geefAlleRekeningen() {
        String sql = "Select * From rekening";
        return jdbcTemplate.query(sql, new JdbcRekeningDAO.RekeningRowMapper());
    }

    private class RekeningRowMapper implements RowMapper<Rekening> {
        @Override
        public Rekening mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Rekening(resultSet.getString("IBAN"),
                    resultSet.getDouble("saldo"));
        }
    }
}
