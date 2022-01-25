'use strict';

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

// DECLARATIE KOERSEN
let koers1 = 31714.99;
let koers2 = 31726.35;
let koers3 = 31722.71;
let koers4 = 31703.04;
let koers5 = 31688.12;
let koers6 = 31686.87;
let koers7 = 31672.63;
let laatsteWaarde = null;

// BEREKENEN ACTUELE DAGEN
gisteren.setDate(gisteren.getDate() - 2);
eergisteren.setDate(eergisteren.getDate() - 3);
vierDagen.setDate(vierDagen.getDate() - 4);
vijfDagen.setDate(vijfDagen.getDate() - 5);
zesDagen.setDate(zesDagen.getDate() - 6);
week.setDate(week.getDate() - 7);

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
        label: 'week',
        backgroundColor: divaRoze,
        borderColor: divaRoze,
        data: [koers7, koers6, koers5, koers4, koers3, koers2, koers1],
    }, {
        label: 'dagkoers',
        backgroundColor: divaGold,
        borderColor: divaGold,
        data: [koers1, koers1, koers1, koers1, koers1, koers1, koers1   ],
    }]
};

// CONFIGURATIE VAN GRAFIEK
const config = {
    type: 'line',
    data: data,
    options: {
        // plugins: {
        //     legend: {
        //         display: false
        //     }
        // }
    }
};

function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
    if (!event.target.matches('.dropbtn')) {
        var dropdowns = document.getElementsByClassName("dropdown-content");
        var i;
        for (i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
}

const select = document.getElementById("dropdownKoers")

// DECLAREREN
const myChart = new Chart(
    document.getElementById('koersGrafiek'),
    config
);



let basisString = 'wss://stream.binance.com:9443/ws/'
let koersString = 'eth'
let valutaString = 'eur'
let eindString = '@trade'
const wsBTC = new WebSocket(basisString + koersString + valutaString + eindString);
const koersBTC = document.getElementById('huidigeKoers');

wsBTC.onmessage = (event) => {
    let stockObject = JSON.parse(event.data)
    let waarde = parseFloat(stockObject.p).toFixed(2);
    vulDiagram(waarde);
    koers1 = waarde;
    console.log(waarde);
    koersBTC.innerText = waarde;
    koersBTC.style.color = !laatsteWaarde || laatsteWaarde === waarde ? divaGoud : waarde > laatsteWaarde ? divaGroen : divaRood;
    laatsteWaarde = waarde;
}

function vulDiagram(waarde) {
    koers1 = waarde;
    koers2 = waarde;
}

document.getElementById('naarAlleKoersen').addEventListener('click', toonAlleKoersen);

function toonAlleKoersen(){
    window.location.href = "koersInformatie.html";
}
