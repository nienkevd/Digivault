'use strict';

const domainArray = location.origin.split(':');
const urlLead = domainArray[0] + ':' + domainArray[1] + ':8080/';

// LOGO: Pagina verversen bij klik op #logoDigivault, verbergen van andere lagen
document.getElementById('logoDigivault').addEventListener('click', verversPagina);

function verversPagina() {
    location.reload();
}

// Loginpagina: actie bij klik op login knop en ingevulde velden

// stuur data als form data ipv json, want backend accepteert alleen form
// let formData = `emailadres=${email}&wachtwoord=${wachtwoord}`;

document.getElementById("login").addEventListener("click", (e) => {
    let email = document.getElementById("email_login").value;
    let wachtwoord = document.getElementById("wachtwoord_login").value;

    let data = {'emailadres': email, 'wachtwoord': wachtwoord};

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
            console.log(response);
            return response.json()})
        .then((response) => {
        // .then((data) => {
        //     console.log(data);
        //     if (data.error) {
        //         alert("Error Password or Username");
        //     } else {
                toonFinancieelOverzicht();
                // localStorage.setItem("token", response.header('Authorization'));
                let inMemoryToken = response.bearer;
                localStorage.setItem('user', JSON.stringify(response));
            // }
        })
        .catch((err) => {
            console.log(err);
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
