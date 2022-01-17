// RELATIVE PATH URL FETCH
    const domainArray = location.origin.split(':');
const urlLead = domainArray[0] + ':' + domainArray[1] + ':8080/';
const url = urlLead + 'financieeloverzicht/10';

fetch(url, {
    method: 'GET',
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

// document.getElementById('asset').addEventListener('change', function (e) {
//     toonWaarde();
//     toonTransactieKosten();
// })
//
// function toonWaarde (json) {
//     let hoeveelheid = document.getElementById("hoeveelheid").value;
//     let dagkoers = json.dagkoers;
//     const waarde = hoeveelheid;
//     document.getElementById("waarde").append(waarde);
// }
//
// function toonTransactieKosten(json) {
//     const transactiekosten = (waarde * 0.02);
//     document.getElementById("transactiekosten").append(transactiekosten);
// }
//
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