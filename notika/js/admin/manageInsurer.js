window.onload = function(){
    getInsurers();  
}

function getInsurers() {
    async function fetchAsync() {
        const render = document.getElementById("insurerTableBody");
        let txt = "";
        const response = await fetch('http://localhost:8080/api/admin/getInsurers',{
            headers: {'Content-Type': 'application/json'},
            credentials: "include",
            method: 'GET'
            });
        const data = await response.json();
        console.log(data);
        var insurers = data;
       
        for (const insurer of insurers) {
            txt += "<tr><td>" + insurer.name + "</td><td>" + insurer.email + "</td>";
            txt += "<td><button type = 'button' onclick= 'confirmDelete(this)'style='float: right;' class='btn btn-danger notika-btn-danger waves-effect' value ='" + insurer.id + "'><i class='glyphicon glyphicon-trash'></i> Eliminar</button>";
        
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


function registerInsurer(){

    var username = document.getElementById("username").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var name = document.getElementById("name").value;

    data = {username,email,password,name}

    console.log(data);
    fetch('http://localhost:8080/api/auth/signup/insurer',{
        headers: {'Content-Type': 'application/json'},
        method: 'POST',
        credentials: 'include',
        body: JSON.stringify(data)
        }).then(function(response){
            if (!response.ok) {
                alertify.notify('Houve um erro ao adicionar a seguradora!', 'error', 5, function(){  console.log('dismissed'); });
                if (response.status === 409) {
                } else {
                throw Error(response.statusText);
                }
            } else {
                alertify.notify('Seguradora adicionada com sucesso!', 'success', 5, function(){  console.log('dismissed'); });
                   
            }
        }).then(function (result) {
            getInsurers(); 
                    console.log(result);
                    
                }).catch(function (err) {
                                console.log(err);
            });    
    }

    function confirmDelete(button){
        var id = button.value;
        console.log(id);
         swal({
            title: "Atenção!",
            text: "Tem a certeza que quer eliminar esta seguradora?!",
            icon: "warning",
            buttons: ["Cancelar", true],
            dangerMode: true,
            })
            .then((willDelete) => {
            if (willDelete) {
            deleteInsurer(id);
                }
                });
                
        
    }

    function deleteInsurer(id){

        fetch('http://localhost:8080/api/admin/deleteInsurer/'+ id,{
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
            method: 'DELETE',
            credentials: 'include',
        })
        .then(function (response) {
            if (!response.ok) {
                alertify.notify('Houve um erro ao eliminar a seguradora!', 'error', 5, function(){  console.log('dismissed'); });
                if (response.status === 409) {
                } else {
                throw Error(response.statusText);
                }
            } else {
                alertify.notify('Seguradora eliminada com sucesso!', 'success', 5, function(){  console.log('dismissed'); });
                
                
                }
            return response.json();
        })
        .then(function (result) {
            console.log(result);
            getInsurers();
        })
        .catch (function (error) {
            console.log('Request failed', error);
        });
    }