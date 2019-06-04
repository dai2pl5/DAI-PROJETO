window.onload = function(){
    refresh2();
}
function refresh2() {
    async function fetchAsync() {
        const render = document.getElementById("housesTableBody");
        let txt = "";
        const response = await fetch('http://localhost:8080/api/user/getHouses/',{
            headers: {'Content-Type': 'application/json'},
            credentials: "include",
            method: 'GET'
            });
        const result = await response.json();
        var houses = result.houses;
        var isInsured = result.isInsured;
        console.log(result);
        var index = 0;
        //percorrer a variável users e por cada user cria a linha da tabela com os dados presentes
        for (const house of houses) {
            txt += "<tr><td>" + house.idHome + "</td><td>" + house.morada + "</td><td><button type = 'button' onclick= 'passContentDelete()'style='float: right;' class='btn btn-danger notika-btn-danger waves-effect'><i class='glyphicon glyphicon-trash'></i> Eliminar</button>"; 
            txt += "<button type = 'button' data-toggle='modal' data-target='#modalRegisterForm' style='float: right' onclick='passContentUpdate()' class='btn btn-info notika-btn-info waves-effect'><i class='glyphicon glyphicon-edit'></i> Editar</button>"
            txt += "<button class='btn btn-success notika-btn-success waves-effect' type = 'button' data-toggle='modal' data-target='#modalSimulation' onclick = 'passContentSimulation()' style = 'float: right;display:" + isHouseInsured(isInsured[index]) + "'><i class='far fa-chart-bar'></i> Simular</button></td>"
            txt += "<td style= 'display:none'>" + house.area + "</td><td style= 'display:none'>" + house.ano + "</td><td style= 'display:none'>" + house.capitalImovel + "</td><td style= 'display:none'>" + house.owner + "</td><td style='display:none;'>"+ house.solarPanels + "</td>"
            txt += "<td style='display:none;'>" + house.prevention + "</td><td style='display:none;'>" + house.topologia + "</td></tr>"
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



function addHome(){
    isHouseInsured(22);
    console.log('save() foi corrido');
    var idHome = document.getElementById('idHome2').value;
    var morada = document.getElementById('morada2').value;
    var area = document.getElementById('area2').value;
    var ano = document.getElementById('ano2').value;
    var capitalImovel = document.getElementById('capitalImovel2').value;
    var owner = document.getElementById('owner2').value;
    var solarPanels = document.getElementById('solarPanels2').value;
    var prevention = document.getElementById('prevention2').value;
    var topologia = document.getElementById('topologia2').value;
    var data = {
        morada,
        area,
        ano,
        capitalImovel,
        owner,
        solarPanels,
        prevention,
        topologia
    };
    console.log(data);
    fetch('http://localhost:8080/api/user/addHome',{
    headers: {'Content-Type': 'application/json'},
    method: 'POST',
    credentials: 'include',
    body: JSON.stringify(data)
    }).then(function(response){
        if (!response.ok) {
            alertify.notify('Houve um erro ao adicionar a casa!', 'error', 5, function(){  console.log('dismissed'); });
            if (response.status === 409) {
            } else {
            throw Error(response.statusText);
            }
        } else {
            alertify.notify('Casa adicionada com sucesso!', 'success', 5, function(){  console.log('dismissed'); });
            }
    }).then(function (result) {
                console.log(result);
                refresh();
            }).catch(function (err) {
                            console.log(err);
        });    
}


function isHouseInsured(isInsured){

    if(isInsured == true){
        return 'none';
    }else{
        return 'inline';
    }

        
    }

function passContentSimulation(){
    var id = document.getElementById("idHome3");
    var table = document.getElementById('housesTable');
    for(var i = 0; i<table.rows.length; i++){
        table.rows[i].onclick = function(){
            id.value = this.cells[0].innerHTML;
        }
    }
}



