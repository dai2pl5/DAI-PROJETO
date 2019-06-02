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
            txt += "<td><a class='btn btn-primary' href='#popup"+ index + "'><i class='far fa-eye'></i></a><button class='btn btn-primary' onclick = 'confirmValidation(this)' value='" + insurances[index].idInsurance +"'>";
            txt += "<i class='fas fa-check'></i></button><button class='btn btn-primary' onclick = 'confirmRejection(this)' value='"+ insurances[index].idInsurance + "'><i class='fas fa-minus-circle'></i></button>";
            txt += "<div id = 'popup" + index + "'class = 'overlay'><div class='popup'><a class='close' href='#'>&times;</a>";
            txt += "<div class='content'><p>Cliente: " + client.name +  "</p><p>Pacote: " + insurances[index].packageInsurer.description + "</p>";
            txt += "<p>Pacotes: ";
            let coverages = insurances[index].packageInsurer.coverages;
            for(const coverage of coverages){

                txt += "&nbsp" + checkCoverage(coverage.name) + "&nbsp";
            }
            txt += "</p><p>Prémio: " + insurances[index].price + "</p>"
            let house = insurances[index].home;
            txt += "<p>Morada: " + house.morada + "</p>"
            txt += "<p>Area: " + house.area + "</p><p>Ano de construção: " + house.ano + "</p>"
            txt += "<p>Capital do Imovél: " + house.capitalImovel + "</p><p>Proprietário: " + checkOwner(house.owner) + "</p>"
            txt += "<p>Capital dos sistemas de microgeração: " + checkSolarPanels(house.solarPanels) + "</p><p>Meios de prevenção: " + checkPrevention(house.prevention) + "</p>"
            txt += "<p>Topologia : " + house.topologia + "</p>";
            txt += "</div></div></div></td>"; 
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

function confirmValidation(button){
    var id = button.value;
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

function confirmRejection(button){
    var id = button.value;
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

function checkCoverage(name){
    var check = "";
    if(name === "flood"){
        check += "<i class='fas fa-water'></i>&nbspInundação";
        return check;
    }else if(name === "natural_causes"){
        check += "<i class='fas fa-globe-europe'></i>&nbspCausas naturais";
        return check; 
    }else if(name === "fire"){
        check += "<i class='fas fa-fire'></i>&nbspIncêndios";
        return check;
    }
}

function checkOwner(owner){

    if(owner == true){
        return "Sim";
    }else{
        return "Não";
    }
}

function checkSolarPanels(solarPanels){

    if(solarPanels == 0){
        return "Não tem";
    }else{
        return solarPanels;
    }
}

function checkPrevention(prevention){

    if(prevention == true){
        return "Sim";
    }else{
        return "Não";
    }
}