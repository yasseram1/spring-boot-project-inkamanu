document.addEventListener('DOMContentLoaded', function () {
    var btnCerrar = document.getElementById("btn-cerrar");
    var toast = document.getElementById("toast")

    btnCerrar.addEventListener('click', () => {
        toast.style.display = "none";
    })

    setTimeout(function () {
        toast.style.display = "none";
    }, 4900);
})