// voorbeeld:

// https://stackoverflow.com/questions/68389117/using-fetch-api-to-create-a-login-form
//
// const form = {
//     emailadres: document.getElementById('emailadres_veld'),
//     wachtwoord: document.getElementById('wachtwoord_veld'),
//     submit: document.getElementById('btnLogin'),
//
// };
window.onload = function () {

    const emailadres = document.getElementById('emailadres_veld').value
    const wachtwoord = document.getElementById('wachtwoord_veld').value
    const data = {accountId: 0, 'emailadres': emailadres, 'wachtwoord': wachtwoord}; // javascript object
    const submitButton = document.getElementById('btnLogin')


    submitButton.addEventListener('click', login);

    function login() {
        // submitButton.addEventListener("click", (e) => {
        //     e.preventDefault();
            const login = 'http://localhost:8080/login';

            fetch(login, {
                method: 'POST',
                headers: {
                    // Accept: "application/json, text/plain, */*",
                    // "Content-Type": "application/json",
                },
                body: JSON.stringify(data)
            })
                .then((response) => response.json())
                .then((data) => {
                    console.log(data);
                    // code here //
                    if (data.error) {
                        alert("Error Password or Username"); /*displays error message*/
                    } else {
                        body.style.backgroundColor = 'white';
                        // window.open(
                        //     "target.html"
                        // ); /*opens the target page while Id & password matches*/
                    }
                })
                .catch((err) => {
                    console.log(err);
                });
        // });
    };
}



// const emailadres = document.getElementById('emailadres_veld').value
// const wachtwoord = document.getElementById('wachtwoord_veld').value
// const data = {accountId: 0, 'emailadres': emailadres, 'wachtwoord': wachtwoord}; // javascript object
//
//
// document.querySelector('#btnLogin').addEventListener('click', login);
//
//
// function login() {
//     if (emailadres != null && wachtwoord != null) {
//
//         fetch('localhost:8080/login', {
//             method: 'POST',
//             headers: {
//                 'Accept': 'application/json',
//                 'Content-Type': 'application/json'
//             },
//             body: JSON.stringify(data)  // moet worden omgezet naar een string
//         })
//             .then(response => {
//                     console.log(response)
//                     return response.json()
//                 }
//             )
//             .then(json => {
//                 console.log(json);
//             })
//             .catch((error) => {
//                     console.error('Foutje', error);
//                 }
//             )
//     } else {
//         alert("Vul je emailadres en wachtwoord in!")
//     }
// };
