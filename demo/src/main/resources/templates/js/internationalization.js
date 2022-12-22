$(document).ready(function() {
    $("#locales").change(function () {
        var selectedOption = $('#locales').val();
        if (selectedOption != ''){
            window.location.replace(window.location.protocol + '//' + window.location.host + window.location.pathname + '?lang=' + selectedOption);
        }
    });
});