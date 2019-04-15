function signup(){
    console.log('signup() foi corrido');
    var name = document.getElementById('nome').value
    var username = document.getElementById('username').value
    var email = document.getElementById('email').value
    var password = document.getElementById('password').value
    var data = {
                name:name,
                username:username,
                email:email,
                password:password
        };
        console.log(data);
        fetch('http://localhost:8080/api/auth/signup',{
        headers: {'Content-Type': 'application/json'},
        method: 'POST',
        body: JSON.stringify(data)
        }).then(function(response){
            if (!response.ok) {
                console.log(response.status); //=> number 100–599
                console.log(response.statusText); //=> String
                console.log(response.headers); //=> Headers
                console.log(response.url); //=> String
                if (response.status === 409) {
                alert("Duplicated Email");
                } else {
                throw Error(response.statusText);
                }
            } else {
                alert("submitted with success");
                
                }
        }).then(function (result) {
                    console.log(result);
                }).catch(function (err) {
                        console.log(err);
                            });
    }
    
