document.querySelector('#postcode').addEventListener('focusout', checkForAddressPart);
document.querySelector('#huisnummer').addEventListener('focusout', checkForAddressPart);

function checkForAddressPart(){
    let regex = new RegExp(/^[1-9][0-9]{3}[\s]?[A-Za-z]{2}$/i);

    // velden moeten niet leeg zijn, liefst regex
    let postcode = document.querySelector('#postcode').value
    let huisnummer = document.querySelector('#huisnummer').value

    // als postcode een geldige postcode is nummer niet leeg, dan
    console.log('pc is valide: ' + regex.test(postcode))

    if(regex.test(postcode) && huisnummer){
        // var data = { postcode: postcode, nr: huisnummer};
        // stuur data als form data ipv json, want backend accepteert alleen form
        let formData = `postcode=${postcode}&number=${huisnummer}` //postcode=1234AB&nr=15
        fetch("https://postcode.tech/api/v1/postcode?" + formData , {
            headers: {
                'Authorization': 'Bearer f972ed2c-4197-4cde-823b-54cf38520abd',
            },
        })
            .then(response => response.json())
            .then(json => processAddress(json))
            .catch((error) => { console.error('Foutje', error) });
    }
}

function processAddress(data){
    document.querySelector('#error').style.display = 'none';
    // haal de data uit de request
    let addressPart = data; // de data is in ASCII format, nu nog naar object omzetten
    // velden vullen
    document.querySelector('#woonplaats').value = addressPart.city; // zonder validatie
    document.querySelector('#straatnaam').value = addressPart.street; // zonder validatie
    // error style weghalen
    document.querySelector('#postcode').classList.remove('error');
    document.querySelector('#huisnummer').classList.remove('error');
}
