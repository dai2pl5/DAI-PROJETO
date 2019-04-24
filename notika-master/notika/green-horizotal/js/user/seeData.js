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




