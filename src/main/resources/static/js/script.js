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

// Vergelijkt values uit #wachtwoord_reg en #wachtwoord_check uit registratielaag en geeft in #wachtwoordCheckRegistratie
// weer of de wachtwoorden al dan niet overeen komen
document.getElementById("wachtwoord_reg").addEventListener("keyup", checkOvereenkomenWachtwoorden);
document.getElementById("wachtwoord_check").addEventListener("keyup", checkOvereenkomenWachtwoorden);

function checkOvereenkomenWachtwoorden() {
    let identiek = `komt overeen`;
    let verschil = `niet identiek`;
    let wachtwoord_reg = document.getElementById("wachtwoord_reg");
    let wachtwoord_check = document.getElementById("wachtwoord_check");
    let wachtwoord_info = document.getElementById("wachtwoordCheckInformatie");

    if (wachtwoord_reg.value.length === 0 || wachtwoord_check.value.length === 0) {
        wachtwoord_info.innerHTML = "";
        wachtwoord_info.style.color = "black";
    } else if (wachtwoord_reg.value !== wachtwoord_check.value) {
        wachtwoord_info.innerHTML = verschil;
        wachtwoord_info.style.color = "var(--divaRood)";
    } else if (wachtwoord_reg.value === wachtwoord_check.value) {
        wachtwoord_info.innerHTML = identiek;
        wachtwoord_info.style.color = "var(--divaGroen)";
    }
}
