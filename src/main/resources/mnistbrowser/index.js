const serverUrl = "http://localhost:8080"

document.getElementById("printRandomBtn").addEventListener("click", (clickEvent) => {
    fetch(serverUrl + "/digits/TRAIN/random")
        .then(response =>  response.json() )
        .then(json => {
            document.getElementById("serverOutput").innerHTML = json.html
        })
})