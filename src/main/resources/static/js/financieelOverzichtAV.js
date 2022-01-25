'use strict';

// RELATIVE PATH URL FETCH
const domainArray = location.origin.split(':');
const urlLead = domainArray[0] + ':' + domainArray[1] + ':8080/';
const url = urlLead + 'financieeloverzicht';

// VERWIJZINGEN
const transactie = document.getElementById("transactie");
const iban = document.getElementById("iban");
const saldo = document.getElementById("saldo");
const tableBody = document.querySelector("#fi-table > tbody");


fetch(url, {
    method: 'POST',
    headers: {
        'Authorization': localStorage.getItem("token"),
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
    },
})
    .then(response => {
        if(response.status === 200){
            return response.json()
        } else {
            throw new Error("Er is iets verkeerd gegaan! " + response.status)
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
        td2.textContent = json.assetMetAantal[i].afkorting;
        td3.textContent = json.assetMetAantal[i].aantal;
        td4.textContent = json.assetMetAantal[i].dagkoers;
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
    // UITLOGGEN EN TRANSACTIE BUTTON EN LOGO(naar login) - Nienke
    document.getElementById('naarTransactiePagina').addEventListener('click', toonTransactiePagina);
    document.getElementById('naarLoginPagina').addEventListener('click', toonLoginPagina);
    //document.getElementById('logoDigivault').addEventListener('click', toonLoginPagina);

    function toonTransactiePagina() {
        window.location.href = "transactie.html";
    }
    function toonLoginPagina() {
        window.location.href = "index.html";
    }

    /**
     * ROW SORTING...Nienke, 20-01-2022
     *
     * @param {HTMLTableElement} table De te sorteren tabel (fi-tabele)
     * @param {number} column Index van de colom te sorteren
     * @param {boolean} asc Bepaald groot naar klein/klein naar groot
     */
    function sortTableByColumn(table, column, asc = true) {
        const dirModifier = asc ? 1 : -1;
        const tBody = table.tBodies[0];
        const rows = Array.from(tBody.querySelectorAll("tr"));

        //Sorteer elke row
        //onderstaan,function moet erin anders sorteert hij bij default alpabetisch
        //arr.sort(function(a, b){return a-b;});
        const sortedRows = rows.sort((a,b) => {
            let aColText = a.querySelector(`td:nth-child(${ column + 1 })`).textContent.trim();
            let bColText = b.querySelector(`td:nth-child(${ column + 1 })`).textContent.trim();
            // naar een float parsen als het een float is zoadat ze niet als string gesorteerd worden
            if (!isNaN(parseFloat(aColText)) && !isNaN(parseFloat(bColText))) {
                aColText = parseFloat(aColText)
                bColText = parseFloat(bColText)
            }

            return aColText > bColText ? (1 * dirModifier) : (-1 * dirModifier);
            //console.log(sortedRows); test, zo kan je zien dat ze in de consol op volgorden staan
            //nu moeten ze alleen nog in de tabel op volgorden komen
        });
        //alle elementen (tr) uit tabel halen om zodat je ze weer toe kan voegen op volgorde
        while (tBody.firstChild) {
            tBody.removeChild(tBody.firstChild);
        }
        //terug plaatsen gesorted rows
        tBody.append(...sortedRows);

        //remember how the colom is sorted now, geeft nu aan de header een klasse mee in HTML
        //die veranderd van desc naar asc
        table.querySelectorAll("th").forEach(th => th.classList.remove("th-sort-asc", "th-sort-desc"));
        table.querySelector(`th:nth-child(${ column + 1})`).classList.toggle("th-sort-asc", asc);
        table.querySelector(`th:nth-child(${ column + 1})`).classList.toggle("th-sort-desc", !asc);
    }
//sortTableByColumn(document.querySelector("table"), 2, true);
// checken of het werkt, zo kan je per row het sorteren aanhalen.
//nu ombouwen naar dat het op eke header gaat.
    document.querySelectorAll(".fi-table th").forEach(headerCell => {
        headerCell.addEventListener("click", () => {
            const tableElement = headerCell.parentElement.parentElement.parentElement;
            const headerIndex = Array.prototype.indexOf.call(headerCell.parentElement.children, headerCell);
            const currentIsAscending = headerCell.classList.contains("th-sort-asc");

            sortTableByColumn(tableElement, headerIndex, !currentIsAscending);
        });
    });


}







