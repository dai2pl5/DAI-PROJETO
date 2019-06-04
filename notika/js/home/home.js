window.onload = function(){
    refresh2()
    
}

function refresh() {
    async function fetchAsync() {
        
        const render = document.getElementById("housesTableBody");
        let txt = "";
        const response = await fetch('http://localhost:8080/api/user/getHouses/',{
            headers: {'Content-Type': 'application/json'},
            credentials: "include",
            method: 'GET'
            });
        const houses = await response.json();
        console.log(houses);
        console.log("123");
        //percorrer a variável users e por cada user cria a linha da tabela com os dados presentes
        for (const house of houses) {
            txt += "<tr><td>" + house.idHome + "</td><td>" + house.morada + "</td><td><button";
            txt += "type = 'button' onclick= 'passContentDelete()'style='float: right;' class='btn btn-danger notika-btn-danger waves-effect'><i class='glyphicon glyphicon-trash'></i></button><button type = 'button' data-toggle='modal' data-target='#modalRegisterForm' style='float: right' onclick='passContentUpdate()'";
            txt += "class='btn btn-info notika-btn-info waves-effect'><i class='glyphicon glyphicon-edit'></i> Editar</button></td>";
            txt += "<td style= 'display:none'>" + house.area + "</td><td style= 'display:none'>" + house.ano + "</td><td style= 'display:none'>" + house.capitalImovel + "</td><td style= 'display:none'>" + house.owner + "</td><td style='display:none;'>"+ house.solarPanels + "</td>"
            txt += "<td style='display:none;'>" + house.prevention + "</td><td style='display:none;'>" + house.topologia + "</td></tr>"
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


function deleteHome(id){
    
   
    console.log('delete a correr');
            console.log(id);
    fetch('http://localhost:8080/api/user/deleteHome/' + id,{
                headers: {'Content-Type': 'application/json'},
                method: 'DELETE',
                credentials : "include"
            }).then(function(response){
                if (!response.ok) {
                    alertify.notify('Houve um erro ao eliminar a casa!', 'error', 5, function(){  console.log('dismissed'); });
                } else {
                    alertify.notify('Casa eliminada com sucesso!', 'success', 5, function(){  console.log('dismissed'); });
                    refresh();
                    }
            }).then(function (result) {
                        console.log(result);
                    }).catch(function (err) {
                            console.log(err);
                    });
                }

    
function passContentDelete(){
    var id;
    var table = document.getElementById('housesTable');
    console.log
    for(var i = 0; i<table.rows.length; i++){
        table.rows[i].onclick = function(){
            id = this.cells[0].innerHTML;
            console.log(id);
            swal({
                title: "Atenção!",
                text: "Tem a certeza que quer eliminar esta casa?!",
                icon: "warning",
                buttons: ["Cancelar", true],
                dangerMode: true,
                })
                .then((willDelete) => {
                if (willDelete) {
                    deleteHome(id);
                    }
                    });
            
        }
    }
}

function passContentUpdate(){

    var idHome = document.getElementById('idHome');
    var morada = document.getElementById('morada');
    var area = document.getElementById('area');
    var ano = document.getElementById('ano');
    var capitalImovel = document.getElementById('capitalImovel');
    var owner = document.getElementById('owner');
    var solarPanels = document.getElementById('solarPanels');
    var prevention = document.getElementById('prevention');
    var topologia = document.getElementById('topologia');
    var table = document.getElementById('housesTable');
    for(var i = 0; i<table.rows.length; i++){
        table.rows[i].onclick = function(){
            idHome.value = this.cells[0].innerHTML;
            morada.value = this.cells[1].innerHTML;
            area.value = this.cells[3].innerHTML;
            ano.value = this.cells[4].innerHTML;
            capitalImovel.value = this.cells[5].innerHTML;
            owner.value = this.cells[6].innerHTML;
            solarPanels.value = this.cells[7].innerHTML;
            prevention.value = this.cells[8].innerHTML;
            topologia.value = this.cells[9].innerHTML;
            
        }
    }
    
}

function confirmUpdate(){
    swal({
        title: "Atenção!",
        text: "Tem a certeza que quer alterar?!",
        icon: "warning",
        buttons: ["Cancelar", true],
        dangerMode: true,
        })
        .then((willDelete) => {
        if (willDelete) {
            	updateHome();
            }
            });
}
function updateHome(){
    console.log('update() foi corrido');
    var idHome = document.getElementById('idHome').value;
    var morada = document.getElementById('morada').value;
    var area = document.getElementById('area').value;
    var ano = document.getElementById('ano').value;
    var capitalImovel = document.getElementById('capitalImovel').value;
    var owner = document.getElementById('owner').value;
    var solarPanels = document.getElementById('solarPanels').value;
    var prevention = document.getElementById('prevention').value;
    var topologia = document.getElementById('topologia').value;
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
    fetch('http://localhost:8080/api/user/alterHome/' + idHome,{
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
