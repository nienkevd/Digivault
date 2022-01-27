'use strict';

// JavaScript voor het vertonen van koersinformatie in koersDiagram.html
// Auteur: Erwin, studentnummer 500889293

// DECLARATIE URL-REQUEST
let basisString = 'wss://stream.binance.com:9443/ws/'
let koersString = 'btc'
let valutaString = 'eur'
let eindString = '@trade'
const wsRequest = new WebSocket(basisString + koersString + valutaString + eindString);
const koersBTC = document.getElementById('huidigeKoers');

// OPHALEN VAN ONLINE KOERSINFORMATIE
wsRequest.onmessage = (event) => {
    let stockObject = JSON.parse(event.data)
    let waarde = parseFloat(stockObject.p).toFixed(2);
    vulDiagram(waarde);
    koers1 = waarde;
    console.log(waarde);
    koersBTC.innerText = waarde;
    koersBTC.style.color = !laatsteWaarde || laatsteWaarde === waarde ? divaGoud : waarde > laatsteWaarde ? divaGroen : divaRood;
    laatsteWaarde = waarde;
}

// DECLARATIE KLEUREN
const divaRoze = '#f27ddd';
const divaGold = '#ffc95f';
const divaGroen = '#44c26e';
const divaRood = '#E34232';
const divaGoud = '#ffc95f';

// DECLARATIE DAGEN
const vandaag = new Date();
const gisteren = new Date();
const eergisteren = new Date();
const vierDagen = new Date();
const vijfDagen = new Date;
const zesDagen = new Date();
const week = new Date();

// DECLARATIE KOERSEN (nog tijdelijke met harde data)
let koers1 = 36403.7;
let koers2 = 36800.4;
let koers3 = 36950.6;
let koers4 = 36686.3;
let koers5 = 36269.5;
let koers6 = 35075.2;
let koers7 = 36475.5;
let laatsteWaarde = null;

// DECLAREREN LABELS VOOR GRAFIEK
const labels = [
    week.getDate(),
    zesDagen.getDate(),
    vijfDagen.getDate(),
    vierDagen.getDate(),
    eergisteren.getDate(),
    gisteren.getDate(),
    vandaag.getDate(),
];

// DECLAREREN DATA VOOR GRAFIEK
let data = {
    labels: labels,
    datasets: [{
        label: 'dagkoers',
        backgroundColor: divaRoze,
        borderColor: divaRoze,
        data: [koers7, koers6, koers5, koers4, koers3, koers2, koers1],
    }, {
        label: 'huidige koers',
        backgroundColor: divaGold,
        borderColor: divaGold,
        data: [koers1, koers1, koers1, koers1, koers1, koers1, koers1   ],
    }]
};

// VERWIJZINGEN
const buttonKoersInformatie = document.getElementById('buttonKoersInformatie');
const select = document.getElementById("dropdownKoers");
const dropKoersContent = document.getElementById("dropKoersContent")
const koersGrafiek = document.getElementById('koersGrafiek');
const dropdowns = document.getElementsByClassName("dropdownKoers-content");

// EVENT-LISTENERS
buttonKoersInformatie.addEventListener('click', naarLoginScherm);

// TERUG NAAR LOGINSCHERM
function naarLoginScherm() {
    window.location.href = "index.html";
}

// BEREKENEN ACTUELE DAGEN
gisteren.setDate(gisteren.getDate() - 2);
eergisteren.setDate(eergisteren.getDate() - 3);
vierDagen.setDate(vierDagen.getDate() - 4);
vijfDagen.setDate(vijfDagen.getDate() - 5);
zesDagen.setDate(zesDagen.getDate() - 6);
week.setDate(week.getDate() - 7);

// CONFIGURATIE VAN DE GRAFIEK
const config = {
    type: 'line',
    data: data,
    options: {
    }
};

// Functie voor het tonen van de dropDown
function myFunction() {
    dropKoersContent.classList.toggle("show");
}

// Vullen van diagram met cryptoInformatie
function vulDiagram(waarde) {
    koers1 = waarde;
    koers2 = waarde;
}

// Functie voor het sluiten van de dropDown
window.onclick = function(event) {
    if (!event.target.matches('.dropKoers')) {
        let i;
        for (i = 0; i < dropdowns.length; i++) {
            const openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
}

// DECLAREREN GRAFIEK MYCHART
const myChart = new Chart(koersGrafiek, config);
