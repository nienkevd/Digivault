"use strict";

/*Info voor team:
* het tonen/verbergen van een laag kan op twee verschillende manieren: ik heb optie 2 gebruikt.
* (1) met onclick methode in element en document.getElementById("registratieLaag").style.display = "none" [OF] "block";
* (2) met jQuery: je geeft in script verwijzing naar #id in function, en hoeft geen methode meer toe te voegen aan element zelf
* jQuery is een JS-library die veel gebruikt wordt en allerlei zaken rondom AJAX en DOM-manipulatie helpt versimpelen
* */


// Verberg registratieLaag na klik op #registreren (tijdelijk)
$(document).ready(function() {
    $("#registreren").click(function() {
        $("#registratieLaag").hide();
    });
});

// Toon registratieLaag na klik op #registratie en #registratieLabel
$(document).ready(function() {
    $("#registratie,#registratieLabel").click(function() {
        $("#registratieLaag").show();
    });
});

// Check op overeenkomst wachtwoorden in registratieLaag
$(document).ready(function() {
    $('#wachtwoord_reg, #wachtwoord_check').on('keyup', function() {
        if ($('#wachtwoord_reg').val().length === 0 || $('#wachtwoord_check').val().length === 0) {
            $('#wachtwoordInfo').html('').css('color', 'black');
        } else if ($('#wachtwoord_reg').val() === $('#wachtwoord_check').val()) {
            $('#wachtwoordInfo').html('komt overeen').css('color', 'green');
        } else
            $('#wachtwoordInfo').html('niet identiek').css('color', '#C0392B');
    });
});

/*Info voor team:
Ik heb een reCHAPTA v2 aanvraag gedaan voor ons domein team-4, zie developers.google.com/recaptcha:
- site key:   6Ld85NcdAAAAALDUx4Wl943frenGbzW_7Zx4QQDH
- secret key: 6Ld85NcdAAAAAJ8m6eosIuvmNPbB58zG3Tv8hz6v
*/
