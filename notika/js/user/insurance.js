window.onload = function(){

    getInsurances();
}

function getInsurances(){
    async function fetchAsync() {
        const render = document.getElementById("insuranceTableBody");
        let txt = "";
        const response = await fetch('http://localhost:8080/user/insurance/getInsurances',{
            headers: {'Content-Type': 'application/json'},
            credentials: "include",
            method: 'GET'
            });
        const data = await response.json();
        console.log(data);
        var insurerNames = data.insurerNames;
        var insurances = data.insurances;
        var index = 0;
        for (const insurance of insurances) {
            console.log(insurance.home.morada);
            txt += "<tr><td>" + insurance.home.morada + "</td><td>" + insurance.packageInsurer.description + "</td>";
            txt += "<td>" + checkStatus(insurance.active, insurance.rejected) + "</td>";
            txt += "<td><a class='btn btn-primary' href='#popup"+ index + "'><i class='far fa-eye'></i></a>";
            txt += "<div id = 'popup" + index + "'class = 'overlay'><div class='popup'><a class='close' href='#'>&times;</a>";
            txt += "<div class='content'><p>Seguradora: " + insurerNames[index] +  "</p><p>Pacote: " + insurance.packageInsurer.description + "</p>";
            txt += "<p>Coberturas: ";
            let coverages = insurance.packageInsurer.coverages;
            for(const coverage of coverages){

                txt += "&nbsp" + checkCoverage(coverage.name) + "&nbsp";
            }
            txt += "</p><p>Prémio: " + insurance.price + "</p>"
            let house = insurance.home;
            txt += "<p>Morada: " + house.morada + "</p>"
            txt += "<p>Area: " + house.area + "</p><p>Ano de construção: " + house.ano + "</p>"
            txt += "<p>Capital do Imovél: " + house.capitalImovel + "</p><p>Proprietário: " + checkOwner(house.owner) + "</p>"
            txt += "<p>Capital dos sistemas de microgeração: " + checkSolarPanels(house.solarPanels) + "</p><p>Meios de prevenção: " + checkPrevention(house.prevention) + "</p>"
            txt += "<p>Topologia : " + house.topologia + "</p>";
            txt += "</div></div></div></td>";
            console.log("teste");
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