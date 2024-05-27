const serverUrl = "http://localhost:8080"

let currentDigit = undefined

document.getElementById("printRandomBtn").addEventListener("click", (clickEvent) => {
    fetch(serverUrl + "/digits/TEST/random")
        .then(response =>  response.json() )
        .then(json => {
            document.getElementById("serverOutput").innerHTML = json.html
            currentDigit = json
        })

    let guessBtn = document.getElementById("guessDigitBtn")
    guessBtn.classList.remove("invisible")
    guessBtn.classList.add("visible")
})

document.getElementById("guessDigitBtn").addEventListener("click", (clickEvent) => {
    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(currentDigit),
    };

    fetch(serverUrl + "/digits/guess", options)
        .then(response =>  response.json() )
        .then(json => {
            document.getElementById("guessResultDiv").innerHTML = json
        })
})