package nl.hva.c25.team1.digivault.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RootRepository {

    JdbcTemplate jdbcTemplate;
    KlantDAO klantDAO;
    RekeningDAO rekeningDAO;
    AccountDAO accountDAO;
    PortefeuilleItemDAO portefeuilleItemDAO;

    @Autowired
    public RootRepository(JdbcTemplate jdbcTemplate, KlantDAO klantDAO, RekeningDAO rekeningDAO, AccountDAO accountDAO,
                          PortefeuilleItemDAO portefeuilleItemDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.klantDAO = klantDAO;
        this.rekeningDAO = rekeningDAO;
        this.accountDAO = accountDAO;
        this.portefeuilleItemDAO = portefeuilleItemDAO;
    }

    // TODO: bij registreer klant moet de methode public int slaLegePortefeuilleOpMetSleutel() worden gebruikt

/*
methode slaKlantOp(Klant klant--> meegegeven vanuit Postman/Frontend){

!!BewaarmetKey geeft geen int terug maar hele object!!

Klant nieuweKlant = klantDAO.bewaarKlantMetKey --> key wordt in methode in jdbc al gezet)

In naam en adres ook Klant meegeven? Dan findNaam/AdresOpKlantId?
Naam naam = naamDAO.bewaarNaamMetKey --> (nieuweKlant.setNaam(naam))
Adres adres = adresDAO.bewaarAdresMetKey --> (nieuweKlant.setAdres(adres))

accountDAO.updateKlant(klantId) --> email en wachtwoord toevoegen aan klant

Rekening rekening = new Rekening(new IBAN, STARTSALDO)
rekeningDAO.bewaarRekeningMetKey(rekening)
nieuweKlant.setRekening

nieuwe lege portefeuille:
new Portefeuille portefeuille (standaard Map met de 20 assets en aantal op 0)?
klant.setPortefeuille(portefeuille)

om goed in database te zetten:

for each door assetlijst
portefeuilleDAO.slaItemOp(Klant, Asset, 0)
klant.getPortefeuille.setTotaleWaarde --> 0

}
 */


}
