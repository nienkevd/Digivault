package nl.hva.c25.team1.digivault.model;

import nl.hva.c25.team1.digivault.transfer.FinancieelOverzichtDto;
import nl.hva.c25.team1.digivault.transfer.RegisterDto;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Anneke, studentnummer 500889251
 * @version 1-12-2021
 *
 * Review Anthon 6-12-2021
 */

public class Klant {
    private int klantId;
    private String bsn;
    private LocalDate geboortedatum;
    private Naam naam;
    private Adres adres;
    private Account account;
    private Rekening rekening;
    private List<PortefeuilleItem> portefeuille;

    private Klant(int klantId, String bsn, LocalDate geboortedatum, Naam naam, Adres adres,
                 Account account, Rekening rekening, List<PortefeuilleItem> portefeuille) {
        this.klantId = klantId;
        this.bsn = bsn;
        this.geboortedatum = geboortedatum;
        this.naam = naam;
        this.adres = adres;
        this.account = account;
        this.rekening = rekening;
        this.portefeuille = portefeuille;
    }

    public Klant(String bsn, LocalDate geboortedatum, Naam naam, Adres adres, Account account) {
        this(0, bsn, geboortedatum, naam, adres, account, null, null);
    }

    public Klant(RegisterDto dto){
        this(dto.getBsn(), dto.getGeboortedatum(), new Naam(dto), new Adres(dto), new Account(dto) );
    }

    public Klant(int klantId, String bsn, LocalDate geboortedatum) {
        this(klantId,bsn,geboortedatum, null, null,null, null, null);
    }

    public Klant(String bsn, LocalDate geboortedatum) {
        this(0, bsn, geboortedatum, null, null, null, null, null);
    }

    public Klant(String bsn, LocalDate geboortedatum, Naam naam) {
        this.bsn = bsn;
        this.geboortedatum = geboortedatum;
        this.naam = naam;
    }
    public Klant (int klantId,Rekening rekening,List<PortefeuilleItem> portefeuille){
        this(klantId,"",null,null,null,null,rekening,portefeuille);
    }
    //InteliJ wilde dat we deze constructor aan maakte, hij pakte mijn eigen cnstr niet wanneer rekening nog b
// als object erin staat. voor nu laten staan.
    public Klant(int klantId, String iban, double saldo, List<PortefeuilleItem> portefeuille) {
    }

    public Klant(FinancieelOverzichtDto dto){
        this(dto.getKlantId(),dto.getIBAN(),dto.getSaldo(),dto.getPortefeuille());
    }
    public Klant(int klantId) {
        this(klantId, "", null);
    }


    public int getKlantId() {
        return klantId;
    }

    public void setKlantId(int klantId) {
        this.klantId = klantId;
    }

    public String getBsn() {
        return bsn;
    }

    public void setBsn(String bsn) {
        this.bsn = bsn;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(LocalDate geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public Naam getNaam() {
        return naam;
    }

    public void setNaam(Naam naam) {
        this.naam = naam;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Rekening getRekening() {
        return rekening;
    }

    public void setRekening(Rekening rekening) {
        this.rekening = rekening;
    }

    public List<PortefeuilleItem> getPortefeuille() {
        return portefeuille;
    }

    public void setPortefeuille(List<PortefeuilleItem> portefeuille) {
        this.portefeuille = portefeuille;
    }

    @Override
    public String toString() {
        return "Klant{" +
                "klantId=" + klantId +
                ", bsn='" + bsn + '\'' +
                ", geboortedatum=" + geboortedatum +
                ", naam=" + naam +
                ", adres=" + adres +
                ", account=" + account +
                ", rekening=" + rekening +
                ", portefeuille=" + portefeuille +
                '}';
    }
//    @Override
//    public String toString() {
//        return String.format("%s %s %s", klantId, bsn, geboortedatum);
//    }

}
