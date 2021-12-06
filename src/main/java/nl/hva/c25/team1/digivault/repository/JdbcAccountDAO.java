package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// review door Erwin, 1 december

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

    /**
     *
     * update emailadres en wachtwoord van de klant
     * @param account
     */

    @Override
    public void update(Account account) {
        String sql = "Update klant Set emailadres = ?, wachtwoord = ? Where klantId = ?;";
        jdbcTemplate.update(sql, account.getEmailadres(), account.getWachtwoord(), account.getKlant().getKlantId());
    }

    /**
     *
     * vindt een account in database adhv emailadres
     * @param emailadres
     * @return Account
     */

    @Override
    public Account vindAccountOpEmailadres(String emailadres) {
        String sql = "Select emailadres, wachtwoord From klant Where emailadres = ?";
        return jdbcTemplate.queryForObject(sql, new AccountRowMapper(), emailadres);
    }

    /**
     *
     * @return List<Account> geeft lijst van alle accounts uit DB terug
     */

    @Override
    public List<Account> geefAlleAccounts() {
        String sql = "Select emailadres, wachtwoord From klant";
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
