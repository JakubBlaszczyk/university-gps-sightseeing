$('.u-btn-submit').click(function (){
    setTimeout(() => {location.reload();}, 1000);
});

$(document).ready(function() {

    function readFile() {

        if (this.files && this.files[0]) {

            var FR = new FileReader();

            FR.addEventListener("load", function (e) {
                document.getElementById("b64").value = e.target.result;
            });

            FR.readAsDataURL(this.files[0]);
        }

    }

    document.getElementById("avatar-input").addEventListener("change", readFile);

});