'use strict';

// JavaScript voor het aanvullen van adresgegevens in de registratieLaag
// Auteur: Erwin, studentnummer 500889293

// EVENT-LISTENERS
document.querySelector('#postcode').addEventListener('focusout', verkrijgAdresGegevens);
document.querySelector('#huisnummer').addEventListener('focusout', verkrijgAdresGegevens);

// Checkt ingevoerde postcode op regex, en haalt op basis van postcode en huisnummer adresGegevens op
function verkrijgAdresGegevens() {
    let regex = new RegExp(/^[1-9][0-9]{3}[\s]?[A-Za-z]{2}$/i);
    let postcode = document.querySelector('#postcode').value
    let huisnummer = document.querySelector('#huisnummer').value

    if(regex.test(postcode) && huisnummer) {
        let formData = `postcode=${postcode}&number=${huisnummer}`
        fetch("https://postcode.tech/api/v1/postcode?" + formData , {
            headers: {
                'Authorization': 'Bearer f972ed2c-4197-4cde-823b-54cf38520abd',
            },
        })
            .then(response => response.json())
            .then(json => vulAdresGegevens(json));
    }
}

// Vult de velden #woonplaats en #straatnaam met de verkregen gegevens
function vulAdresGegevens(data) {
    let adresGegevens = data;
    document.querySelector('#woonplaats').value = adresGegevens.city;
    document.querySelector('#straatnaam').value = adresGegevens.street;
}
