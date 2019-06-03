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
        fetch('http://35.222.187.200:8080/auth/signin',{
        headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
        method: 'POST',
        credentials: 'include',
        body: JSON.stringify(data)
    })
    .then(function (response) {
        if (!response.ok) {
            render.innerHTML = swal("Erro!", "Tente novamente!", "error").then(function(){render.innerHTML = renderIfError});
        } else {
            //render.innerHTML = swal("Logado com sucesso!", "A ser redirecionado!", "success").then(function(){redirectUser()});
        }
        return response.json();
    })
    .then(function (result) {
        console.log(result);
        console.log(result.accessToken);
        setCookie("token", result.accessToken, 3, render);
        
    })
    .catch (function (error) {
        console.log('Request failed', error);
    });
}

function redirectUser(){

    async function fetchAsync(){
        const response = await fetch('http://35.222.187.200:8080/api/user/me',{
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


function setCookie(name,value,days,render) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days*24*60*60*1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
    render.innerHTML = swal("Logado com sucesso!", "A ser redirecionado!", "success").then(function(){redirectUser()});
}