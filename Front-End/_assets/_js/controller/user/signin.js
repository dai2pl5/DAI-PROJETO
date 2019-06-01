function signin(){
    console.log('signin() foi corrido');
    var usernameOrEmail = document.getElementById('emailLogin').value
    var password = document.getElementById('passwordLogin').value
    var renderIfError = document.getElementById("renderSwal").innerHTML;
    var render = document.getElementById("renderSwal");
    var data = {
                usernameOrEmail:usernameOrEmail,
                password:password
        };
        console.log(data);
        fetch('http://localhost:8080/api/auth/signin',{
        headers: {'Content-Type': 'application/json'},
        credentials: "include",
        method: 'POST',
        body: JSON.stringify(data)
        }).then(function(response){
            if (!response.ok) {
                render.innerHTML = swal("Erro!", "Tente novamente!", "error").then(function(){render.innerHTML = renderIfError});
            } else {
                render.innerHTML = swal("Logado com sucesso!", "A ser redirecionado!", "success").then(function(){redirectUser()});
        
                }
        }).then(function (result) {
            console.log(result);
        }).catch(function (err) {
            console.log(err);
        });
}

function redirectUser(){

    async function fetchAsync(){
        const response = await fetch('http://localhost:8080/api/user/me',{
        headers: {'Content-Type': 'application/json'},
        credentials: "include",
        method: 'GET',
        });
        const user = await response.json();
        const role = user.roles[0].name;
        if(role === "ROLE_USER"){
            window.location.href = "../notika/perfil.html";
        }else if(role === "ROLE_INSURER"){
            window.location.href = "../notika/seguradoraIndex.html";
        }else if(role === "ROLE_ADMIN"){
            //window.location.href("ainda não sei");
        }
    }
        //chama a função fetchAsync()
        fetchAsync().then(function(data){
        console.log('ok');
        }).catch(function(reason){
            console.log(reason.message);
        });

    
}
