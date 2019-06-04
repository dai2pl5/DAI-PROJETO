window.onload = function(){
    refresh();
}

function refresh() {
    async function fetchAsync() {
        const render = document.getElementById("packagesTableBody");
        let txt = "";
        const response = await fetch('http://localhost:8080/api/insurer/getPackages',{
            headers: {'Content-Type': 'application/json'},
            credentials: "include",
            method: 'GET'
            });
        const result = await response.json();
        var packages = result;
    
        console.log(result);
        var index = 0;
        var indexPopup = 0;
        //percorrer a variável users e por cada user cria a linha da tabela com os dados presentes
        for (const package of packages) {
            let coverages = package.coverages;
            txt += "<tr><td>" + package.description + "</td><td>" + package.basePrice + "</td><td><button type = 'button' onclick= 'passContentDelete()'style='float: right;' class='btn btn-danger notika-btn-danger waves-effect'><i class='glyphicon glyphicon-trash'></i> Eliminar</button>"; 
            txt += "<a style='float: right' class='btn btn-info notika-btn-info waves-effect' href='#popup"+ indexPopup + "'><i class='glyphicon glyphicon-edit'></i> Editar</a>"
            txt += "<div id = 'popup" + indexPopup + "'class = 'overlay'><div class='popup'><a class='close' href='#'>&times;</a>";
            txt += "<form onsubmit='confirmation(e,this)'><label for='description'><i class='fas fa-file-signature'></i> Descrição do pacote</label><input type ='text' id = 'description' value ='" + package.description + "'>"
            txt += "<label for='basePrice'><i class='fas fa-money-bill'></i> Preço base </label><input type ='text' id = 'basePrice' value ='" + package.basePrice + "'>"
            txt += "<label for='fire'> Coberturas </label><input type='checkbox' name= 'fire' id = 'fire' value='fire'> Incêndio<br>"
            txt += "<input type='checkbox' id = 'flood' name = 'flood' value='flood'> Inundação";
            txt += "<input type='checkbox' id = 'naturalCauses' value='natural_causes' name='naturalCauses'> Causas naturais"
            txt += "<button type='button' class ='btn btn-success notika-btn-success waves-effect'><i class='fas fa-check'></i>Confirmar</button></form>"
            txt += "</div></div>"
            indexPopup += 1;
            txt += "<a class='btn btn-success notika-btn-success waves-effect' style='float: right' href='#popup"+ indexPopup + "'><i class='far fa-eye'></i> Ver coberturas</a></td>";
            txt += "<div id = 'popup" + indexPopup + "'class = 'overlay'><div class='popup'><a class='close' href='#'>&times;</a>";
            for(const coverage of coverages){

                txt += "&nbsp" + checkCoverage(coverage.name) + "&nbsp";
            }
            txt += "</p></div></div></tr>";
            txt += "";
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

function confirmation(e,form){
    e.preventDefault();
    var names = [];
    if(form.fire.checked == true){
        names.push(form.fire.value);
    }

    if(form.flood.checked == true){
        names.push(form.fire.value);
    }
    if(form.naturalCauses.checked == true){
        names.push(form.fire.value);
    }

    var basePrice = form.basePrice.value;
    var description = form.description.value;

    var data = {
        names,basePrice,description
    }
    console.log(data);

}