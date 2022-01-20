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
    transactie.addEventListener("click",window.location.href = "transactie.html");
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
    // UITLOGGEN EN TRANSACTIE BUTTON
    document.getElementById('naarTransactiePagina').addEventListener('click', toonTransactiePagina);
    document.getElementById('naarLoginPagina').addEventListener('click', toonLoginPagina);

    function toonTransactiePagina() {
        window.location.href = "transactie.html";
    }

    function toonLoginPagina() {
        window.location.href = "index.html";
    }

    //ROW SORTING...Nienke, 20-01-2022
    /**
     * Sorts a HTML table.
     *
     * @param {HTMLTableElement} table The table to sort
     * @param {number} column The index of the column to sort
     * @param {boolean} asc Determines if the sorting will be in ascending
     */
    function sortTableByColumn(table, column, asc = true) {
        const dirModifier = asc ? 1 : -1;
        const tBody = table.tBodies[0];
        const rows = Array.from(tBody.querySelectorAll("tr"));

        //Sort each row
        const sortedRows = rows.sort((a,b) => {
            const aColText = a.querySelector(`td:nth-child(${ column + 1 })`).textContent.trim();
            const bColText = b.querySelector(`td:nth-child(${ column + 1 })`).textContent.trim();

            return aColText > bColText ? (1 * dirModifier) : (-1 * dirModifier);
            //console.log(sortedRows); test, zo kan je zien dat ze in de consol op volgorden staan
            //nu moeten ze alleen nog in de tabel op volgorden komen
        });
        //remove all existing TRs from table (om ze te verwijderen zodat je ze weer in kan voegen op volgorde
        while (tBody.firstChild) {
            tBody.removeChild(tBody.firstChild);
        }
        //readd newly sortet rows
        tBody.append(...sortedRows);

        //remember how the colom is sorted now, geeft nu aan de header een klasse mee in HTML
        //die veranderd van desc naar asc
        table.querySelectorAll("th").forEach(th => th.classList.remove("th-sort-asc", "th-sort-desc"));
        table.querySelector(`th:nth-child(${ column + 1})`).classList.toggle("th-sort-asc", asc);
        table.querySelector(`th:nth-child(${ column + 1})`).classList.toggle("th-sort-desc", !asc);
    }
    //sortTableByColumn(document.querySelector("table"), 2, true);
    // deze kaat over de afkortingen van crypto namen
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







