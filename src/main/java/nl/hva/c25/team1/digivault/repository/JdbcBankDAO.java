// Created by antho
// Creation date 23-12-2021

package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcBankDAO implements BankDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcBankDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private class BankRowMapper implements RowMapper<Bank> {
        @Override
        public Bank mapRow(ResultSet resultSet, int RowNumber) throws SQLException {
            return new Bank(resultSet.getInt("tpId"), resultSet.getString("banknaam"),
                    resultSet.getDouble("transactiePercentage"));
        }
    }

    @Override
    public Bank vindBankOpId(int bankId) {
        return null; // TODO: implement
    }

}