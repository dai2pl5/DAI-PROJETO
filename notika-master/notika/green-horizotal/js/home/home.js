window.onload = function(){
    refresh()
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
        //percorrer a variável users e por cada user cria a linha da tabela com os dados presentes
        for (const house of houses) {
            txt += "<tr><td>" + house.idHome + "</td>" + "<td>" + house.morada + "</td>" + "<td> <button style='float: right;' class='btn btn-primary'><i class='glyphicon glyphicon-trash'></i></button> <button type = 'button' style='float: right' onclick='passContentDelete()'class='btn btn-primary'><i class='glyphicon glyphicon-edit'></i></button> </td></tr>"
        }
        
    
        //envia a tabela construida para a view e mostra no object com ID result
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
    fetch('http://localhost:8080/api/user/deleteHouse/' + id,{
                headers: {'Content-Type': 'application/json'},
                method: 'DELETE',
                credentials : "include"
            }).then(response =>
                response.json().then(json => {
                refresh();
                return json;
                
    }));
}
    
function passContentDelete(){
    var id;
    var table = document.getElementById('housesTable');
    console.log
    for(var i = 0; i<table.rows.length; i++){
        table.rows[i].onclick = function(){
            id = this.cells[0].innerHTML;
            document.getElementById('id').value = id;
            console.log(id);
            deleteHome(id);
            
        }
    }
}
