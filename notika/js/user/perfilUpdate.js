window.onload = function(){
    userData();
}
function userData(){
    async function fetchAsync() {
        const username = document.getElementById("username");
        const mail = document.getElementById("mail");
        const name = document.getElementById("name");
        const response = await fetch('http://localhost:8080/api/user/me',{
        headers: {'Content-Type': 'application/json'},
        credentials: "include",
        method: 'GET',
        });
        const user = await response.json();
        console.log(user);
        console.log(user.name);
        username.value = user.username;
        name.value = user.name;
        mail.value = user.email;
    }
        //chama a função fetchAsync()
        fetchAsync().then(function(data){
        console.log('ok');
        }).catch(function(reason){
            console.log(reason.message);
        });

}
function edit(){
    document.getElementById("cenas").style.visibility = "hidden";
    document.getElementById("formPassword").disabled = false;
    document.getElementById("username").disabled = false;
    document.getElementById("name").disabled = false;
    document.getElementById("edit").setAttribute("onclick", "perfilUpdate()");

}

function perfilUpdate(){
    console.log('update() foi corrido');
    var email = document.getElementById("mail").value;
    var password = document.getElementById("formPassword").value;
    var username = document.getElementById("username").value;
    var name = document.getElementById("name").value;
    var data = {
           email,
           name,
           password,
           username,
    };
    console.log(data);
    fetch('http://localhost:8080/api/user/updateInfo',{
    headers: {'Content-Type': 'application/json'},
    method: 'PUT',
    credentials : "include",
    body: JSON.stringify(data)
    }).then(function(response){
        if (!response.ok) {
            swal("Erro!", "Tente novamente!", "error");
        } else {
            swal("Informação alterada com sucesso!", "", "success");
            
            }
    }).then(function (result) {
                console.log(result);
                
            }).catch(function (err) {
                    console.log(err);
                        });
        
}

