window.onload = fetchData();

function fetchData(){
fetch("http://localhost:8080/info", {
    method: 'GET',
    headers: {
        'Authorization': 'Bearer ' + localStorage.getItem("token"),
        'Content-Type': 'application/json'
    },
})
    .then(r => {
        if(r.status === 200){
            return r.json()
        } else if(r.status === 401){
            // document.querySelector('#info').classList.add("error")
            // return { "text": "Je bent niet geauthenticeerd. Haal eerst je token op" }
            // of redirect naar opgegeven location
            console.log(r.headers.get('Location'))
            window.location.href = r.headers.get('Location');

        }
    })
    .then(data =>
        document.querySelector('#info').innerHTML = data.text
    )
    .catch()
}


