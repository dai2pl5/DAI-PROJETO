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
            alertify.notify('Houve um erro ao adicionar a casa!', 'error', 5, function(){  console.log('dismissed'); });
            if (response.status === 409) {
            } else {
            throw Error(response.statusText);
            }
        } else {
            alertify.notify('Casa adicionada com sucesso!', 'success', 5, function(){  console.log('dismissed'); });
            }
    }).then(function (result) {
                console.log(result);
            }).catch(function (err) {
                            console.log(err);
                        });    
}
