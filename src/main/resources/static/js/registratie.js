'use strict';

// VERWIJZINGEN
const bevestigingSluiten = document.getElementById('bevestigingSluiten');
const naarRegistreren = document.getElementById('naarRegistreren');
const registreren = document.getElementById('registreren');
const registratieBevestiging = document.getElementById('registratieBevestiging');
const registratieInformatie = document.getElementById('registratieInformatie');
const registratieLaag = document.getElementById('registratieLaag');
const foutMeldingRegistratie = document.getElementById('foutMeldingRegistratie');
const welkomsAanbieding = document.getElementById('welkomsAanbieding');
const url = 'http://localhost:8080/registratie';

// INPUTVELDEN
const email_reg = document.getElementById('email_reg');
const wachtwoord_reg = document.getElementById('wachtwoord_reg');
const wachtwoord_check = document.getElementById('wachtwoord_check');
const voornaam = document.getElementById('voornaam');
const tussenvoegsel = document.getElementById('tussenvoegsel');
const achternaam = document.getElementById('achternaam');
const geboortedatum = document.getElementById('geboortedatum');
const bsn = document.getElementById('bsn');
const postcode = document.getElementById('postcode');
const huisnummer = document.getElementById('huisnummer');
const toevoeging = document.getElementById('toevoeging');
const straatnaam = document.getElementById('straatnaam');
const woonplaats = document.getElementById('woonplaats');

// MELDINGSBERICHTEN
const regExpMailMelding = `Dit mailadres is niet geldig`
const regExpPostcodeMelding = `Deze postcode is niet geldig`
const legeVeldenMelding = `Je hebt nog niet alle verplichte velden ingevuld`
const monkeyMelding = `Je bent nu aan het monkey-testen, vul geldige gegevens in`
const identiekWachtwoord = `De wachtwoorden komen overeen`;
const verschilWachtwoord = `De wachtwoorden zijn niet identiek`;
const kortWachtwoord = `Het wachtwoord moet uit minimaal 10 tekens bestaan`;
const kortBsn = `Een geldig BSN-nummer bevat 8 of 9 cijfers`;
const geldigeBsnMelding = `Controleer de geldigheid van je BSN-nummer`;

// OVERIGE
const emailRegExp = new RegExp(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/);
const postcodeRegExp = new RegExp(/^([0-9]{4}[a-zA-Z]{2})$/)
const alleenLetters = new RegExp(/^[A-Za-z]+$/)
const alleenCijfers = new RegExp(/^[0-9]+$/)

// EVENT-LISTENERS
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
        console.log('Validatie is niet geslaagd');
    }
})

// REGISTRATIE VALIDATIE - Voert alle validatiechecks uit op het registratieFormulier
function validatieRegistratie() {
    foutMeldingRegistratie.style.color = 'var(--divaRood)';
    // bsnCheck();
    monkeyCheckLetters();
    monkeyCheckCijfers();
    aantallenCheck();
    mailCheck();
    postcodeCheck();
    legeVeldenCheck();
    return true;
}

// REGISTRATIE VALIDATIE - Controleert of BSN voldoet aan elfproef //
function bsnCheck() {
    console.log("bsnCheck")
    let bsnWaarde = bsn.value;
    let total = 0;
    bsnWaarde = verwijderPunten(bsnWaarde);
    let j = bsnWaarde.length;
    for(let i = 0; i < bsnWaarde.length; i++) {
        total += bsnWaarde.charAt(i) * j;
        j -= 1;
    }
    if((total % 11 ) !== 0) {
        console.log(">> bsnCheck")
        vertraging(200).then(() => { foutMeldingRegistratie.innerHTML = geldigeBsnMelding; });
    }
}

// REGISTRATIE VALIDATIE - Hulpmethode voor bsnCheck(), verwijdert punten //
function verwijderPunten(param) {
    let l = param.length;
    let bankacct = "";
    for(let i = 0; i < l; i++) {
        bankacct += param.charAt(i) !== "." ? param.charAt(i) : "";
    }
    return bankacct;
}

// REGISTRATIE VALIDATIE - Controleert of alle verplichte velden in registratieFormulier zijn ingevuld //
function legeVeldenCheck() {
    console.log("legeVeldenCheck")
    if(email_reg.value === '' || wachtwoord_reg.value === '' || wachtwoord_check.value === ''|| voornaam.value === ''
        || achternaam.value === ''|| geboortedatum.value === '' || bsn.value === ''|| postcode.value === ''
        || huisnummer.value === '') {
        console.log(">> legeVeldenCheck")
        foutMeldingRegistratie.innerHTML = legeVeldenMelding;
    }
}

// REGISTRATIE VALIDATIE - Controleert op juiste aantal cijfers bij wachtwoord en bsn-nummer //
function aantallenCheck() {
    console.log("aantallenCheck")
    if(wachtwoord_reg.value.length < 10) {
        console.log(">> aantallenCheck 1")
        foutMeldingRegistratie.innerHTML = kortWachtwoord;
    }
    if(bsn.value.length < 8) {
        console.log(">> aantallenCheck 2")
        foutMeldingRegistratie.innerHTML = kortBsn;
    }
}

// REGISTRATIE VALIDATIE - Controleert via RegExp of het ingevoerde mailadres voldoet aan algemene criteria //
function mailCheck() {
    console.log("mailCheck")
    if(!emailRegExp.test(email_reg.value)) {
        console.log(">> mailCheck")
        foutMeldingRegistratie.innerHTML = regExpMailMelding;
    }
}

// REGISTRATIE VALIDATIE - Controleert of het een geldige Nederlandse postcode is //
function postcodeCheck() {
    console.log("postcodeCheck")
    if(!postcodeRegExp.test(postcode.value)) {
        console.log(">> postcodeCheck")
        foutMeldingRegistratie.innerHTML = regExpPostcodeMelding;
    }
}

// REGISTRATIE VALIDATIE - Checkt of er alleen letters worden ingevuld in voor-, achter-, straatnaam en woonplaats //
function monkeyCheckLetters() {
    console.log("monkeyCheckLetters")
    if(!alleenLetters.test(voornaam.value) || !alleenLetters.test(achternaam.value) ||
        !alleenLetters.test(straatnaam.value) || !alleenLetters.test(woonplaats.value)) {
        console.log(">> monkeyCheckLetters")
        foutMeldingRegistratie.innerHTML = monkeyMelding;
    }
}

// REGISTRATIE VALIDATIE - Checkt of er alleen cijfers worden ingevuld bij huisnummer en bsn //
function monkeyCheckCijfers() {
    console.log("monkeyCheckCijfers")
    if (!alleenCijfers.test(huisnummer.value) || !alleenCijfers.test(bsn.value)) {
        console.log(">> monkeyCheckCijfers")
        foutMeldingRegistratie.innerHTML = monkeyMelding;
    }
}

// Hulpfunctie bij checkOvereenkomenWachtwoorden() - Bouwt vertraging in uitvoering code
function vertraging(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

// REGISTRATIE WACHTWOORD-CHECK - Vergelijkt de ingevulde values #wachtwoord_reg en #wachtwoord_check in registratielaag
// en geeft in #foutMeldingRegistratie gelijk weer of de wachtwoorden al dan niet overeen komen
function checkOvereenkomenWachtwoorden() {
    if (wachtwoord_reg.value.length === 0 || wachtwoord_check.value.length === 0) {
        foutMeldingRegistratie.innerHTML = '';
        foutMeldingRegistratie.style.color = 'black';
    } else if (wachtwoord_reg.value !== wachtwoord_check.value) {
        foutMeldingRegistratie.innerHTML = verschilWachtwoord;
        foutMeldingRegistratie.style.color = 'var(--divaRood)';
    } else if (wachtwoord_reg.value === wachtwoord_check.value) {
        foutMeldingRegistratie.innerHTML = identiekWachtwoord;
        foutMeldingRegistratie.style.color = 'var(--divaGroen)';
        vertraging(2500).then(() => { foutMeldingRegistratie.innerHTML = ''; });
    }
}

// REGISTRATIE FORMULIER - Methode die het registreren van het #registratieFormulier daadwerkelijk uitvoert
function registreerFormulier() {
    let data = {
        'emailadres': email_reg.value,
        'wachtwoord': wachtwoord_reg.value,
        'voornaam': voornaam.value,
        'tussenvoegsel': tussenvoegsel.value,
        'achternaam': achternaam.value,
        'geboortedatum': geboortedatum.value,
        'bsn': bsn.value,
        'straat': straatnaam.value,
        'huisnummer': huisnummer.value,
        'toevoeging': toevoeging.value,
        'postcode': postcode.value,
        'woonplaats': woonplaats.value
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
                return false;
            } else {
                toonRegistratieBevestiging(data);
                document.getElementById("registreren").contentWindow.location.reload();
            }
        })
        .catch((err) => {
            console.log(err);
        });
}
