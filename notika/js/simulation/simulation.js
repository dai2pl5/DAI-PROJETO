function showData(response, afterSim){

    var txt = "";
    var index = 0;
    console.log(response);
    const packages = response.packages;
    const prices = response.finalPrices;
    const insurerNames = response.insurerNames;
    const idInsurers = response.idInsurer;
    txt += "<div class= 'container'><div class='row'>";
    for(const package of packages){
        txt += "<div class='col-md-3 col-sm-6'><div class='serviceBox'><div class='service-icon'><i class='fa fa-globe'></i></div>";
        txt += "<h3 class='title'>" + package.description + "</h3><h3 class='title'>" + insurerNames[index] + "</h3><p class='description'>Preço base: " + package.basePrice + "€</p>";
        var coverages = package.coverages;
        txt += "<p class='description'>Coberturas: "
        for(const coverage of coverages){
            txt += "&nbsp" + checkCoverage(coverage.name) + "&nbsp";
        }
        txt += "</p><p class = 'description'>O seu preço: "+ roundNumber(prices[index]) + "€</p>";
        txt += "<form onsubmit = 'confirmation(event,this)'><input type = 'hidden' name = 'formMorada' style = 'display : none;' value= '  " + afterSim.morada + "'></input>";
        txt += "<input type = 'hidden' name = 'formAno' style = 'display : none;' value = ' " + afterSim.ano + "'></input>";
        txt += "<input type = 'hidden' name = 'formArea' style = 'display : none;' value =  '" + afterSim.area + "'></input>";
        txt += "<input type = 'hidden' name = 'formCapitalImovel' style = 'display : none;' value =  '" + afterSim.capitalImovel + "'></input>";
        txt += "<input type = 'hidden' name = 'formOwner' style = 'display : none;' value =  '" + afterSim.owner + "'></input>";
        txt += "<input type = 'hidden' name = 'formPrevention' style = 'display : none;' value = '" + afterSim.prevention + "'></input>";
        txt += "<input type = 'hidden' name = 'formSolarPanels' style = 'display : none;' value =  '" + afterSim.solarPanels + "'></input>";
        txt += "<input type = 'hidden' name = 'formTopologia' style = 'display : none;' value =  '" + afterSim.topologia + "'></input>";
        txt += "<input type = 'hidden' name = 'formPackage' style = 'display : none;' value =  '" + package.idPackage + "'></input>";
        txt += "<input type = 'hidden' name = 'formPrice' style = 'display : none;' value =  '" + roundNumber(prices[index]) + "'></input>";
        txt += "<input type = 'hidden' name = 'formIdInsurer' style = 'display : none;' value =  '" + idInsurers[index] + "'></input>"
        txt += "<button class='btnSimulation btn1Comprar'>Comprar</button>";
        txt += "</form></div></div>";
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
    var afterSim = {
        morada,ano,area,capitalImovel,owner, prevention, solarPanels, topologia
    }
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
        showData(result,afterSim);
        
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

function confirmation(e,form){
    e.preventDefault();
    var data = {
        morada : form.formMorada.value,
        area : form.formArea.value,
        ano : form.formAno.value,
        capitalImovel : form.formCapitalImovel.value,
        owner : form.formOwner.value,
        solarPanels : form.formSolarPanels.value,
        prevention : form.formPrevention.value,
        topologia : form.formTopologia.value,
        price : form.formPrice.value,
        idPackage : form.formPackage.value,
        idInsurer : form.formIdInsurer.value    
    }

    swal({
        title: "Atenção!",
        text: "Tem a certeza que quer este pacote?!",
        icon: "warning",
        buttons: ["Cancelar", true],
        dangerMode: true,
      })
      .then((willDelete) => {
        if (willDelete) {
            buyInsurance(data);
        }
      });
      
  
    
}

function roundNumber(num){

    var finalNumber = Math.round(num * 100)/100;
    return finalNumber;

}

function buyInsurance(data){
    
    fetch('http://localhost:8080/user/insurance/buyInsurance',{
        headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
        method: 'POST',
        credentials: 'include',
        body: JSON.stringify(data)
        })
        .then(function (response) {
            if (!response.ok) {
                alertify.notify('Houve um erro na compra do seu seguro...', 'error', 5, function(){  console.log('dismissed'); });
                if (response.status === 409) {
                } else {
                throw Error(response.statusText);
                }
            } else {
                swal("Sucesso! Aguarde a confirmação da seguradora.", {
                    icon: "success",
                  });
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