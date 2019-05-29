function simular(){
    var names = document.getElementById("coberturas").value;
    var morada = document.getElementById("morada").value;
    var ano = document.getElementById("ano").value;
    var area = document.getElementById("area").value;
    var capitalImovel = document.getElementById("capitalImovel").value;
    var owner = document.getElementById("owner").value;
    var prevention = document.getElementById("prevention").value;
    var solarPanels = document.getElementById("solarPanel").value;
    var topologia = document.getElementById("topologia").value;

    console.log(names);

    var data = {
        morada : morada,
        area : area,
        ano : ano,
        capitalImovel : capitalImovel,
        owner : owner,
        solarPanels : solarPanels,
        prevention : prevention,
        topologia : topologia
    }

    console.log(data);
    fetch('http://localhost:8080/simulator/execute/noDeal',{
    headers: {'Content-Type': 'application/json'},
    method: 'POST',
    credentials: 'include',
    body: JSON.stringify(data)
    }).then(function(response){
        if (!response.ok) {
            alertify.notify('Houve um erro na simulação!', 'error', 5, function(){  console.log('dismissed'); });
            if (response.status === 409) {
            } else {
            throw Error(response.statusText);
            }
        } else {
            alertify.notify('Simulação feita com sucesso!', 'success', 5, function(){  console.log('dismissed'); });
            }
    }).then(function (result) {
                console.log(result);
            }).catch(function (err) {
                console.log(err);
            });    
}