const domainArray = location.origin.split(':');
const urlLead = domainArray[0] + ':' + domainArray[1] + ':8080/';
const url = urlLead + 'financieeloverzicht';
//asset array in financieeloverzicht json bestand
let assets;
//dropdown menu op transactie pagina
const select = document.getElementById("dropdown")
//hoeveelheid input veld op transactie pagina
const hoeveelheid = document.getElementById("hoeveelheid")
//contant transactie kosten percentage
const percentage = 0.02

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

select.addEventListener("click", (e) => {
    console.log("button")
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
        for (i = 0; i < 20; i++) {
            var option = document.createElement('option');
            option.text = option.value = json.assetMetAantal[i].naam;
            select.add(option, 0);
        }
    }
    select.addEventListener( "change", toonWaarde);

    function toonWaarde() {
        const value = select.options[select.selectedIndex].value;
        const hoeveelheid = document.getElementById("hoeveelheid").value;
        let dagkoers;

        for (i = 0; i < assets.length; i++) {
            if (assets[i].naam == value) {
                dagkoers = assets[i].dagkoers;
                break;
            }
        }
        const waarde = hoeveelheid * dagkoers;
        document.getElementById("waarde").innerText=waarde.toFixed(2);
        const transactiekosten = waarde * percentage;
        document.getElementById("transactiekosten").innerText=transactiekosten.toFixed(2);
    }

    hoeveelheid.addEventListener("input", toonWaarde)

})










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