// Created by antho
// Creation date 2-1-2022

package nl.hva.c25.team1.digivault.transfer;

import nl.hva.c25.team1.digivault.model.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Anthon
 */
public class TransactieMapper {

    private static final int LOWEST_ID_CUSTOMER = 10;

    public TransactieMapper() {
        super();
    }

    public Transactie toObject(TransactieDTO transactieDTO) {
        Transactie transactie = new Transactie(LocalDate.now(), LocalTime.now(), transactieDTO.getAantal());
        transactie.setAsset(new Asset(transactieDTO.getAssetId()));
        setKoper(transactie, transactieDTO.getKoperId());
        setVerkoper(transactie, transactieDTO.getVerkoperId());
        return transactie;
    }

    void setKoper(Transactie transactie, int koperId) {
        if (koperId < LOWEST_ID_CUSTOMER) { // koper is bank
            transactie.setKoper(new Bank(koperId));
        } else { // koper is klant
            transactie.setKoper(new Klant(koperId));
        }
    }

    void setVerkoper(Transactie transactie, int verkoperId) {
        if (verkoperId < LOWEST_ID_CUSTOMER) { // verkoper is bank
            transactie.setVerkoper(new Bank(verkoperId));
        } else { // verkoper is klant
            transactie.setVerkoper(new Klant(verkoperId));
        }
    }

}