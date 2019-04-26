function addHome(){
    console.log('save() foi corrido');
    var morada = document.getElementById('morada').value;
    var area = document.getElementById('area').value;
    var ano = document.getElementById('ano').value;
    var capitalImovel = document.getElementById('capitalImovel').value;
    var topologia = document.getElementById('topologia').value;
    var data = {
        morada,
        area,
        ano,
        capitalImovel, 
        topologia
    }
    console.log(data);
    fetch('http://localhost:8080/api/user/addHome',{
    headers: {'Content-Type': 'application/json'},
    method: 'POST',
    credentials: 'include',
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
