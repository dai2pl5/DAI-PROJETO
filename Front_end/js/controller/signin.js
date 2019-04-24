function signin(){
    console.log('signin() foi corrido');
    var usernameOrEmail = document.getElementById('emailLogin').value
    var password = document.getElementById('passwordLogin').value
    
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
               alert('Deu merda');
            } else {
                alert("submitted with success");
                console.log(response);
                window.location.href = "../notika-master/notika/green-horizotal/perfil_teste.html";
                }
        }).then(function (result) {
            console.log(result);
        }).catch(function (err) {
            console.log(err);
        });
}

function redirectUser(){

    
}
