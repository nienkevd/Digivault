package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.model.Rekening;

import java.util.List;

public interface RekeningDAO {

    public void bewaar(Rekening rekening);

    public void ververs(Rekening rekening);

    public Rekening vindRekeningOpIBAN(String IBAN);

    public List<Rekening> geefAlleRekeningen();
}