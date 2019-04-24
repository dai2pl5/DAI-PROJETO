window.onload = tipoHab();
function tipoHab() {
  document.getElementById("tipo_habitacao").value = "Apartamento"
}
window.onload = coberturas();
function coberturas() {
    //document.getElementById("paredes").setAttribute("selected", "false")
    //document.getElementById("furto").setAttribute("selected", "selected")
    //document.getElementById("tsunami").setAttribute("selected", "selected")
}
function edit() {
    document.getElementById("name").disabled = false
    document.getElementById("local").disabled =  false
    document.getElementById("mail").disabled = false
    document.getElementById("data").disabled = false
    document.getElementById("tipo_habitacao").disabled = false
    document.getElementById("coberturas").disabled = false
}
window.onload = showButton();
function showButton() {
  document.getElementById("edit").style.visibility = "hidden";
}