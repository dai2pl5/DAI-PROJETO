window.onload = function(){
    refresh()
}

function refresh() {
    async function fetchAsync() {
        const render = document.getElementById("housesTable");
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
            txt += "<tr><td>" + house.idHome + "</td>" + "<td>" + house.morada + "</td>" + "<td> <button style='float: right;' class='btn btn-primary'><i class='glyphicon glyphicon-trash'></i></button> <button style='float: right' class='btn btn-primary'><i class='glyphicon glyphicon-edit'></i></button> </td></tr>"
        }
        txt += "</tbody></table>";
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


function deleteHouse(){

    
}
