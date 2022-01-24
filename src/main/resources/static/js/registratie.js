'use strict';

// VERWIJZINGEN
const bevestigingSluiten = document.getElementById('bevestigingSluiten');
const naarRegistreren = document.getElementById('naarRegistreren');
const registreren = document.getElementById('registreren');
const loginPagina = document.getElementById('loginPagina');
const registratieBevestiging = document.getElementById('registratieBevestiging');
const registratieInformatie = document.getElementById('registratieInformatie');
const registratieLaag = document.getElementById('registratieLaag');
const foutMeldingRegistratie = document.getElementById('foutMeldingRegistratie');
const welkomsAanbieding = document.getElementById('welkomsAanbieding');

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
const serverDownMelding = `Er is geen verbinding met de server`;
const minimumLeeftijdMelding = `Sorry, je moet 18 jaar of ouder zijn om te registreren`;
const monkeyLeeftijdMelding = `Monkey-tester, vul je echte geboortedatum in`;
const regExpMailMelding = `Dit e-mailadres is niet geldig`;
const regExpPostcodeMelding = `Deze postcode is niet geldig`;
const legeVeldenMelding = `Je hebt nog niet alle verplichte velden ingevuld`;
const monkeyCijferMelding = `Je hebt letters ingevuld in een inputveld voor cijfers`;
const monkeyLetterMelding = `Je hebt cijfers ingevuld in een inputveld voor letters`;
const identiekWachtwoord = `De wachtwoorden komen overeen`;
const verschilWachtwoord = `De wachtwoorden zijn niet identiek`;
const kortWachtwoord = `Het wachtwoord moet uit minimaal 10 tekens bestaan`;
const kortBsn = `Een geldig BSN-nummer bevat 8 of 9 cijfers`;
const geldigeBsnMelding = `Controleer de geldigheid van je BSN-nummer`;

// OVERIGE
const emailRegExp = new RegExp(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/);
const postcodeRegExp = new RegExp(/^([1-9][0-9]{3}[a-zA-Z]{2})$/);
const alleenLetters = new RegExp(/^[A-Za-z]+$/);
const alleenCijfers = new RegExp(/^[0-9]+$/);

// LOSSE EVENT-LISTENERS
welkomsAanbieding.addEventListener('click', toonRegistratieLaag);
naarRegistreren.addEventListener('click', toonRegistratieLaag);
bevestigingSluiten.addEventListener('click', verbergRegistratieBevestiging);
wachtwoord_reg.addEventListener('keyup', checkOvereenkomenWachtwoorden);
wachtwoord_check.addEventListener('keyup', checkOvereenkomenWachtwoorden);

// REGISTRATIE LAAG - Tonen registratieLaag na klik #welkomsAanbieding en #naarRegistreren, verbergen #welkomsAanbieding
function toonRegistratieLaag() {
    registratieLaag.style.display = 'block';
    loginPagina.style.display = 'none';
    welkomsAanbieding.style.display = 'none';
}

// REGISTRATIE LAAG - Verbergen van de registratieLaag
function verbergRegistratieLaag() {
    registratieLaag.style.display = 'none';
    loginPagina.style.display = 'block';
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
    }
})

// REGISTRATIE VALIDATIE - Reset #foutMeldingRegistratie en voert alle validatiechecks uit op het registratieFormulier
function validatieRegistratie() {
    resetFoutMeldingRegistratie();
    monkeyCheckLetters();
    monkeyCheckCijfers();
    aantallenCheck();
    mailCheck();
    postcodeCheck();
    bsnCheck();
    legeVeldenCheck();
    leeftijdValidatie();
    return true;
}

// FOUTMELDINGREGISTRATIE - Hulpmethode die #foutMeldingRegistratie terugzet naar basisinstellingen (geen melding, rood)
function resetFoutMeldingRegistratie() {
    foutMeldingRegistratie.innerHTML = '';
    foutMeldingRegistratie.style.color = 'var(--divaRood)';
}

// VALIDATIE - Hulpmethode voor bsnCheck: zet een nul voor het bsn-nummer (als String)
function bsnMetNul(bsn) {
    return ("000000000" + bsn).slice(-9);
}

// VALIDATIE - Hulpmethode voor bsnCheck(): optellen en vermenigvuldigen bsn-nummer volgens elfproef
function optellenBsn(bsn) {

    let a = parseInt(bsn[0])*9;
    let b = parseInt(bsn[1])*8;
    let c = parseInt(bsn[2])*7;
    let d = parseInt(bsn[3])*6;
    let e = parseInt(bsn[4])*5;
    let f = parseInt(bsn[5])*4;
    let g = parseInt(bsn[6])*3;
    let h = parseInt(bsn[7])*2;
    let i = parseInt(bsn[8])*-1;

    return a + b + c + d + e + f + g + h + i;
}

// VALIDATIE - Controleert of ingevoerde bsn-nummer niet te kort is en of het voldoet aan de elfproef
function bsnCheck() {
    const bsnWaarde = bsn.value;

    if (isNaN(bsnWaarde) || bsnWaarde.length < 8) {
        console.log(">> validatiefout: te korte BSN");
        foutMeldingRegistratie.innerHTML = geldigeBsnMelding;
    }

    let bsnNegenCijfers = bsnMetNul(bsnWaarde);

    if (optellenBsn(bsnNegenCijfers) % 11) {
        console.log(">> validatiefout: ongeldige BSN");
        foutMeldingRegistratie.innerHTML = geldigeBsnMelding;
    }
}

// VALIDATIE - Zoekt eerst de datum van 18 jaar geleden op, en checkt of de geboortedatum voor die datum ligt
function leeftijdValidatie() {
    let vandaag = new Date();
    let ingevoerdeDatum = geboortedatum.value;
    let geboorteDatum = new Date(ingevoerdeDatum);

    let leeftijd = vandaag.getFullYear() - geboorteDatum.getFullYear();
    let aantalMaanden = vandaag.getMonth() - geboorteDatum.getMonth();
    let aantalDagen = vandaag.getDate() - geboorteDatum.getDate();

    if (aantalMaanden < 0 || (aantalMaanden === 0 && aantalDagen < 0)) {
        leeftijd -= 1;
    }
    if ((leeftijd === 18 && aantalMaanden <= 0 && aantalDagen <= 0) || leeftijd < 18) {
        console.log(">> validatiefout: leeftijd is te jong");
        foutMeldingRegistratie.innerHTML = minimumLeeftijdMelding;
    }
    if (leeftijd >= 121) {
        console.log(">> validatiefout: leeftijd is te oud");
        foutMeldingRegistratie.innerHTML = monkeyLeeftijdMelding;
    }
}

// REGISTRATIE VALIDATIE - Controleert of alle verplichte velden in registratieFormulier zijn ingevuld
function legeVeldenCheck() {
    if (email_reg.value === '' || wachtwoord_reg.value === '' || wachtwoord_check.value === ''|| voornaam.value === ''
        || achternaam.value === ''|| geboortedatum.value === '' || bsn.value === ''|| postcode.value === ''
        || huisnummer.value === '') {
        console.log(">> validatiefout: er zijn lege velden");
        foutMeldingRegistratie.innerHTML = legeVeldenMelding;
    }
}

// REGISTRATIE VALIDATIE - Controleert op juiste aantal cijfers bij wachtwoord en bsn-nummer
function aantallenCheck() {
    if (wachtwoord_reg.value.length < 10) {
        console.log(">> validatiefout: te kort wachtwoord");
        foutMeldingRegistratie.innerHTML = kortWachtwoord;
    }
    if (bsn.value.length < 8) {
        console.log(">> validatiefout: te kort bsn-nummer");
        foutMeldingRegistratie.innerHTML = kortBsn;
    }
}

// REGISTRATIE VALIDATIE - Controleert via RegExp of het ingevoerde mailadres voldoet aan algemene criteria
function mailCheck() {
    if (!emailRegExp.test(email_reg.value)) {
        console.log(">> validatiefout: ongeldige mailadres");
        foutMeldingRegistratie.innerHTML = regExpMailMelding;
    }
}

// REGISTRATIE VALIDATIE - Controleert of het een geldige Nederlandse postcode is
function postcodeCheck() {
    if (!postcodeRegExp.test(postcode.value)) {
        console.log(">> validatiefout: ongeldige postcode");
        foutMeldingRegistratie.innerHTML = regExpPostcodeMelding;
    }
}

// REGISTRATIE VALIDATIE - Checkt of er alleen letters worden ingevuld in voor-, achter-, straatnaam en woonplaats
function monkeyCheckLetters() {
    if (!alleenLetters.test(voornaam.value) || !alleenLetters.test(achternaam.value) ||
        !alleenLetters.test(straatnaam.value) || !alleenLetters.test(woonplaats.value)) {
        console.log(">> validatiefout: cijfers in veld met alleen letters");
        foutMeldingRegistratie.innerHTML = monkeyLetterMelding;
    }
}

// REGISTRATIE VALIDATIE - Checkt of er alleen cijfers worden ingevuld bij huisnummer en bsn
function monkeyCheckCijfers() {
    if (!alleenCijfers.test(huisnummer.value) || !alleenCijfers.test(bsn.value)) {
        console.log(">> validatiefout: letters in veld met alleen cijfers");
        foutMeldingRegistratie.innerHTML = monkeyCijferMelding;
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

// REGISTRATIE FORMULIER - Hulpmethode bij registreerFormulier() die de data verzamelt uit het #registratieFormulier
function verzamelDataRegistratieFormulier() {
    const registratieData = {
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
    };
    return registratieData;
}

// REGISTRATIE FORMULIER - Methode die het registreren van het #registratieFormulier daadwerkelijk uitvoert
function registreerFormulier() {
    let data = verzamelDataRegistratieFormulier();
    fetch(urlLead + 'registratie', {
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
            return response.json();
        })
        .then((data) => {
            if (data.error) {
                console.log(">> Algemene validatiefout");
                return false;
            } else {
                toonRegistratieBevestiging(data);
            }
        })
        .catch((err) => {
            console.log(err);
            foutMeldingRegistratie.innerHTML = serverDownMelding;
        });
}
