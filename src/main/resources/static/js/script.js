'use strict';

// LOGO: Pagina verversen bij klik op #logoDigivault, verbergen van andere lagen
document.getElementById('logoDigivault').addEventListener('click', verversPagina);

function verversPagina() {
    location.reload();
}

// Loginpagina: actie bij klik op login knop en ingevulde velden

// stuur data als form data ipv json, want backend accepteert alleen form
// var formData = `emailadres=${email}&wachtwoord=${wachtwoord}`;

document.getElementById("login").addEventListener("click", (e) => {
    let email = document.getElementById("email_login").value;
    let wachtwoord = document.getElementById("wachtwoord_login").value;

    let data = {'emailadres': email, 'wachtwoord': wachtwoord};

    e.preventDefault();
    const login = 'http://localhost:8080/login';
    console.log(JSON.stringify(data));
    fetch(login, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*'
        },
        body: JSON.stringify(data),
    })
        .then((response) => {
            console.log(response);
            return response.json()})
        .then((data) => {
            console.log(data);
            if (data.error) {
                alert("Error Password or Username");
            } else {
                toonFinancieelOverzicht();
            }
        })
        .catch((err) => {
            console.log(err);
        });
});

// REGISTRATIE - Tonen van de registratieLaag bij klik op #welkomsAanbieding en #naarRegistreren
document.getElementById('welkomsAanbieding').addEventListener('click', toonRegistratieLaag);
document.getElementById('naarRegistreren').addEventListener('click', toonRegistratieLaag);

function toonRegistratieLaag() {
    document.getElementById('registratieLaag').style.display = 'block';
}

// REGISTRATIE - Verbergen van de registratieLaag (tijdelijk nog niet gebruikt)
function verbergRegistratieLaag() {
    document.getElementById('registratieLaag').style.display = 'none';
}

// REGISTRATIE - Na het invullen en validatie van het #registratieFormulier en klik op #registreren worden gegevens
// opgeslagen in de database
document.getElementById('registreren').addEventListener('click', (e) => {
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

    fetch("http://localhost:8080/registratie" + data , {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*'
        },
    })
        .then(response => response.json())
        .then(json => vulAdresGegevens(json));

    // e.preventDefault();
    // const login = 'http://localhost:8080/registratie';
    // fetch(login, {
    //     method: "POST",
    //     headers: {
    //         'Content-Type': 'application/json',
    //         'Access-Control-Allow-Origin': '*'
    //     },
    //     body: JSON.stringify(data),
    // })
    //     .then((response) => {
    //         console.log(response);
    //         return response.json()})
    //     .then((data) => {
    //         console.log(data);
    //         if (data.error) {
    //             alert("Registratie mislukt");
    //         } else {
    //             alert("Registratie geslaagd!")
    //         }
    //     })
    //     .catch((err) => {
    //         console.log(err);
    //     });
});

// REGISTRATIE - Vergelijkt de ingevulde values uit #wachtwoord_reg en #wachtwoord_check in de registratielaag en geeft
// in #wachtwoordCheckRegistratie weer of de wachtwoorden al dan niet overeen komen
document.getElementById('wachtwoord_reg').addEventListener('keyup', checkOvereenkomenWachtwoorden);
document.getElementById('wachtwoord_check').addEventListener('keyup', checkOvereenkomenWachtwoorden);

function checkOvereenkomenWachtwoorden() {
    let identiek = `komt overeen`;
    let verschil = `niet identiek`;
    let wachtwoord_reg = document.getElementById('wachtwoord_reg');
    let wachtwoord_check = document.getElementById('wachtwoord_check');
    let wachtwoord_info = document.getElementById('wachtwoordCheckInformatie');

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

// FINANCIEEL-OVERZICHT
function toonFinancieelOverzicht() {
    window.location.href = "FinancieelOverzicht.html";
}

// WACHTWOORD-RESET - Vanuit Login Scherm naar Wachtwoord vergeten pagina bij klik op #naarWachtwoordVergeten
document.getElementById('naarWachtwoordVergeten').addEventListener('click',toonWachtwoordVergeten);

function toonWachtwoordVergeten(){
    document.getElementById('wachtwoordVergetenLaag').style.display = 'block';
}

// WACHTWOORD-RESET - Vanuit WachtwoordVergetenLaag naar login en registreren
document.getElementById('naarLoginPagina').addEventListener('click', toonLoginPagina);
document.getElementById('naarRegistreren1').addEventListener('click', toonRegistratieLaag);

function toonLoginPagina(){
    document.getElementById('wachtwoordVergetenLaag').style.display = 'none';
}
