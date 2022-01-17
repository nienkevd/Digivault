'use strict';

// RELATIVE PATH URL FETCH
const domainArray = location.origin.split(':');
const urlLead = domainArray[0] + ':' + domainArray[1] + ':8080/';
const url = urlLead + 'financieeloverzicht/10';

// VERWIJZINGEN
const iban = document.getElementById("iban");
const saldo = document.getElementById("saldo");
const tableBody = document.querySelector("#fi-table > tbody");


fetch(url, {
    method: 'GET',
    headers: {
        'Authorization': localStorage.getItem("token"),
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
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
        vulPagina(json)
    )
    .catch((err) => {
        console.log(err);
    });

function vulPagina(json) {
    // toon IBAN van gebruiker
    iban.append(json.iban);
    // toon saldo van gebruiker
    saldo.append(json.saldo);

    // loop door portefeuilleitems en vul tabel
    for (let i = 0; i < 20; i++) {
        const tr = document.createElement('tr');
            const td = document.createElement('td');
            const td2 = document.createElement('td');
            const td3 = document.createElement('td');
            const td4 = document.createElement('td');
            const td5 = document.createElement('td');
            td.textContent = json.assetMetAantal[i].naam;
            td2.textContent= json.assetMetAantal[i].afkorting;
            td3.textContent= json.assetMetAantal[i].aantal;
            td4.textContent= json.assetMetAantal[i].dagkoers;
            const aantal = parseFloat(td3.textContent).toFixed(2);
            const dagkoers = parseFloat(td4.textContent).toFixed(2);
            const waarde = (aantal * dagkoers).toFixed(2).toString();
            td5.textContent = ('€ ' + waarde + ',-');
            tr.appendChild(td);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(td4);
            tr.appendChild(td5);
        tableBody.appendChild(tr);
    }
}





