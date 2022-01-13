
fetch("http://localhost:8080/financieeloverzicht/10", {
    method: 'GET',
    headers: {
        'Authorization': 'Bearer ' /*+ localStorage.getItem("token")*/,
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
        createTable(json)
    )
    .catch((err) => {
        console.log(err);
    });

function createTable(json) {
    const overzicht = json;

    const iban = document.getElementById("iban");
    iban.append(overzicht.iban);

    const saldo = document.getElementById("saldo");
    saldo.append(overzicht.saldo);

    const tableBody = document.querySelector("#fi-table > tbody");
    console.log(tableBody);
    console.log(json);

    // verwijderd de bestaande data uit de body van de tabel
    // while (tableBody.firstChild) {
    //     tableBody.removeChild(tableBody.firstChild);
    // }

    for (i = 0; i < 20; i++) {
        const tr = document.createElement('tr');
            const td = document.createElement('td');
            const td2 = document.createElement('td');
            const td3 = document.createElement('td');
            const td4 = document.createElement('td');
            td.textContent = overzicht.assetMetAantal[i].naam;
            td2.textContent= json.assetMetAantal[i].afkorting;
            td3.textContent= json.assetMetAantal[i].aantal;
            td4.textContent= json.assetMetAantal[i].dagkoers;
            tr.appendChild(td);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(td4);
        tableBody.appendChild(tr);
    }
}





