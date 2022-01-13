'use strict';

const bevestigingSluiten = document.getElementById('bevestigingSluiten');
const naarRegistreren = document.getElementById('naarRegistreren');
const registreren = document.getElementById('registreren');
const registratieBevestiging = document.getElementById('registratieBevestiging');
const registratieInformatie = document.getElementById('registratieInformatie');
const registratieLaag = document.getElementById('registratieLaag');
const wachtwoord_reg = document.getElementById('wachtwoord_reg');
const wachtwoord_check = document.getElementById('wachtwoord_check');
const wachtwoord_info = document.getElementById('wachtwoordCheckInformatie');
const foutMeldingRegistratie = document.getElementById('foutMeldingRegistratie');
const welkomsAanbieding = document.getElementById('welkomsAanbieding');
const url = 'http://localhost:8080/registratie';

const algemeneValidatieFout = `Validatiefout: Voer de juiste gegevens in`
const legeVeldenMelding = `Vul de ontbrekende gegevens in`

// EVENT LISTENERS
welkomsAanbieding.addEventListener('click', toonRegistratieLaag);
naarRegistreren.addEventListener('click', toonRegistratieLaag);
bevestigingSluiten.addEventListener('click', verbergRegistratieBevestiging);
wachtwoord_reg.addEventListener('keyup', checkOvereenkomenWachtwoorden);
wachtwoord_check.addEventListener('keyup', checkOvereenkomenWachtwoorden);

// REGISTRATIE LAAG - Tonen registratieLaag na klik #welkomsAanbieding en #naarRegistreren, verbergen #welkomsAanbieding
function toonRegistratieLaag() {
    registratieLaag.style.display = 'block';
    welkomsAanbieding.style.display = 'none';
}

// REGISTRATIE LAAG - Verbergen van de registratieLaag
function verbergRegistratieLaag() {
    registratieLaag.style.display = 'none';
}

// REGISTRATIE BEVESTIGING - Tonen van #registratieBevestiging met daarop de melding dat registratie gelukt is
function toonRegistratieBevestiging(data) {
    verbergRegistratieLaag();
    registratieBevestiging.style.display = 'block';
    registratieInformatie.innerHTML = data;
    welkomsAanbieding.style.display = 'none';
}

// REGISTRATIE BEVESTIGING - Verbergen van #registratieBevestiging
function verbergRegistratieBevestiging() {
    location.reload();
    welkomsAanbieding.style.display = 'none';
}

// REGISTRATIE FORMULIER - Na klik op #registreren worden de gegevens gecontroleerd en opgeslagen in de database
registreren.addEventListener('click', function (e) {
    if (validatieRegistratie()) {
        e.preventDefault();
        registreerFormulier();
    } else {
        console.log("Ik moet nu stoppen")
    }
})

function validatieRegistratie() {
    if(wachtwoord_reg.value === '' || wachtwoord_check.value === '') {
        document.getElementById('foutMeldingRegistratie').innerHTML = legeVeldenMelding;
    }
    return true;
}

// REGISTRATIE FORMULIER - Methode die het registreren van het #registratieFormulier daadwerkelijk uitvoert
function registreerFormulier() {
    let email_reg = document.getElementById('email_reg').value;
    let wachtwoord_reg = document.getElementById('wachtwoord_reg').value;
    let voornaam = document.getElementById('voornaam').value;
    let tussenvoegsel = document.getElementById('tussenvoegsel').value;
    let achternaam = document.getElementById('achternaam').value;
    let geboortedatum = document.getElementById('geboortedatum').value;
    let bsn = document.getElementById('bsn').value;
    let straatnaam = document.getElementById('straatnaam').value;
    let huisnummer = document.getElementById('huisnummer').value;
    let toevoeging = document.getElementById('toevoeging').value;
    let postcode = document.getElementById('postcode').value;
    let woonplaats = document.getElementById('woonplaats').value;
    let data = {
        'emailadres': email_reg,
        'wachtwoord': wachtwoord_reg,
        'voornaam': voornaam,
        'tussenvoegsel': tussenvoegsel,
        'achternaam': achternaam,
        'geboortedatum': geboortedatum,
        'bsn': bsn,
        'straat': straatnaam,
        'huisnummer': huisnummer,
        'toevoeging': toevoeging,
        'postcode': postcode,
        'woonplaats': woonplaats
    }

    console.log(JSON.stringify(data));
    fetch(url, {
        method: "POST",
        headers: {
            'Access-Control-Allow-Methods': 'POST',
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*'
        },
        body: JSON.stringify(data),
    })
        .then((response) => {
            console.log(response);
            return response.json()
        })
        .then((data) => {
            console.log(data);
            if (data.error) {
                console.log("Validatiefout")
            } else {
                toonRegistratieBevestiging(data);
            }
        })
        .catch((err) => {
            console.log(err);
        });
}

// REGISTRATIE WACHTWOORD-CHECK - Vergelijkt de ingevulde values #wachtwoord_reg en #wachtwoord_check in registratielaag
//  en geeft in #wachtwoordCheckInformatie gelijk weer of de wachtwoorden al dan niet overeen komen
function checkOvereenkomenWachtwoorden() {
    const identiek = `komt overeen`;
    const verschil = `niet identiek`;

    if (wachtwoord_reg.value.length === 0 || wachtwoord_check.value.length === 0) {
        wachtwoord_info.innerHTML = '';
        wachtwoord_info.style.color = 'black';
    } else if (wachtwoord_reg.value !== wachtwoord_check.value) {
        wachtwoord_info.innerHTML = verschil;
        wachtwoord_info.style.color = 'var(--divaRood)';
    } else if (wachtwoord_reg.value === wachtwoord_check.value) {
        wachtwoord_info.innerHTML = identiek;
        wachtwoord_info.style.color = 'var(--divaGroen)';
    }
}
