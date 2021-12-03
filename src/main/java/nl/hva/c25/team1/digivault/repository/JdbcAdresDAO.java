package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Adres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import java.sql.*;
import java.util.List;

/**
 * Java Database Connectivity voor DB-tabel Adres
 *
 * @author Anneke, studentnummer 500889251
 * @version 3-12-2021
 */

public class JdbcAdresDAO implements AdresDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAdresDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     *
     * slaat adres van klant op in database en genereert een surrogate key
     * @param adres welke opgeslagen moet worden
     * @return int adresID, de automatisch gegenereerde surrogate key
     */
    @Override
    public int bewaarAdresMetSK(Adres adres) {
        String sql = "INSERT INTO Adres a JOIN Ziphuisnr j ON a.ziphuisnrId = z.ziphuisnrId " +
                "(straat, huisnummer, toevoeging, postcode, woonplaats) VALUES (?,?,?,?,?);";
        KeyHolder keyholder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, "Jan van Galenstraat");
                ps.setInt(2,7);
                ps.setString(3,"A");
                ps.setString(4, "1051NJ");
                ps.setString(5,"Amsterdam" );
                return ps;
            }
        } , keyholder);
        adres.setAdresId(keyholder.getKey().intValue());
        return adres.getAdresId();
    }


    /**
     * vindt een adres in database adhv adresID
     * @param adresId
     * @return Adres
     */
    @Override
    public Adres vindAdresOpAdresId(int adresId) {
        String sql = "SELECT (adresId, straat, huisnummer, toevoeging," +
                "postcode, woonplaats) FROM Adres a JOIN Ziphuisnr z ON a.ziphuisnrId = z.ziphuisnrId" +
                "WHERE adresId = ?;";
        Adres adres;
        try {
            adres = jdbcTemplate.queryForObject(sql, new AdresRowMapper(), adresId);
        } catch (EmptyResultDataAccessException noResult) {
            adres = null;
        }
        return adres;
    }

    /**
     * geeft een lijst met alle adressen uit de database terug
     * @return List<Adres>
     */
    @Override
    public List<Adres> vindAlleAdressen() {
        String sql = "SELECT (adresId, straat, huisnummer, toevoeging, postcode, woonplaats)" +
                "FROM Adres a JOIN Ziphuisnr z ON a.ziphuisnrId = z.ziphuisnrId;";
        return jdbcTemplate.query(sql, new JdbcAdresDAO.AdresRowMapper());
    }

    /**
     * update adres van klant
     * @param adres
     */
    @Override
    public void update(Adres adres) {
        String sql = "UPDATE Adres SET adresId =?, straat = ?, huisnummer = ?, toevoeging = ?," +
                "postcode = ?, woonplaats = ?,";
        jdbcTemplate.update(sql, adres.getAdresId(), adres.getStraat(),adres.getHuisnummer(),
                adres.getToevoeging(),adres.getPostcode(),adres.getWoonplaats());
    }


    private class AdresRowMapper implements RowMapper<Adres> {
        @Override
        public Adres mapRow(ResultSet resultSet, int RowNumber) throws SQLException {
            return new Adres(resultSet.getInt("adresId"), resultSet.getString("straat"),
                    resultSet.getInt("huisnummer"), resultSet.getString("toevoeging"),
                    resultSet.getString("postcode"), resultSet.getString("woonplaats"));
        }
    }
}
