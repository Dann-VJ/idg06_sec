/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
document.addEventListener("DOMContentLoaded", function (event) {
    $("div.overout")
            .on("mouseover", function () {

                $(this).find("span").text("¿Estas seguro que deseas publicar?");
            })
            .on("mouseout", function () {
                $(this).find("span").text("Preciona el botón para publicar");
            });

    $("#btnGuardaPublicacion").on("click", function (e) {

        e.preventDefault();

        $("#errordatos").hide();

        texto = $("#textoPublicacion").val();

        var imagen = $("#imagen")[0].files[0];


        console.log("texto ", texto, "imagen ", imagen);

        if ("" === texto.trim() || undefined == imagen) {

            console.log("Debes intrpducir los datos");

            $('#errordatos').show();
            $('#errordatos').html("debes introducir todos los datos");

        } else {
            console.log("enviando Formulario");
            $("#file").trigger("submit");

        }
    });
});

function reloadPage() {
    setTimeout(function () {
        console.log("Hola Mundo");
    }, 2000);
}


