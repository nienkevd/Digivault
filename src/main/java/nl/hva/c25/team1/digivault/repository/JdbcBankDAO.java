// Created by antho
// Creation date 23-12-2021

package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcBankDAO implements BankDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcBankDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Bank vindBankOpId(int bankId) {
        return null;
    }

}