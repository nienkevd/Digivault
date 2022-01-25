// RELATIVE PATH URL FETCH
const domainArray = location.origin.split(':');
const urlLead = domainArray[0] + ':' + domainArray[1] + ':8080/';
const url = urlLead + 'financieeloverzicht';
const urltr = urlLead + 'transactie';
//asset array in financieeloverzicht json bestand
let assets = [];
//dropdown menu op transactie pagina
const select = document.getElementById("dropdown")
//hoeveelheid input veld
const hoeveelheid = document.getElementById("hoeveelheid")
//constant transactie kosten percentage
const percentage = 0.02
//knop koop
const koop = document.getElementById("koop")
//knop verkoop
const verkoop = document.getElementById("verkoop")
//klantID ophalen van localStorage
const klantId = localStorage.getItem("klantId")
//assetId declareren
let assetId;
//assetAantal declareren
let assetAantal;
//bankId is altijd 1
const bankId = 1
//Foutmeldingen koop en verkoop
const koopFoutMelding = `Je hebt niet genoeg saldo`;
const verkoopFoutMelding = 'Je hebt niet genoeg munten';
const legeVeldenMelding = 'Je moet hoeveelheid invullen en cryptomunt selecteren'
//Foutmelding op transactie pagina
const foutMeldingTransactie = document.getElementById('foutMeldingTransactie');
//saldo
const saldo = document.getElementById("saldo");
//waarde
let waarde;
//transactiekosten
let transactiekosten;
//saldo
let saldoValue;

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
        } else if (response.status === 401) {
            console.log(response.headers.get('Location'))
            window.location.href = response.headers.get('Location');
        }
    })
    .then(json =>
        toonSaldo(json)
    )
    .catch((err) => {
        console.log(err);
    });

function toonSaldo(json) {
    saldoValue = json.saldo
    document.getElementById("saldoValue").innerText = json.saldo;
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
            console.log(response)
            if (response.status === 200) {
                return response.json()
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
    const asset = select.options[select.selectedIndex].value;
    let dagkoers;
    for (let i = 0; i < assets.length; i++) {
        if (assets[i].naam === asset) {
            dagkoers = assets[i].dagkoers;
            break;
        }
    }
    waarde = hoeveelheid.value * dagkoers;
    document.getElementById("waarde").innerText = waarde.toFixed(2);
    transactiekosten = waarde * percentage;
    document.getElementById("transactiekosten").innerText = transactiekosten.toFixed(2);
}

//Toon waarde en transactie kosten wanneer hoeveelheid is gewijzigd
hoeveelheid.addEventListener("input", toonWaarde)

select.addEventListener("change", setAssetId);

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

//voer transactie uit als koop knop is gedrukt
function koopTransactie() {
    const data = {'koperId': klantId, 'verkoperId': bankId, 'assetId': assetId, 'aantal': hoeveelheid.value};
    console.log('check' + JSON.stringify(data));

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
            console.log(response)
            if (response.status === 200) {
                return response.blob()
            } else {
                throw new Error("Er is iets verkeerd gegaan! " + response.status)
            }
        })
        .then((data) => {
            if (data.error) {
                console.log(">> Algemene validatiefout");
                return false;
            } else {
                toonTransactieBevestiging(data);
            }
        })
        .catch((err) => {
            console.log(err);
        });
}

// Transactie koop validatie
function validatieKoopTransactie() {
    resetFoutMeldingTransactie();
    console.log('validatie');
    legeVeldenCheck();
    validatieSaldo();
    return true;
}

// Foutmelding Transactie - Hulpmethode die #foutMeldingTransactie terugzet naar basisinstellingen (geen melding, rood)
function resetFoutMeldingTransactie() {
    foutMeldingTransactie.innerHTML = '';
    foutMeldingTransactie.style.color = 'var(--divaRood)';
}

// Transactie validatie - checkt of er genoeg saldo is om te kopen
function validatieSaldo() {
    console.log('saldo check')
    let totaal = waarde + transactiekosten
    console.log(waarde)
    console.log(transactiekosten)
    console.log(saldoValue)
    if (saldoValue < totaal) {
        console.log(totaal + "saldo niet genoeg")
        foutMeldingTransactie.innerHTML = koopFoutMelding;
    }
}

//voer transactie uit als verkoop knop is gedrukt
verkoop.addEventListener("click", (e) => {
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
            console.log(response)
            if (response.status === 200) {
                return response.blob()
            } else {
                throw new Error("Er is iets verkeerd gegaan! " + response.status)
            }
        })
        .then((json) => {
            if (validatieVerkoopTransactie()) {
                e.preventDefault();
            } else {
                toonTransactieBevestiging(data);
            }
        })
        .catch((err) => {
            console.log(err);
        });
})

// Transactie verkoop validatie
function validatieVerkoopTransactie() {
    foutMeldingTransactie.innerHTML = '';
    foutMeldingTransactie.style.color = 'var(--divaRood)';
    legeVeldenCheck();
    validatieMunten();
    return true;
}

// Transactie validatie - checkt of er genoeg cryptomunten zijn om te verkopen
function validatieMunten() {
    setAssetAantal();
    if (assetAantal < hoeveelheid.value) {
        console.log("munten niet genoeg")
        foutMeldingTransactie.innerHTML = verkoopFoutMelding;
    }
}

// Transactie validatie - checkt of alle verplichte velden bij transactie zijn ingevuld
function legeVeldenCheck() {
    console.log("legeVeldenCheck");
    console.log(select.options[select.selectedIndex].text);
    if (hoeveelheid.value ==='' || /*select.innerText*/select.options[select.selectedIndex].text === '') {
        console.log(">> validatiefout: lege velden");
        console.log(hoeveelheid.value);

        foutMeldingTransactie.innerHTML = legeVeldenMelding;
    }
}

function toonFinancieelOverzicht() {
    window.location.href = "FinancieelOverzicht.html";
}

//Klik op LOGO naar financieel overzicht
document.getElementById('logoDigivault').addEventListener('click', toonFinancieelOverzicht);


//TRANSACTIE BEVESTIGING

//transactieBevestiging constante
const transactieBevestiging = document.getElementById('transactieBevestiging');
const transactiePagina = document.getElementById('transactiePagina');
//const transactieInformatie = document.getElementById('registratieInformatie');

//Event listner
bevestigingSluiten.addEventListener('click', verbergTransactieBevestiging);

// REGISTRATIE BEVESTIGING - Tonen van #registratieBevestiging met daarop de melding dat registratie gelukt is
function toonTransactieBevestiging(data) {
    verbergTransactieLaag();
    transactieBevestiging.style.display = 'block';
    //transactieInformatie.innerHTML = data;
    //welkomsAanbieding.style.display = 'none';
}

// REGISTRATIE BEVESTIGING - Verbergen van #registratieBevestiging
function verbergTransactieBevestiging() {
    toonFinancieelOverzicht()
    //welkomsAanbieding.style.display = 'none';
}

function verbergTransactieLaag() {
    transactiePagina.style.display = 'none';
    // loginPagina.style.display = 'block';
}

//KLIK OP LOGO GAAT NAAR FINANCIEEL OVERZICHT
document.getElementById('logoDigivault').addEventListener('click', toonFinancieelOverzicht);