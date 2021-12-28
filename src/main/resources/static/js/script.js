"use strict";

<!-- Tijdelijk javascript om de registratieLaag te tonen -->
function on() {
    document.getElementById("overlay").style.display = "block";
}
function off() {
    document.getElementById("overlay").style.display = "none";
}

<!-- Check op overeenkomen wachtwoord in registratieLaag -->
$('#wachtwoord_reg, #wachtwoord_check').on('keyup', function () {
    if ($('#wachtwoord_reg').val().length === 0 || $('#wachtwoord_check').val().length === 0) {
        $('#wachtwoordInfo').html('').css('color', 'black');
    } else if ($('#wachtwoord_reg').val() === $('#wachtwoord_check').val()) {
        $('#wachtwoordInfo').html('komt overeen').css('color', 'green');
    } else
        $('#wachtwoordInfo').html('niet identiek').css('color', '#C0392B');
});
