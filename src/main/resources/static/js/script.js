"use strict";

// Pagina verversen bij klik op #logoDigivault, verbergen van andere lagen
document.getElementById("logoDigivault").addEventListener("click", verversPagina);

function verversPagina() {
    location.reload();
}
//vanuit Login Scherm naar Wachtwoord vergeten pagina bij klik op #naarWachtwoordVergeten
document.getElementById("naarWachtwoordVergeten").addEventListener("click",toonWachtwoordVergeten);

function toonWachtwoordVergeten(){
    document.getElementById("wachtwoordVergetenLaag").style.display = "block";

}

//vanuit WachtwoordVergetenLaag naar login en registreren
//TERUG NAAR INLOG WERK NIET
document.getElementById("naarLoginPagina").addEventListener("click", toonLoginPagina);
document.getElementById("naarRegistreren1").addEventListener("click", toonRegistratieLaag);

function toonLoginPagina(){
    document.getElementById("loginPagina").style.display = "block";
}

// Check op overkomen van wachtwoorden in wachtwoordVergetenLaag
//als ik deze aan zet werkt het registreren knopje vanaf het inlog scherm niet meer...
/*$('#wachtwoord_nieuw, #wachtwoord_herhaal').on('keyup', function () {
    if ($('#wachtwoord_nieuw').val().length === 0 || $('#wachtwoord_check').val().length === 0) {
        $('#wachtwoordInfo').html('').css('color', 'black');
    } else if ($('#wachtwoord_nieuw').val() === $('#wachtwoord_check').val()) {
        $('#wachtwoordInfo').html('komt overeen').css('color', 'green');
    } else
        $('#wachtwoordInfo').html('niet identiek').css('color', '#C0392B');
});*/



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
        $('#wachtwoordCheckRegistratie').html('').css('color', 'black');
    } else if ($('#wachtwoord_reg').val() === $('#wachtwoord_check').val()) {
        $('#wachtwoordCheckRegistratie').html('komt overeen').css('color', 'green');
    } else
        $('#wachtwoordCheckRegistratie').html('niet identiek').css('color', '#C0392B');
});