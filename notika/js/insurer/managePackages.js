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
        var indexPopupEdit = 0;
        var indexPopupDelete = 0;
        var indexPopupCoverages = 10;
        //percorrer a variável users e por cada user cria a linha da tabela com os dados presentes
        for (const package of packages) {
            let coverages = package.coverages;
            txt += "<tr><td>" + package.description + "</td><td>" + package.basePrice + "</td><td>"; 
            txt += "<a style='float: right' class='btn btn-info notika-btn-info waves-effect' href='#popup"+ indexPopupEdit + "'><i class='glyphicon glyphicon-edit'></i> Editar</a>"
            txt += "<div id = 'popup" + indexPopupEdit + "'class = 'overlay'><div class='popup'><a class='close' href='#'>&times;</a>";
            txt += "<form onsubmit='confirmation(event,this)'><label for='description'><i class='fas fa-file-signature'></i> Descrição do pacote &nbsp</label><input type ='text' id = 'description' value ='" + package.description + "'>"
            txt += "<label for='basePrice'><i class='fas fa-money-bill'></i>&nbsp Preço base &nbsp</label><input type ='text' id = 'basePrice' value ='" + package.basePrice + "'><p></p>"
            txt += "<label for='fire'>Coberturas &nbsp</label><input type='checkbox' name= 'fire' id = 'fire' value='fire'> Incêndio &nbsp"
            txt += "<input type='checkbox' id = 'flood' name = 'flood' value='flood'> Inundação &nbsp";
            txt += "<input type='checkbox' id = 'naturalCauses' value='natural_causes' name='naturalCauses'> Causas naturais &nbsp";
            txt += "<input type='hidden' name = 'idPackage' id = 'idPackage' value = '"+ package.idPackage + "'>"
            txt += "<button type='submit' class ='btn btn-success notika-btn-success waves-effect'><i class='fas fa-check'></i>Confirmar</button></form>"
            txt += "</div></div>"
            indexPopupEdit += 1;
            txt += "<a class='btn btn-success notika-btn-success waves-effect' style='float: right' href='#popup"+ indexPopupCoverages + "'><i class='far fa-eye'></i> Ver coberturas</a></td>";
            txt += "<div id = 'popup" + indexPopupCoverages + "'class = 'overlay'><div class='popup'><a class='close' href='#'>&times;</a>";
            for(const coverage of coverages){

                txt += "&nbsp" + checkCoverage(coverage.name) + "&nbsp";
            }
            indexPopupCoverages += 1;
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
    var coverages = [];

        if(form.fire.checked == true){
            coverages.push(form.fire.value);
        }

        if(form.flood.checked == true){
            coverages.push(form.flood.value);
        }
        if(form.naturalCauses.checked == true){
            coverages.push(form.naturalCauses.value);
        }

    var basePrice = form.basePrice.value;
    var description = form.description.value;
    var idPackage = form.idPackage.value;


    var data = {
        coverages,basePrice,description
    }
   
    updatePackage(data, idPackage);

}

function addPackage(){

    var basePrice = document.getElementById("basePrice").value;
    var description = document.getElementById("description").value;
    var coverages = [];
   
    if(document.getElementById("flood").checked == true){
        coverages.push("flood");
    }
     if(document.getElementById("fire").checked == true){
        coverages.push("fire");
     }
     if(document.getElementById("natural_causes").checked == true){
        coverages.push("natural_causes")
}

    var data = {basePrice,description,coverages};
    console.log(data);

    fetch('http://localhost:8080/api/insurer/addPackage',{
        headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
        method: 'POST',
        credentials: 'include',
        body: JSON.stringify(data)
        })
        .then(function (response) {
            if (!response.ok) {
                alertify.notify('Houve um erro na adição do pacote!', 'error', 5, function(){  console.log('dismissed'); });

            } else {
                alertify.notify('Pacote adicionado com sucesso!', 'sucess', 5, function(){  console.log('dismissed'); });
            }
            return response.json();
        })
        .then(function (result) {
        console.log(result);
        refresh();
        })
        .catch (function (error) {
            console.log('Request failed', error);
        });

}

function confirmDelete(button){
    var id = button.value;
    console.log(id);
     swal({
        title: "Atenção!",
        text: "Tem a certeza que quer remover este pacote?!",
        icon: "warning",
        buttons: ["Cancelar", true],
        dangerMode: true,
        })
        .then((willDelete) => {
        if (willDelete) {
        deletePackage(id);
            }
            });
}

function deletePackage(id){

    fetch('http://localhost:8080/api/insurer/deletePackage/' + id,{
        headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
        method: 'DELETE',
        credentials: 'include',
    })
    .then(function (response) {
        if (!response.ok) {
            alertify.notify('Houve um erro a eliminar o pacote!', 'error', 5, function(){  console.log('dismissed'); });
            if (response.status === 409) {
            } else {
            throw Error(response.statusText);
            }
        } else {
            alertify.notify('Pacote eliminado com sucesso!', 'success', 5, function(){  console.log('dismissed'); });
            
            }
        return response.json();
    })
    .then(function (result) {
        console.log(result);
        refresh();
    })
    .catch (function (error) {
        console.log('Request failed', error);
    });
}

function updatePackage(data, id){
    console.log(data);
    console.log(id);
    fetch('http://localhost:8080/api/insurer/updatePackage/' + id,{
        headers: {'Content-Type': 'application/json'},
        method: 'PUT',
        credentials : "include",
        body: JSON.stringify(data)
        }).then(function(response){
            if (!response.ok) {
                swal("Erro!", "Tente novamente!", "error");
            } else {
                swal("Alterado com sucesso!", "", "success");
                refresh();
                
                }
        }).then(function (result) {
                    console.log(result);
                    refresh();
                }).catch(function (err) {
                        console.log(err);
                            });
}