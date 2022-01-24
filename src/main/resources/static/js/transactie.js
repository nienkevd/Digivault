// RELATIVE PATH URL FETCH
const domainArray = location.origin.split(':');
const urlLead = domainArray[0] + ':' + domainArray[1] + ':8080/';
const url = urlLead + 'financieeloverzicht';
const urltr = urlLead + 'transactie';
//asset array in financieeloverzicht json bestand
let assets;
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
//bankId is altijd 1
const bankId = 1


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
        if(response.status === 200){
            return response.json()
        } else if(response.status === 401){
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

function toonSaldo (json) {
    const saldo = document.getElementById("saldo")
    saldo.append(json.saldo);
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
        });

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
            if (assets[i].naam == asset) {
                dagkoers = assets[i].dagkoers;
                break;
            }
        }
        const waarde = hoeveelheid.value * dagkoers;
        document.getElementById("waarde").innerText = waarde.toFixed(2);
        const transactiekosten = waarde * percentage;
        document.getElementById("transactiekosten").innerText = transactiekosten.toFixed(2);
    }

    //Toon waarde en transactie kosten wanneer hoeveelheid is gewijzigd
    hoeveelheid.addEventListener("input", toonWaarde)

    select.addEventListener("change", setAssetId);
    function setAssetId() {
        const asset = select.options[select.selectedIndex].value;
        for (let i = 0; i < assets.length; i++) {
            if (assets[i].naam == asset) {
                assetId = assets[i].assetId;
                break;
            }
        }
    }
})

    //voer transactie uit als koop knop is gedrukt
    koop.addEventListener("click", (e) => {
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
                    return response.json()
                } else {
                    throw new Error("Er is iets verkeerd gegaan! " + response.status)
                }
            })
            .then(json => {
                toonFinancieelOverzicht();
                //toonTransactieBevestiging(data);
            })
            .catch((err) => {
                console.log(err);
            });

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
                        return response.json()
                    } else {
                        throw new Error("Er is iets verkeerd gegaan! " + response.status)
                    }
                })
                .then(json => {
                    toonFinancieelOverzicht();
                    //toonTransactieBevestiging(data);
                })
                .catch((err) => {
                    console.log(err);
                });
        })
})
//transactieBevestiging constante
const transactieBevestiging = document.getElementById('transactieBevestiging');
const transactiePagina = document.getElementById('transactiePagina');

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
    location.reload();
    //welkomsAanbieding.style.display = 'none';
}
function verbergTransactieLaag() {
    transactiePagina.style.display = 'none';
    loginPagina.style.display = 'block';
}
//KLIK OP LOGO GAAT NAAR FINANCIEEL OVERZICHT
document.getElementById('logoDigivault').addEventListener('click', toonFinancieelOverzicht);
function toonFinancieelOverzicht() {
    window.location.href = "FinancieelOverzicht.html";
}












// document.getElementById("koop").addEventListener("click", (e) => {
//     let hoeveelheid = document.getElementById("hoeveelheid").value;
//     let asset = document.getElementById("dropdown").value;
//     let data = {'hoeveelheid': hoeveelheid, 'dropdown': asset};
//
//         fetch("http://localhost:8080/transactie/10", {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json',
//                 'Access-Control-Allow-Origin': '*'
//             },
//             body: JSON.stringify(data),
//         })
//             .then((response) => {
//                 console.log(response);
//                 return response.json()
//             })
//             .then((data) => {
//                 console.log(data);
//                 if (data.error) {
//                     alert("Transactie niet mogelijk");
//                 } else {
//                     toonFinancieelOverzicht();
//                 }
//             })
//             .catch((err) => {
//                 console.log(err);
//             })
//     })