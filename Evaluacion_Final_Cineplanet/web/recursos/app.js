"use strict";
// Al recuperar una pagina desde el historial, volver a comprobar la sesion en el servidor.
window.addEventListener("pageshow", function (evento) {
    if (evento.persisted) window.location.reload();
});
document.addEventListener("DOMContentLoaded", function () {
    var boton = document.getElementById("imprimir");
    if (boton) boton.addEventListener("click", function () { window.print(); });
});
