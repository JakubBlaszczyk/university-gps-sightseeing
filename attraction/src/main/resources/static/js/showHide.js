$(document).ready(function(){
    var retry = sessionStorage.getItem("retry");
    console.log(retry);

    if ($.cookie('access_token') != null) {
        sessionStorage.setItem("retry", 0);
        $('.login-panel').hide();
        $('.logout-panel').show();
        $('.error-message').hide();
    }
    else {
        $('.login-panel').show();
        $('.logout-panel').hide();
        if (retry == 1){
            $('.error-message').show();
        }
        else {
            $('.error-message').hide();
        }
    }

    $('.logout-button').click(function (){
        $.removeCookie('access_token');
        sessionStorage.setItem("retry", 0);
        location.reload();
    });

    $('.login-button').click(function (){
        retry = 1;
        sessionStorage.setItem("retry", retry);
        setTimeout(() => {location.reload();}, 1000);
    });
});