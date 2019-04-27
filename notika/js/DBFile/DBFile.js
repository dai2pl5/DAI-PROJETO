function uploadFile(){
    console.log('signin() foi corrido');
    const input = document.getElementById('fileToUpload').files[0];
    
        fetch('http://localhost:8080/uploadFile',{
        headers: {'Content-Type': 'application/json'},
        credentials: "include",
        method: 'POST',
        body: input
        }).then(function(response){
            if (!response.ok) {
               alert('Deu merda');
            } else {
                alert("submitted with success");
                console.log(response);
                }
        }).then(function (result) {
            console.log(result);
        }).catch(function (err) {
            console.log(err);
        });
}
    