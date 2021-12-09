package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.repository.JdbcAssetDAO;
import nl.hva.c25.team1.digivault.repository.JdbcPortefeuilleItemDAO;

/**
 * @author Erwin, studentnummer 500889293
 * @version 9-12-2021
 */

public class FinancieelOverzichtService {

    private JdbcAssetDAO assetDAO;
    private JdbcPortefeuilleItemDAO portefeuilleItemDAO;

    public FinancieelOverzichtService(JdbcAssetDAO jdbcAssetDAO, JdbcPortefeuilleItemDAO jdbcPortefeuilleItemDAO) {
        super();
        this.assetDAO = jdbcAssetDAO;
        this.portefeuilleItemDAO = jdbcPortefeuilleItemDAO;
    }
}
