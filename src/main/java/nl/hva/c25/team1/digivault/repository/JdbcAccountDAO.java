package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// review door Erwin, 6 december

/**
 * Java Database Connectivity voor DB-tabel Account
 *
 * @author Sezi, studentnummer 500889525
 * @version 4-12-2021
 */

@Repository
public class JdbcAccountDAO implements AccountDAO {
    JdbcTemplate jdbcTemplate;

    public JdbcAccountDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void bewaar(Account account) {
        String sql = "Insert into account values (?,?)";
        jdbcTemplate.update(sql, account.getEmailadres(), account.getWachtwoord());
    }

    /**
     *
     * ververs gegevens van account
     * @param account
     */

    @Override
    public void ververs(Account account) {
        String sql = "Update account Set gebruikersnaam = ?, wachtwoord = ? ;";
        jdbcTemplate.update(sql, account.getEmailadres(), account.getWachtwoord());
    }

    /**
     *
     * vindt een account in database adhv emailadres
     * @param emailadres
     * @return Account
     */

    @Override
    public Account vindAccountOpEmailadres(String emailadres) {
        String sql = "Select * From account Where emailadres = ?";
        return jdbcTemplate.queryForObject(sql, new AccountRowMapper(), emailadres);
    }

    /**
     *
     * @return List<Account> geeft lijst van alle accounts uit DB terug
     */

    @Override
    public List<Account> geefAlleAccounts() {
        String sql = "Select * From account";
        return jdbcTemplate.query(sql, new AccountRowMapper());
    }

    private class AccountRowMapper implements RowMapper<Account> {
        @Override
        public Account mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Account(resultSet.getString("emailadres"),
                    resultSet.getString("wachtwoord"));
        }
    }
}
