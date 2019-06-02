window.onload = function(){
    getClients()
    
}
function getClients() {
    async function fetchAsync() {
        const render = document.getElementById("clientTableBody");
        let txt = "";
        const response = await fetch('http://localhost:8080/api/insurer/getClients',{
            headers: {'Content-Type': 'application/json'},
            credentials: "include",
            method: 'GET'
            });
        const data = await response.json();
        console.log(data);
        var clients = data.clients;
        var insurances = data.insurances;
        var index = 0;
        for (const client of clients) {
            txt += "<tr><td>" + client.name + "</td><td>" + insurances[index].packageInsurer.description + "</td>";
            txt += "<td>" + checkStatus(insurances[index].active, insurances[index].rejected) + "</td>";
            txt += "<td><button class='btn btn-primary'><i class='far fa-eye'></i></button><button class='btn btn-primary' onclick = 'confirmValidation()'>";
            txt += "<i class='fas fa-check'></i></button><button class='btn btn-primary' onclick = 'confirmRejection()'><i class='fas fa-minus-circle'></i></button></td>";
            txt += "<td style = 'display:none'>" + insurances[index].idInsurance + "</td></tr>"
            index += 1;
        }
        render.innerHTML = txt;
    }
        //chama a função fetchAsync()
        fetchAsync().then(function(data){
        console.log('ok');
        }).catch(function(reason){
            console.log(reason.message);
        });
}

function validateInsurance(id){

    data = {id}
    fetch('http://localhost:8080/api/insurer/validateInsurance',{
        headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
        method: 'PUT',
        credentials: 'include',
        body: JSON.stringify(data)
    })
    .then(function (response) {
        if (!response.ok) {
            alertify.notify('Houve um erro na validação!', 'error', 5, function(){  console.log('dismissed'); });
            if (response.status === 409) {
            } else {
            throw Error(response.statusText);
            }
        } else {
            alertify.notify('Seguro validado com sucesso!', 'success', 5, function(){  console.log('dismissed'); });
            
            
            }
        return response.json();
    })
    .then(function (result) {
        console.log(result);
        
    })
    .catch (function (error) {
        console.log('Request failed', error);
    });
}

function rejectInsurance(id){

    data = {id}
    fetch('http://localhost:8080/api/insurer/rejectInsurance',{
        headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
        method: 'PUT',
        credentials: 'include',
        body: JSON.stringify(data)
    })
    .then(function (response) {
        if (!response.ok) {
            alertify.notify('Houve um erro na validação!', 'error', 5, function(){  console.log('dismissed'); });
            if (response.status === 409) {
            } else {
            throw Error(response.statusText);
            }
        } else {
            alertify.notify('Seguro validado com sucesso!', 'success', 5, function(){  console.log('dismissed'); });
            
            
            }
        return response.json();
    })
    .then(function (result) {
        console.log(result);
        
    })
    .catch (function (error) {
        console.log('Request failed', error);
    });
}

function checkStatus(active, rejected){
    if(active == true && rejected == false){
        return "Ativo";
    }else if(active == false && rejected == true){
        return "Rejeitado";
    }else if(active == false && rejected == false){
        return "Pendente"
    }else if(active == true && rejected == true){
        return "Erro!";
    }
}

function confirmValidation(){
    var table = document.getElementById("clientTable");
    var id;
    for(var i = 0; i<table.rows.length; i++){
        table.rows[i].onclick = function(){
            id = this.cells[4].innerHTML;
            console.log(id);
            swal({
                title: "Atenção!",
                text: "Tem a certeza que quer validar este seguro?!",
                icon: "warning",
                buttons: ["Cancelar", true],
                dangerMode: true,
              })
              .then((willDelete) => {
                if (willDelete) {
                    validateInsurance(id);
                }
              });
            
        }
    }
    
}

function confirmRejection(){
    var table = document.getElementById("clientTable");
    var id;
    for(var i = 0; i<table.rows.length; i++){
        table.rows[i].onclick = function(){
            id = this.cells[4].innerHTML;
            console.log(id);
            swal({
                title: "Atenção!",
                text: "Tem a certeza que quer rejeitar este seguro?!",
                icon: "warning",
                buttons: ["Cancelar", true],
                dangerMode: true,
              })
              .then((willDelete) => {
                if (willDelete) {
                    rejectInsurance(id);
                }
              });
            
        }
    }
}