'use strict';

// VERWIJZINGEN
const foutMeldingLogin = document.getElementById('foutMeldingLogin');

// MELDINGSBERICHTEN
const inlogFoutMelding = `Je inloggegevens zijn onjuist`;
const geduldMelding = `Even geduld a.u.b.`;

// OVERIGE
const domainArray = location.origin.split(':');
const urlLead = domainArray[0] + ':' + domainArray[1] + ':8080/';

// LOGO: Pagina verversen bij klik op #logoDigivault, verbergen van andere lagen
document.getElementById('logoDigivault').addEventListener('click', verversPagina);

function verversPagina() {
    location.reload();
}

// LOGINPAGINA: fetch bij klik op login knop en ingevulde velden

document.getElementById("login").addEventListener("click", (e) => {
    // Verwijzingen loginPagina
    const email = document.getElementById("email_login").value;
    const wachtwoord = document.getElementById("wachtwoord_login").value;

// DECLARATIE VAN CONSTANTEN
    const data = {'emailadres': email, 'wachtwoord': wachtwoord};

    foutMeldingLogin.innerHTML = geduldMelding;

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
            // statuscheck
            if (response.status === 200) {
                localStorage.setItem("token", response.headers.get("Bearer"));
            }
            return response.json()
        })
        .then((data) => {
            console.log(data);
            if (data.error) {
                foutMeldingLogin.innerHTML = inlogFoutMelding;
                console.log(">> fout: verkeerde inloggegevens");
            } else {
                toonFinancieelOverzicht();
            }
        })
        .catch((err) => {
            console.log(err);
            // if (email === '' && wachtwoord === '') {
            //     foutMeldingLogin.innerHTML = inlogFoutMelding;
            //     console.log(">> fout: verkeerde inloggegevens");
            // }
            foutMeldingLogin.innerHTML = serverDownMelding;
            console.log(">> fout: geen respons van de server");
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
