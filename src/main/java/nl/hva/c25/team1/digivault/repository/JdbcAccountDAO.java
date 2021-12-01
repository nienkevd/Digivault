package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// review door Erwin, 1 december: eventueel nog auteursinfo toevoegen

@Repository
public class JdbcAccountDAO implements AccountDAO {
    JdbcTemplate jdbcTemplate;

    public JdbcAccountDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void bewaar(Account account) {
        String sql = "Insert into account values (?,?)";
        jdbcTemplate.update(sql, account.getGebruikersnaam(), account.getWachtwoord());
    }

    @Override
    public void ververs(Account account) {
        String sql = "Update account Set gebruikersnaam = ?, wachtwoord = ? ;";
        jdbcTemplate.update(sql, account.getGebruikersnaam(), account.getWachtwoord());
    }

    @Override
    public Account vindAccountOpGebruikersnaam(String gebruikersnaam) {
        String sql = "Select * From account Where gebruikersnaam = ?";
        return jdbcTemplate.queryForObject(sql, new AccountRowMapper(), gebruikersnaam);
    }

    @Override
    public List<Account> geefAlleAccounts() {
        String sql = "Select * From account";
        return jdbcTemplate.query(sql, new AccountRowMapper());
    }

    private class AccountRowMapper implements RowMapper<Account> {
        @Override
        public Account mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Account(resultSet.getString("gebruikersnaam"),
                    resultSet.getString("wachtwoord"));
        }
    }
}
