function showData(response){

    var txt = "";
    var index = 0;
    console.log(response);
    const packages = response.packages;
    const prices = response.finalPrices;
    txt += "<div class= 'container'><div class='row'>";
    console.log(packages[0].description);
    for(const package of packages){
        txt += "<div class='col-md-3 col-sm-6'><div class='serviceBox'><div class='service-icon'><i class='fa fa-globe'></i></div>";
        txt += "<h3 class='title'>" + package.description + "</h3><p class='description'>Preço base: " + package.basePrice + "€</p>";
        var coverages = package.coverages;
        txt += "<p class='description'>Coberturas: "
        for(const coverage of coverages){
            txt += "&nbsp" + checkCoverage(coverage.name) + "&nbsp";
        }
        txt += "</p><p class = 'description'>O seu preço: "+ Math.round(prices[index] * 100)/100 + "€</p>"
        txt += "<button class='btnSimulation btn1Comprar'>Comprar</button></div></div>"
        index += 1;
    }

    txt += "</div></div><p><br><br></p>"
    var simulationForm = document.getElementById("simulationForm");
    document.getElementById("info").innerHTML = "Simulação feita com sucesso! Escolha dos pacotes!";
    simulationForm.innerHTML = txt;
}

function simular(){

    var names = [];
    var select = document.getElementById("coberturas");
    var options = select && select.options;
    var opt;
    for (var i=0, iLen=options.length; i<iLen; i++) {
      opt = options[i];
  
      if (opt.selected) {
        names.push(opt.value || opt.text);
      }
    }
    var morada = document.getElementById("morada").value;
    var ano = document.getElementById("ano").value;
    var area = document.getElementById("area").value;
    var capitalImovel = document.getElementById("capitalImovel").value;
    var owner = document.getElementById("owner").value;
    var prevention = document.getElementById("prevention").value;
    var solarPanels = document.getElementById("solarPanel").value;
    var topologia = document.getElementById("topologia").value;

    var data = {
        names : names,
        morada : morada,
        area : area,
        ano : ano,
        capitalImovel : capitalImovel,
        owner : owner,
        solarPanels : solarPanels,
        prevention : prevention,
        topologia : topologia
    }

    fetch('http://localhost:8080/simulator/execute/noDeal',{
        headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
        method: 'POST',
        credentials: 'include',
        body: JSON.stringify(data)
    })
    .then(function (response) {
        if (!response.ok) {
            alertify.notify('Houve um erro na simulação!', 'error', 5, function(){  console.log('dismissed'); });
            if (response.status === 409) {
            } else {
            throw Error(response.statusText);
            }
        } else {
            alertify.notify('Simulação feita com sucesso!', 'success', 5, function(){  console.log('dismissed'); });
            
            
            }
        return response.json();
    })
    .then(function (result) {
        console.log(result);
        showData(result);
        
    })
    .catch (function (error) {
        console.log('Request failed', error);
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
