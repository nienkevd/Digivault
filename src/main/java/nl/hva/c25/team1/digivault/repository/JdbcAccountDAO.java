package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.model.Klant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

// review door Erwin, 6 december

/**
 * Java Database Connectivity voor DB-tabel Account
 *
 * @author Sezi, studentnummer 500889525
 * @version 8-12-2021
 */

@Repository
public class JdbcAccountDAO implements AccountDAO {
    JdbcTemplate jdbcTemplate;

    public JdbcAccountDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Slaat Account op in Database en geeft surrogate key (SK) accountId terug
     * @param account de te bewaren Account
     * @return de gegenereerde accountId
     */

//    @Override
//    public Account bewaarAccountMetSK(Account account) {
//        String sql = "INSERT INTO transactiepartij (emailadres, wachtwoord) VALUES (?, ?)";
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//                ps.setString(1, account.getEmailadres());
//                ps.setString(2, account.getWachtwoord());
//                return ps;
//            }
//        }, keyHolder);
//        account.setAccountId(keyHolder.getKey().intValue());
//        return account;
//    }

    /**
     *
     * update emailadres en wachtwoord van de account
     * @param klant
     */

    @Override
    public void updateAccount(Klant klant) {
        String sql = "UPDATE transactiepartij SET emailadres = ?, wachtwoord = ? WHERE tpId = ?;";
        jdbcTemplate.update(sql, klant.getAccount().getEmailadres(), klant.getAccount().getWachtwoord(),
        klant.getTransactiepartijId());
    }

//    /**
//     *
//     * vindt een account in database adhv accountId
//     * @param accountId
//     * @return Account
//     */
//
//    @Override
//    public Account vindAccountOpAccountId(int accountId) {
//        String sql = "Select * From account Where accountId = ?";
//        return jdbcTemplate.queryForObject(sql, new AccountRowMapper(), accountId);
//    }

    @Override
    public Account vindAccountOpKlantId(int klantId) {
        String sql = "SELECT emailadres, wachtwoord FROM transactiepartij WHERE tpId = ?";
        return jdbcTemplate.queryForObject(sql, new AccountRowMapper(), klantId);
    }

    @Override
    public Account vindAccountOpEmailAdres(String emailAdres) {
        String sql = "SELECT emailadres, wachtwoord FROM transactiepartij WHERE emailadres = ?";
        return jdbcTemplate.queryForObject(sql, new AccountRowMapper(), emailAdres);
    }

    /**
     *
     * @return List<Account> geeft lijst van alle accounts uit DB terug
     */

    @Override
    public List<Account> geefAlleAccounts() {
        String sql = "SELECT emailadres, wachtwoord FROM transactiepartij";
        return jdbcTemplate.query(sql, new AccountRowMapper());
    }

    private class AccountRowMapper implements RowMapper<Account> {
        @Override
        public Account mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Account(resultSet.getString("emailadres"), resultSet.getString("wachtwoord"));
        }
    }
}
