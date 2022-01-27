'use strict';

// vars voor currency conversion
// Anthon
let currency = localStorage.getItem('munteenheid');
let rate = 1.13;
let eurosaldo;
let dollarsaldo;
let eurowaarde;
let dollarwaarde;
let eurokosten;
let dollarkosten;

const symbols = document.getElementsByClassName('symbol');
const waarde = document.getElementById('waarde');
const kosten = document.getElementById('transactiekosten');

if (currency === 'EUR') {
    for (let symbol of symbols) symbol.textContent = '€';
} else {
    for (let symbol of symbols) symbol.textContent = '$';
}

// eventlistener for currency-button
// Anthon
document.getElementById('currency').addEventListener('click', (event) => {
    switchCurrency();
});

function switchCurrency() { // Anthon
    if (currency === 'EUR') {
        currency = 'USD';
        for (let symbol of symbols) symbol.textContent = '$';
        saldo.textContent = dollarsaldo;
        waarde.textContent = dollarwaarde;
        kosten.textContent = dollarkosten;
    } else {
        currency = 'EUR';
        for (let symbol of symbols) symbol.textContent = '€';
        saldo.textContent = eurosaldo;
        waarde.textContent = eurowaarde;
        kosten.textContent = eurokosten;
    }
}

// RELATIVE PATH URL FETCH
const domainArray = location.origin.split(':');
const urlLead = domainArray[0] + ':' + domainArray[1] + ':8080/';
const url = urlLead + 'financieeloverzicht';
const urltr = urlLead + 'transactie';

//Const verwijzing naar een veld op transactie pagina
const select = document.getElementById("dropdown")
const hoeveelheid = document.getElementById("hoeveelheid")
const koop = document.getElementById("koop")
const verkoop = document.getElementById("verkoop")
const saldo = document.getElementById("saldo");
const foutMeldingTransactie = document.getElementById('foutMeldingTransactie');
const terug = document.getElementById("terug")
const uitlog = document.getElementById("uitlog")

//Variabelen declareren
let assets = [];
let assetId;
let assetAantal;

//constante waardes declareren
const percentage = 0.02
const bankId = 1

//klantId ophalen van localStorage
const klantId = localStorage.getItem("klantId")

//Foutmeldingen koop en verkoop
const koopFoutMelding = `Je hebt niet genoeg saldo`;
const verkoopFoutMelding = 'Je hebt niet genoeg munten';
const legeVeldenMelding = 'Je moet hoeveelheid invullen en cryptomunt selecteren'


// Ophalen saldo vanaf financieeloverzicht
fetch(url, {
    method: 'POST',
    headers: {
        'Authorization': localStorage.getItem("token"),
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
    },
})
    .then(response => {
        if (response.status === 200) {
            return response.json()
        } else if (response.status === 401) { // als token niet meer geldig is
            toonLoginPaginaSessieVerlopen();
        } else {
            throw new Error("Er is iets verkeerd gegaan! " + response.status)
        }
    })
    .then(json =>
        toonSaldo(json)
    )
    .catch((err) => {
        console.log(err);
    });


// Als token verlopen is, bij response.status 401
function toonLoginPaginaSessieVerlopen() {
    localStorage.removeItem("token");
    alert("Sessie verlopen! Log opnieuw in.")
    window.location.href = "index.html";
}

function toonSaldo(json) {
    eurosaldo = parseFloat(json.saldo).toFixed(2);
    dollarsaldo = (rate * eurosaldo).toFixed(2);
    if (currency === 'EUR') {
        saldo.textContent = eurosaldo;
    } else {
        saldo.textContent = dollarsaldo;
    }
}

//Dropdown menu invullen met alle assets
select.addEventListener("click", (e) => {
    fetch(url, {
        method: "POST",
        headers: {
            'Authorization': localStorage.getItem("token"),
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*'
        },
    })
        .then(response => {
            if (response.status === 200) {
                return response.json()
            } else if (response.status === 401) { // als token niet meer geldig is
                toonLoginPaginaSessieVerlopen();
            } else {
                throw new Error("Er is iets verkeerd gegaan! " + response.status)
            }
        })
        .then(json => {
            assets = json.assetMetAantal;
            vulDropdownMenu(json);
        })
        .catch((err) => {
            console.log(err);
        })
})

function vulDropdownMenu(json) {
    for (let i = 0; i < assets.length; i++) {
        const option = document.createElement('option');
        option.text = option.value = json.assetMetAantal[i].naam;
        select.add(option, 0);
    }
}

//Toon waarde en transactie kosten wanneer een asset is gekozen uit de dropdown menu
select.addEventListener("change", toonWaarde);

function toonWaarde() {
    if (select.innerText === 'Kies je cryptomunt') {
        waarde.innerText = '';
        kosten.innerText = '';
    } else {
        const asset = select.options[select.selectedIndex].value;
        let dagkoers;
        for (let i = 0; i < assets.length; i++) {
            if (assets[i].naam === asset) {
                dagkoers = assets[i].dagkoers;
                break;
            }
        }
        eurowaarde = (hoeveelheid.value * dagkoers).toFixed(2);
        dollarwaarde = (rate * eurowaarde).toFixed(2);
        eurokosten = (eurowaarde * percentage).toFixed(2);
        dollarkosten = (rate * eurokosten).toFixed(2);
        if (currency === 'EUR') {
            waarde.innerText = eurowaarde;
            kosten.innerText = eurokosten;
        } else {
            waarde.innerText = dollarwaarde;
            kosten.innerText = dollarkosten;
        }
    }
}

//Toon waarde en transactie kosten wanneer hoeveelheid is gewijzigd
hoeveelheid.addEventListener("input", toonWaarde)

//Als een munt gekozen is uit de dropdown menu wordt assetId bijgehouden
select.addEventListener("change", setAssetId);
select.addEventListener("change", toonMuntenInWallet);

function toonMuntenInWallet() {
    setAssetAantal();
    document.getElementById('coins').textContent = assetAantal;
}


function setAssetId() {
    const asset = select.options[select.selectedIndex].value;
    for (let i = 0; i < assets.length; i++) {
        if (assets[i].naam === asset) {
            assetId = assets[i].assetId;
            break;
        }
    }
}

//vindt het aantal van de gekozen cryptomunten dat de klant in zijn portefeuille heeft
function setAssetAantal() {
    const asset = select.options[select.selectedIndex].value;
    for (let i = 0; i < assets.length; i++) {
        if (assets[i].naam === asset) {
            assetAantal = assets[i].aantal;
            break;
        }
    }
}

koop.addEventListener('click', function (e) {
    if (validatieKoopTransactie()) {
        e.preventDefault();
        koopTransactie();
    }
})

// Transactie koop validatie
function validatieKoopTransactie() {
    resetFoutMeldingTransactie()
    if (legeVeldenCheck() && validatieSaldo()) {
        return true;
    } else {
        return false;
    }
}

//voer transactie uit als koop knop is gedrukt
function koopTransactie() {
    const data = {'koperId': klantId, 'verkoperId': bankId, 'assetId': assetId, 'aantal': hoeveelheid.value};

    fetch(urltr, {
        method: "POST",
        headers: {
            'Authorization': localStorage.getItem("token"),
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*'
        },
        body: JSON.stringify(data),
    })
        .then(response => {
            if (response.status === 200) {
                return response.blob()
            } else if (response.status === 401) { // als token niet meer geldig is
                toonLoginPaginaSessieVerlopen();
            } else {
                throw new Error("Er is iets verkeerd gegaan! " + response.status)
            }
        })
        .then((json) => {
                toonTransactieBevestiging();
        })
        .catch((err) => {
            console.log(err);
        })
}



// Foutmelding Transactie - Hulpmethode die #foutMeldingTransactie terugzet naar basisinstellingen (geen melding, rood)
function resetFoutMeldingTransactie() {
    foutMeldingTransactie.innerHTML = '';
    foutMeldingTransactie.style.color = 'var(--divaRood)';
}

// Transactie validatie - checkt of er genoeg saldo is om te kopen
function validatieSaldo() {
    let totaal = parseFloat(eurowaarde) + parseFloat(eurokosten);
    if (eurosaldo < totaal) {
        foutMeldingTransactie.innerHTML = koopFoutMelding;
        return false;
    }
    return true;
}

verkoop.addEventListener('click', function (e) {
    if (validatieVerkoopTransactie()) {
        e.preventDefault();
        verkoopTransactie();
    }
})

// Transactie verkoop validatie
function validatieVerkoopTransactie() {
    foutMeldingTransactie.innerHTML = '';
    foutMeldingTransactie.style.color = 'var(--divaRood)';
    if (legeVeldenCheck() && validatieMunten()){
        return true;
    } else {
        return false;
    }
}

//voer transactie uit als verkoop knop is gedrukt
function verkoopTransactie() {
    const data = {'koperId': bankId, 'verkoperId': klantId, 'assetId': assetId, 'aantal': hoeveelheid.value};
    fetch(urltr, {
        method: "POST",
        headers: {
            'Authorization': localStorage.getItem("token"),
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*'
        },
        body: JSON.stringify(data),
    })
        .then(response => {
            if (response.status === 200) {
                return response.blob()
            } else if (response.status === 401) { // als token niet meer geldig is
                toonLoginPaginaSessieVerlopen();
            } else {
                throw new Error("Er is iets verkeerd gegaan! " + response.status)
            }
        })
        .then((json) => {
            toonTransactieBevestiging();
        })
        .catch((err) => {
            console.log(err);
        });
}



// Transactie validatie - checkt of er genoeg cryptomunten zijn om te verkopen
function validatieMunten() {
    setAssetAantal();
    if (assetAantal < hoeveelheid.value) {
        foutMeldingTransactie.innerHTML = verkoopFoutMelding;
        return false;
    }
    return true;
}

// Transactie validatie - checkt of alle verplichte velden bij transactie zijn ingevuld
function legeVeldenCheck() {
    console.log(select.options[select.selectedIndex].text);
    if (hoeveelheid.value ==='' || select.options[select.selectedIndex].text === 'Kies je cryptomunt') {
        console.log(">> validatiefout: lege velden");

        foutMeldingTransactie.innerHTML = legeVeldenMelding;
        return false;
    }
    console.log("legeveldencheck true");
    return true;
}

//als de klant op Terug klikt gaat het naar financieel overzocht pagina
terug.addEventListener('click', (event) => {
    toonFinancieelOverzicht();
});

//als de klant op Uitloggen klikt gaat het naar inlog pagina
uitlog.addEventListener('click', (event) => {
    toonLoginPagina();
});

function toonFinancieelOverzicht() {
    localStorage.setItem('munteenheid', currency);
    window.location.href = "financieelOverzicht.html";
}

function toonLoginPagina() {
    window.location.href = "index.html";
}

//Klik op LOGO naar financieel overzicht
document.getElementById('logoDigivault').addEventListener('click', toonFinancieelOverzicht);


//TRANSACTIE BEVESTIGING

//transactieBevestiging constante
const transactieBevestiging = document.getElementById('transactieBevestiging');
const transactiePagina = document.getElementById('transactiePagina');

//Event listner
bevestigingSluiten.addEventListener('click', verbergTransactieBevestiging);

// REGISTRATIE BEVESTIGING - Tonen van #registratieBevestiging met daarop de melding dat registratie gelukt is
function toonTransactieBevestiging() {
    verbergTransactieLaag();
    transactieBevestiging.style.display = 'block';

}

// REGISTRATIE BEVESTIGING - Verbergen van #registratieBevestiging
function verbergTransactieBevestiging() {
    toonFinancieelOverzicht()

}

function verbergTransactieLaag() {
    transactiePagina.style.display = 'none';

}