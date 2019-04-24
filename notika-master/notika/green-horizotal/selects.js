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
    document.getElementById("apply").removeAttribute("style")
    document.getElementById("edit").setAttribute("style", "visibility: hidden")
    document.getElementById("apply").setAttribute("style", "float: right")
}