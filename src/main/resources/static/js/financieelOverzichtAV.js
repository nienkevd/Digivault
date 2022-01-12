window.onload = fetchData();

function fetchData(){
fetch("http://localhost:8080/financieeloverzicht/{klantId}", {
    method: 'GET',
    headers: {
        'Authorization': 'Bearer ' + localStorage.getItem("token"),
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
    },
})
    .then(response => {
        if(response.status === 200){
            return response.json()
        } else if(response.status === 401){
            console.log(response.headers.get('Location'))
            window.location.href = response.headers.get('Location');
        }
    })
    .then(data =>
        createTable()
    )
    .catch()
}

function createTable(){
}


