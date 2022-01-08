$(document).ready(function(){
    var registered = sessionStorage.getItem("registered");

    if(registered == 1) {
        $('.signup-form').hide();
        $('.success-signup').show();
        sessionStorage.setItem("registered", 0);
    }
    else {
        $('.signup-form').show();
        $('.success-signup').hide();
    }

    $('.signup-button').click(function () {
        sessionStorage.setItem("registered", 1);
        setTimeout(() => {location.reload();}, 1000);
    });
});