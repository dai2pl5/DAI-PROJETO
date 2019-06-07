
window.onload = function(){
    getActiveInsurances();
    mostrarDados();
}

function getActiveInsurances(){

    async function fetchAsync() {
        const response = await fetch('http://localhost:8080/user/insurance/getActiveInsurances',{
        headers: {'Content-Type': 'application/json'},
        credentials: "include",
        method: 'GET',
        });
        const data = await response.json();
        const insurances = data.insurances;
        const insurerNames = data.insurerNames;
        console.log(data);
        showData(insurances, insurerNames);

    }
        //chama a função fetchAsync()
        fetchAsync().then(function(data){
        console.log('ok');
        }).catch(function(reason){
            console.log(reason.message);
        });

}

function showData(insurances, insurerNames){

    var render = document.getElementById("activeInsurances");
    let txt = "";
    var index = 0;
    txt += "<div class= 'container'><div class='row'>";
    for(const insurance of insurances){
        var house = insurance.home;
        var package = insurance.packageInsurer;
        txt += "<div class='col-md-3 col-sm-6'><div class='serviceBox'><div class='service-icon'><i class='fas fa-home'></i></div>";
        txt += "<h3 class='title'>" + package.description + "</h3><h3 class='title'>" + insurerNames[index] + "</h3>";
        txt += "<p>Casa:"+ house.morada +" </p><p class='description'>Preço: " + insurance.price + "€</p>";
        var coverages = package.coverages;
        txt += "<p class='description'>Coberturas: "
        for(const coverage of coverages){
            txt += "&nbsp" + checkCoverage(coverage.name) + "&nbsp";
        }
        
        index += 1;
        txt += "</p><button class='btnSimulation btn1Comprar' onclick = 'sensors(" + insurance.idInsurance + ")'>Ver sensores</button></div></div>"
    }
    txt += "</div></div><p><br><br></p>"
    render.innerHTML = txt;

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

function sensors(id){
    async function fetchAsync() {
        const response = await fetch('http://localhost:8080/sensor/ordem/' + id,{
        headers: {'Content-Type': 'application/json'},
        credentials: "include",
        method: 'GET',
        });
        const data = await response.json();
        
        showSensors(data, id);

    }
        //chama a função fetchAsync()
        fetchAsync().then(function(data){
        console.log('ok');
        }).catch(function(reason){
            console.log(reason.message);
        });

}

function showSensors(data, id){
    var render = document.getElementById("sensors");
    var content = document.getElementById("activeInsurances");
    content.style.display = 'none';
    render.style.visibility = 'visible';
    
    document.getElementById('temperatura').innerHTML = data[1];
    document.getElementById('humidade').innerHTML  = data[0];
    document.getElementById('pessoas').innerHTML  = data[2];
    if(data[4] == "0"){
        console.log("vale");
        document.getElementById("gas").innerHTML = "Niveis estáveis";
    }else{
        document.getElementById("gas").innerHTML = "Niveis nocivos";
    }
    
    if(data[3] == "1"){
        swal({
            title: "Atenção!",
            text: "Casa a ser assaltada",
            icon: "warning",
            buttons: ["Cancelar", true],
            dangerMode: true,
          })
          .then((willDelete) => {
            if (willDelete) {
                setMovimento();
                timer(id, content);
            }
          });
    }else if(data[4] == "1"){
        swal({
            title: "Atenção!",
            text: "Nivel elevado de gases nocivos",
            icon: "warning",
            buttons: ["Cancelar", true],
            dangerMode: true,
          })
          .then((willDelete) => {
            if (willDelete) {
                timer(id, content);
            }
          });
          
    }else if(data[5] == "1"){
        swal({
            title: "Atenção!",
            text: "A sua casa está a inundar!",
            icon: "warning",
            buttons: ["Cancelar", true],
            dangerMode: true,
          })
          .then((willDelete) => {
            if (willDelete) {
                timer(id, content);
            }
          });
        
    }else{
        timer(id, content);
    }
    

}

function timer(id,content){
    if(content.style.display == 'none'){
    setTimeout(function(){
        sensors(id);
    },4000);
    }
}

function setMovimento(){

    fetch('http://localhost:8080/sensor/setMovimento',{
        headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
        method: 'PUT',
        credentials: 'include',
    })
    .then(function (response) {
        if (!response.ok) {
            alertify.notify('Houve um erro!', 'error', 5, function(){  console.log('dismissed'); });
            if (response.status === 409) {
            } else {
            throw Error(response.statusText);
            }
        } else {
            alertify.notify('Sucesso!', 'success', 5, function(){  console.log('dismissed'); });
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

function mostrarDados(){
    var table = document.getElementById("sensorsTableBody");
    async function fetchAsync() {
        const response = await fetch('http://localhost:8080/sensor/getSensores',{
        headers: {'Content-Type': 'application/json'},
        credentials: "include",
        method: 'GET',
        });
        const sensors = await response.json();
        let txt = "";
        for(const sensor of sensors){
            txt += "<tr><td> " + nome(sensor.name) + "</td><td>" + value(sensor.name,sensor.value) + "</td></tr>";

        }

        table.innerHTML = txt;

    }
        //chama a função fetchAsync()
        fetchAsync().then(function(data){
        console.log('ok');
        }).catch(function(reason){
            console.log(reason.message);
        });
    
}

function nome(name){
    if(name == "sensor humidade"){
        return "Sensor de humidade";
    }else if(name == "sensor temperatura"){
        return "Sensor de temperatura";
    }else if(name == "sensor permanência"){
        return "Sensor de permanência";
    }else if(name == "sensor movimento"){
        return "Sensor de movimento";
    }else if(name == "sensor gas"){
        return "Sensor de gas";
    }else if(name == "sensor agua"){
        return "Sensor de água";
    }
}

function value(name, valor){
    if(valor == "0" && name == "sensor gas"){
        return "Niveis estaveis de gases nocivos";
    }else if(valor == "1" && name == "sensor gas"){
        return "Niveis nocivos gases nocivos"
    }else if(name == "sensor temperatura"){
        return "Temperatura da casa: " + valor + "ºC";
    }else if(name == "sensor agua" && valor == "0"){
        return "Normal";
    }else if(name == "sensor agua" && valor == "1"){
        return "Houve uma inundação";
    }else if(name == "sensor movimento"){
        return "O sensor detetou movimento"
    }else if(name == "sensor permanência" && valor == "1"){
        return "A casa foi assaltada";
    }else if(name == "sensor permanência" && valor == "0"){
        return "Alarme está ativo";
    }else if(name == "sensor humidade"){
        return "Nivel de humidade: " + valor;
    }
    
}

