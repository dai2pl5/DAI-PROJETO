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
            txt += "<button type = 'button' data-toggle='modal' data-target='#modalRegisterForm' style='float: right' onclick='passContentUpdate()' class='btn btn-info notika-btn-info waves-effect'><i class='glyphicon glyphicon-edit'></i> Editar</button>"
            txt += "<button class='btn btn-success notika-btn-success waves-effect' type = 'button' data-toggle='modal' data-target='#modalSimulation' onclick = 'passContentSimulation()' style = 'float: right;'><i class='far fa-eye'></i> Ver</button></td></tr>"
            txt += "<div id = 'popup" + indexPopup + "'class = 'overlay'><div class='popup'><a class='close' href='#'>&times;</a>";
            ndex += 1;
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