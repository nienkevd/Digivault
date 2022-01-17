'use strict';

// VERWIJZINGEN
const foutMeldingLogin = document.getElementById('foutMeldingLogin');

// MELDINGSBERICHTEN
const serverDownLoginMelding = `Er is geen verbinding met de server`;
const inlogFoutMelding = `Je inloggegevens zijn onjuist`;

// OVERIGE
const domainArray = location.origin.split(':');
const urlLead = domainArray[0] + ':' + domainArray[1] + ':8080/';

// LOGO: Pagina verversen bij klik op #logoDigivault, verbergen van andere lagen
document.getElementById('logoDigivault').addEventListener('click', verversPagina);

function verversPagina() {
    location.reload();
}

// VERWIJZINGEN LOGINPAGINA
const email = document.getElementById("email_login").value;
const wachtwoord = document.getElementById("wachtwoord_login").value;

// DECLARATIE VAN CONSTANTEN
const data = {'emailadres': email, 'wachtwoord': wachtwoord};

// LOGINPAGINA: fetch bij klik op login knop en ingevulde velden

document.getElementById("login").addEventListener("click", (e) => {
    e.preventDefault();
    console.log(JSON.stringify(data));
    fetch(urlLead + 'login', {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*'
        },
        body: JSON.stringify(data),
    })
        .then((response) => {
            console.log(response.status);
            console.log(response);
            return response.json()})
        .then((response) => {
            //TODO: response of data meegeven? Lijkt allebei te werken
        // .then((data) => {
        //     console.log(data);
        //     if (data.error) {
        //         alert("Error Password or Username");
        //     } else {
                toonFinancieelOverzicht();
                //TODO: hoe token meegeven?
                // localStorage.setItem("token", response.header('Authorization'));
                const inMemoryToken = response.bearer;
                localStorage.setItem('user', JSON.stringify(response));
            // }
        })
        .catch((err) => {
            console.log(err);
            foutMeldingLogin.innerHTML = serverDownLoginMelding;
        });
});

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

function toonRegistratieLaag() {
    registratieLaag.style.display = 'block';
    welkomsAanbieding.style.display = 'none';
}
