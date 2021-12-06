package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Rekening;

import java.util.List;

// review door Erwin, 1 december

/**
 * Interface met de te implementeren methodes voor JdbcRekeningDAO
 *
 * @author Sezi, studentnummer 500889525
 * @version 1-12-2021
 */

public interface RekeningDAO {

    public void bewaar(Rekening rekening);

    public void bewaarRekeningMetSK(Rekening rekening);

    public void updateRekening(Rekening rekening);

    public Rekening vindRekeningOpIBAN(String IBAN);

    public List<Rekening> geefAlleRekeningen();
}
