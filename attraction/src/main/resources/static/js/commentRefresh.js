$('.u-btn-1').click(function (){
    setTimeout(() => {location.reload();}, 1000);
});

$(document).ready(function() {

    var longi = document.getElementById("longitude");
    var lati = document.getElementById("latitude");

    $('.location-in-use').hide();

    function getLocation() {
        navigator.geolocation.getCurrentPosition(sendPosition);
        $('.location-in-use').show();
        setTimeout(() => {$('.location-in-use').hide();}, 3000);
    }

    function sendPosition(position) {
        longi.value = position.coords.longitude;
        lati.value = position.coords.latitude;
    }

    document.getElementById("location-form").addEventListener("click", getLocation);
});