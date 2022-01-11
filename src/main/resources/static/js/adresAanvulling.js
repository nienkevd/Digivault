document.querySelector('#postcode').addEventListener('focusout', checkForAddressPart);
document.querySelector('#huisnummer').addEventListener('focusout', checkForAddressPart);

function checkForAddressPart() {
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
            .then(json => vulAdresgegevens(json));
    }
}

function vulAdresgegevens(data) {
    let adresGegevens = data;
    document.querySelector('#woonplaats').value = adresGegevens.city;
    document.querySelector('#straatnaam').value = adresGegevens.street;
}
