package nl.hva.c25.team1.digivault.transfer;

import nl.hva.c25.team1.digivault.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.*;
import static org.junit.jupiter.api.Assertions.*;

class TransactieMapperTest {

    TransactieMapper transactieMapper;
    Transactie transactie1, transactie2, transactie3;
    int id1, id2, id3;

    @BeforeEach
    void setUp() {
        transactieMapper = new TransactieMapper();
        transactie1 = new Transactie(LocalDate.now(), LocalTime.now(), 5);
        transactie2 = new Transactie(LocalDate.now(), LocalTime.now(), 5);
        transactie3 = new Transactie(LocalDate.now(), LocalTime.now(), 5);
        id1 = 1; // id van een bank
        id2 = 10; // id van een klant
        id3 = 0; // ongeldige id
    }

    @Test
    void setKoper() {
        transactieMapper.setKoper(transactie1, id1);
        TransactiePartij koper = transactie1.getKoper();
        assertEquals(1, koper.getTransactiepartijId()); // check de id
        assertTrue(koper instanceof Bank); // check of van type Bank

        transactieMapper.setKoper(transactie2, id2);
        koper = transactie2.getKoper();
        assertEquals(10, koper.getTransactiepartijId()); // check de id
        assertTrue(koper instanceof Klant); // check of van type Klant

        try {
            transactieMapper.setKoper(transactie3, id3);
            fail();
        }
        catch(IllegalArgumentException illegalArgumentException) {
            System.out.println("testSetKoper: " + illegalArgumentException.getMessage());
        }
        assertNull(transactie3.getKoper());
    }

    @Test
    void setVerkoper() {
        transactieMapper.setVerkoper(transactie1, id1);
        TransactiePartij verkoper = transactie1.getVerkoper();
        assertEquals(1, verkoper.getTransactiepartijId()); // check de id
        assertTrue(verkoper instanceof Bank); // check of van type Bank

        transactieMapper.setVerkoper(transactie2, id2);
        verkoper = transactie2.getVerkoper();
        assertEquals(10, verkoper.getTransactiepartijId()); // check de id
        assertTrue(verkoper instanceof Klant); // check of van type Klant

        try {
            transactieMapper.setVerkoper(transactie3, id3);
            fail();
        }
        catch(IllegalArgumentException illegalArgumentException) {
            System.out.println("testSetVerkoper: " + illegalArgumentException.getMessage());
        }
        assertNull(transactie3.getVerkoper());
    }

    @Test
    void toObject() {
        TransactieDTO transactieDTO = new TransactieDTO(id1, id2, 1, 5);
        Transactie transactie = transactieMapper.toObject(transactieDTO);
        assertEquals(LocalDate.now(), transactie.getTransactieDatum());
        assertTrue(Duration.between(transactie.getTransactieTijd(), LocalTime.now()).toSeconds() <= 1);
        assertEquals(5, transactie.getAantalCryptos(), 0.000001);
        assertEquals(1, transactie.getAsset().getAssetId());
        assertEquals(1, transactie.getKoper().getTransactiepartijId());
        assertEquals(10, transactie.getVerkoper().getTransactiepartijId());

        transactie = null;

        transactieDTO = new TransactieDTO(id3, id2, 1, 5); // ongeldige koperId
        try {
            transactie = transactieMapper.toObject(transactieDTO);
            fail();
        }
        catch(IllegalArgumentException illegalArgumentException) {
            System.out.println("testToObject: " + illegalArgumentException.getMessage());
        }
        assertEquals(null, transactie);

        transactieDTO = new TransactieDTO(id1, id3, 1, 5); // ongeldige verkoperId
        try {
            transactie = transactieMapper.toObject(transactieDTO);
            fail();
        }
        catch(IllegalArgumentException illegalArgumentException) {
            System.out.println("testToObject: " + illegalArgumentException.getMessage());
        }
        assertEquals(null, transactie);

        transactieDTO = new TransactieDTO(id1, id2, 1, 0); // ongeldig aantal
        try {
            transactie = transactieMapper.toObject(transactieDTO);
            fail();
        }
        catch(IllegalArgumentException illegalArgumentException) {
            System.out.println("testToObject: " + illegalArgumentException.getMessage());
        }
        assertEquals(null, transactie);

        transactieDTO = new TransactieDTO(id1, id2, 0, 5); // ongeldige assetId
        try {
            transactie = transactieMapper.toObject(transactieDTO);
            fail();
        }
        catch(IllegalArgumentException illegalArgumentException) {
            System.out.println("testToObject: " + illegalArgumentException.getMessage());
        }
        assertEquals(null, transactie);
    }

}