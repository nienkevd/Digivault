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
const welkomsAanbieding = document.getElementById('welkomsAanbieding');

// EVENT_LISTENERS
welkomsAanbieding.addEventListener('click', toonRegistratieLaag);
naarRegistreren.addEventListener('click', toonRegistratieLaag);
bevestigingSluiten.addEventListener('click', verbergRegistratieBevestiging);
wachtwoord_reg.addEventListener('keyup', checkOvereenkomenWachtwoorden);
wachtwoord_check.addEventListener('keyup', checkOvereenkomenWachtwoorden);

// REGISTRATIE - Tonen van registratieLaag bij klik #welkomsAanbieding en #naarRegistreren, verbergen #welkomsAanbieding
function toonRegistratieLaag() {
    registratieLaag.style.display = 'block';
    welkomsAanbieding.style.display = 'none';
}

// REGISTRATIE - Verbergen van de registratieLaag (tijdelijk nog niet gebruikt)
function verbergRegistratieLaag() {
    registratieLaag.style.display = 'none';
}

// REGISTRATIE - Na het invullen en validatie van het #registratieFormulier en klik op #registreren worden gegevens
// opgeslagen in de database
registreren.addEventListener('click', function (e) {
    // if (validatieRegistratie()) {
    e.preventDefault();
    registreerFormulier();
    // }
})

// function validatieRegistratie() {
//     return true;
// }

function registreerFormulier() {
    let email_reg = document.getElementById('email_reg').value;             // emailadres
    let wachtwoord_reg = document.getElementById('wachtwoord_reg').value;   // wachtwoord
    let voornaam = document.getElementById('voornaam').value;               // voornaam
    let tussenvoegsel = document.getElementById('tussenvoegsel').value;     // tussenvoegsel
    let achternaam = document.getElementById('achternaam').value;           // achternaam
    let geboortedatum = document.getElementById('geboortedatum').value;     // geboortedatum
    let bsn = document.getElementById('bsn').value;                         // bsn
    let straatnaam = document.getElementById('straatnaam').value;           // straat
    let huisnummer = document.getElementById('huisnummer').value;           // huisnummer
    let toevoeging = document.getElementById('toevoeging').value;           // toevoeging
    let postcode = document.getElementById('postcode').value;               // postcode
    let woonplaats = document.getElementById('woonplaats').value;           // woonplaats
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

    const url = 'http://localhost:8080/registratie';
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
                alert("Registratie mislukt");
            } else {
                toonRegistratieBevestiging(data);
            }
        })
        .catch((err) => {
            console.log(err);
        });
}

// REGISTRATIE - Tonen van #registratieBevestiging met daarop de melding dat registratie gelukt is
function toonRegistratieBevestiging(data) {
    verbergRegistratieLaag();
    registratieBevestiging.style.display = 'block';
    registratieInformatie.innerHTML = data;
    welkomsAanbieding.style.display = 'none';
}

// REGISTRATIE - Verbergen van #registratieBevestiging
function verbergRegistratieBevestiging() {
    location.reload();
    welkomsAanbieding.style.display = 'none';
}

// REGISTRATIE - Vergelijkt de ingevulde values uit #wachtwoord_reg en #wachtwoord_check in de registratielaag en geeft
// in #wachtwoordCheckRegistratie weer of de wachtwoorden al dan niet overeen komen
function checkOvereenkomenWachtwoorden() {
    let identiek = `komt overeen`;
    let verschil = `niet identiek`;

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
