"use strict";

// Pagina verversen bij klik op #logoDigivault, verbergen van andere lagen
document.getElementById("logoDigivault").addEventListener("click", verbergRegistratieLaag);

function verversPagina() {
    location.reload();
}

// RegistratieLaag tonen bij klik op #welkomsAanbieding en #naarRegistreren
document.getElementById("welkomsAanbieding").addEventListener("click", toonRegistratieLaag);
document.getElementById("naarRegistreren").addEventListener("click", toonRegistratieLaag);

function toonRegistratieLaag() {
    document.getElementById("registratieLaag").style.display = "block";
}

// RegistratieLaag verbergen (tijdelijk nog niet gebruikt)
function verbergRegistratieLaag() {
    document.getElementById("registratieLaag").style.display = "none";
}

// Check op overkomen van wachtwoorden in registratieLaag
$('#wachtwoord_reg, #wachtwoord_check').on('keyup', function () {
    if ($('#wachtwoord_reg').val().length === 0 || $('#wachtwoord_check').val().length === 0) {
        $('#wachtwoordInfo').html('').css('color', 'black');
    } else if ($('#wachtwoord_reg').val() === $('#wachtwoord_check').val()) {
        $('#wachtwoordInfo').html('komt overeen').css('color', 'green');
    } else
        $('#wachtwoordInfo').html('niet identiek').css('color', '#C0392B');
});