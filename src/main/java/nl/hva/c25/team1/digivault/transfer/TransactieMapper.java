// Created by antho
// Creation date 2-1-2022

package nl.hva.c25.team1.digivault.transfer;

import nl.hva.c25.team1.digivault.model.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Anthon
 */
public final class TransactieMapper {

    public static Transactie toObject(TransactieDTO transactieDTO) {
        Transactie transactie = new Transactie(LocalDate.now(), LocalTime.now(), transactieDTO.getAantal());
        transactie.setAsset(new Asset(transactieDTO.getAssetId()));
        int koperId = transactieDTO.getKoperId();
        int verkoperId = transactieDTO.getVerkoperId();
        if (koperId < 10) { // koper is bank
            transactie.setKoper(new Bank(koperId));
        } else { // koper is klant
            transactie.setKoper(new Klant(koperId));
        }
        if (verkoperId < 10) { // verkoper is bank
            transactie.setVerkoper(new Bank(verkoperId));
        } else { // verkoper is klant
            transactie.setVerkoper(new Klant(verkoperId));
        }
        return transactie;
    }

}