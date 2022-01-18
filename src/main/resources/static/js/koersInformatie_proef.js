let wsBTC = new WebSocket('wss://stream.binance.com:9443/ws/btceur@trade');
let wsETH = new WebSocket('wss://stream.binance.com:9443/ws/etheur@trade');
let wsBNB = new WebSocket('wss://stream.binance.com:9443/ws/bnbeur@trade');
let wsSOL = new WebSocket('wss://stream.binance.com:9443/ws/soleur@trade');
let wsADA = new WebSocket('wss://stream.binance.com:9443/ws/adaeur@trade');
let wsXRP = new WebSocket('wss://stream.binance.com:9443/ws/xrpeur@trade');
let wsDOT = new WebSocket('wss://stream.binance.com:9443/ws/doteur@trade');
let wsDOGE = new WebSocket('wss://stream.binance.com:9443/ws/dogeeur@trade');
let wsAVAX = new WebSocket('wss://stream.binance.com:9443/ws/avaxeur@trade');
let wsLUNA = new WebSocket('wss://stream.binance.com:9443/ws/lunaeur@trade');
let wsLTC = new WebSocket('wss://stream.binance.com:9443/ws/ltceur@trade');
let wsMATIC = new WebSocket('wss://stream.binance.com:9443/ws/maticeur@trade');
let wsALGO = new WebSocket('wss://stream.binance.com:9443/ws/algoeur@trade');
let wsBCH = new WebSocket('wss://stream.binance.com:9443/ws/bcheur@trade');
let wsXLM = new WebSocket('wss://stream.binance.com:9443/ws/xlmeur@trade');
let wsVET = new WebSocket('wss://stream.binance.com:9443/ws/veteur@trade');
let wsICP = new WebSocket('wss://stream.binance.com:9443/ws/icpeur@trade');
let wsUST = new WebSocket('wss://stream.binance.com:9443/ws/usteur@trade');
let wsEGLD = new WebSocket('wss://stream.binance.com:9443/ws/egldeur@trade');
let wsFIL = new WebSocket('wss://stream.binance.com:9443/ws/fileur@trade');

let koersBTC = document.getElementById('koersBTC')
let koersETH = document.getElementById('koersETH')
let koersBNB = document.getElementById('koersBNB')
let koersSOL = document.getElementById('koersSOL')
let koersADA = document.getElementById('koersADA')
let koersXRP = document.getElementById('koersXRP')
let koersDOT = document.getElementById('koersDOT')
let koersDOGE = document.getElementById('koersDOGE')
let koersAVAX = document.getElementById('koersAVAX')
let koersLUNA = document.getElementById('koersLUNA')
let koersLTC = document.getElementById('koersLTC')
let koersMATIC = document.getElementById('koersMATIC')
let koersALGO = document.getElementById('koersALGO')
let koersBCH = document.getElementById('koersBCH')
let koersXLM = document.getElementById('koersXLM')
let koersVET = document.getElementById('koersVET')
let koersICP = document.getElementById('koersICP')
let koersUST = document.getElementById('koersUST')
let koersEGLD = document.getElementById('koersEGLD')
let koersFIL = document.getElementById('koersFIL')

let laatsteWaarde = null;
let divaGroen = '#44c26e';
let divaRood = '#E34232';
let divaGoud = '#ffc95f';

wsBTC.onmessage = (event) => {
    let stockObject = JSON.parse(event.data)
    let waarde = parseFloat(stockObject.p).toFixed(2);
    koersBTC.innerText = waarde;
    koersBTC.style.color = !laatsteWaarde || laatsteWaarde === waarde ? divaGoud : waarde > laatsteWaarde ? divaGroen : divaRood;
    laatsteWaarde = waarde;
}

wsETH.onmessage = (event) => {
    let stockObject = JSON.parse(event.data)
    let waarde = parseFloat(stockObject.p).toFixed(2);
    koersETH.innerText = waarde;
    koersETH.style.color = !laatsteWaarde || laatsteWaarde === waarde ? divaGoud : waarde > laatsteWaarde ? divaGroen : divaRood;
    laatsteWaarde = waarde;
}

wsBNB.onmessage = (event) => {
    let stockObject = JSON.parse(event.data)
    let waarde = parseFloat(stockObject.p).toFixed(2);
    koersBNB.innerText = waarde;
    koersBNB.style.color = !laatsteWaarde || laatsteWaarde === waarde ? divaGoud : waarde > laatsteWaarde ? divaGroen : divaRood;
    laatsteWaarde = waarde;
}

wsADA.onmessage = (event) => {
    let stockObject = JSON.parse(event.data)
    let waarde = parseFloat(stockObject.p).toFixed(2);
    koersADA.innerText = waarde;
    koersADA.style.color = !laatsteWaarde || laatsteWaarde === waarde ? divaGoud : waarde > laatsteWaarde ? divaGroen : divaRood;
    laatsteWaarde = waarde;
}

wsSOL.onmessage = (event) => {
    let stockObject = JSON.parse(event.data)
    let waarde = parseFloat(stockObject.p).toFixed(2);
    koersSOL.innerText = waarde;
    koersSOL.style.color = !laatsteWaarde || laatsteWaarde === waarde ? divaGoud : waarde > laatsteWaarde ? divaGroen : divaRood;
    laatsteWaarde = waarde;
}

wsXRP.onmessage = (event) => {
    let stockObject = JSON.parse(event.data)
    let waarde = parseFloat(stockObject.p).toFixed(2);
    koersXRP.innerText = waarde;
    koersXRP.style.color = !laatsteWaarde || laatsteWaarde === waarde ? divaGoud : waarde > laatsteWaarde ? divaGroen : divaRood;
    laatsteWaarde = waarde;
}

wsDOGE.onmessage = (event) => {
    let stockObject = JSON.parse(event.data)
    let waarde = parseFloat(stockObject.p).toFixed(2);
    koersDOGE.innerText = waarde;
    koersDOGE.style.color = !laatsteWaarde || laatsteWaarde === waarde ? divaGoud : waarde > laatsteWaarde ? divaGroen : divaRood;
    laatsteWaarde = waarde;
}

wsDOT.onmessage = (event) => {
    let stockObject = JSON.parse(event.data)
    let waarde = parseFloat(stockObject.p).toFixed(2);
    koersDOT.innerText = waarde;
    koersDOT.style.color = !laatsteWaarde || laatsteWaarde === waarde ? divaGoud : waarde > laatsteWaarde ? divaGroen : divaRood;
    laatsteWaarde = waarde;
}

wsAVAX.onmessage = (event) => {
    let stockObject = JSON.parse(event.data)
    let waarde = parseFloat(stockObject.p).toFixed(2);
    koersAVAX.innerText = waarde;
    koersAVAX.style.color = !laatsteWaarde || laatsteWaarde === waarde ? divaGoud : waarde > laatsteWaarde ? divaGroen : divaRood;
    laatsteWaarde = waarde;
}

wsLUNA.onmessage = (event) => {
    let stockObject = JSON.parse(event.data)
    let waarde = parseFloat(stockObject.p).toFixed(2);
    koersLUNA.innerText = waarde;
    koersLUNA.style.color = !laatsteWaarde || laatsteWaarde === waarde ? divaGoud : waarde > laatsteWaarde ? divaGroen : divaRood;
    laatsteWaarde = waarde;
}

wsLTC.onmessage = (event) => {
    let stockObject = JSON.parse(event.data)
    let waarde = parseFloat(stockObject.p).toFixed(2);
    koersLTC.innerText = waarde;
    koersLTC.style.color = !laatsteWaarde || laatsteWaarde === waarde ? divaGoud : waarde > laatsteWaarde ? divaGroen : divaRood;
    laatsteWaarde = waarde;
}

wsMATIC.onmessage = (event) => {
    let stockObject = JSON.parse(event.data)
    let waarde = parseFloat(stockObject.p).toFixed(2);
    koersMATIC.innerText = waarde;
    koersMATIC.style.color = !laatsteWaarde || laatsteWaarde === waarde ? divaGoud : waarde > laatsteWaarde ? divaGroen : divaRood;
    laatsteWaarde = waarde;
}

wsALGO.onmessage = (event) => {
    let stockObject = JSON.parse(event.data)
    let waarde = parseFloat(stockObject.p).toFixed(2);
    koersALGO.innerText = waarde;
    koersALGO.style.color = !laatsteWaarde || laatsteWaarde === waarde ? divaGoud : waarde > laatsteWaarde ? divaGroen : divaRood;
    laatsteWaarde = waarde;
}
wsBCH.onmessage = (event) => {
    let stockObject = JSON.parse(event.data)
    let waarde = parseFloat(stockObject.p).toFixed(2);
    koersBCH.innerText = waarde;
    koersBCH.style.color = !laatsteWaarde || laatsteWaarde === waarde ? divaGoud : waarde > laatsteWaarde ? divaGroen : divaRood;
    laatsteWaarde = waarde;
}

wsXLM.onmessage = (event) => {
    let stockObject = JSON.parse(event.data)
    let waarde = parseFloat(stockObject.p).toFixed(2);
    koersXLM.innerText = waarde;
    koersXLM.style.color = !laatsteWaarde || laatsteWaarde === waarde ? divaGoud : waarde > laatsteWaarde ? divaGroen : divaRood;
    laatsteWaarde = waarde;
}

wsVET.onmessage = (event) => {
    let stockObject = JSON.parse(event.data)
    let waarde = parseFloat(stockObject.p).toFixed(2);
    koersVET.innerText = waarde;
    koersVET.style.color = !laatsteWaarde || laatsteWaarde === waarde ? divaGoud : waarde > laatsteWaarde ? divaGroen : divaRood;
    laatsteWaarde = waarde;
}

wsICP.onmessage = (event) => {
    let stockObject = JSON.parse(event.data)
    let waarde = parseFloat(stockObject.p).toFixed(2);
    koersICP.innerText = waarde;
    koersICP.style.color = !laatsteWaarde || laatsteWaarde === waarde ? divaGoud : waarde > laatsteWaarde ? divaGroen : divaRood;
    laatsteWaarde = waarde;
}

wsUST.onmessage = (event) => {
    let stockObject = JSON.parse(event.data)
    let waarde = parseFloat(stockObject.p).toFixed(2);
    koersUST.innerText = waarde;
    koersUST.style.color = !laatsteWaarde || laatsteWaarde === waarde ? divaGoud : waarde > laatsteWaarde ? divaGroen : divaRood;
    laatsteWaarde = waarde;
}

wsEGLD.onmessage = (event) => {
    let stockObject = JSON.parse(event.data)
    let waarde = parseFloat(stockObject.p).toFixed(2);
    koersEGLD.innerText = waarde;
    koersEGLD.style.color = !laatsteWaarde || laatsteWaarde === waarde ? divaGoud : waarde > laatsteWaarde ? divaGroen : divaRood;
    laatsteWaarde = waarde;
}

wsFIL.onmessage = (event) => {
    let stockObject = JSON.parse(event.data)
    let waarde = parseFloat(stockObject.p).toFixed(2);
    koersFIL.innerText = waarde;
    koersFIL.style.color = !laatsteWaarde || laatsteWaarde === waarde ? divaGoud : waarde > laatsteWaarde ? divaGroen : divaRood;
    laatsteWaarde = waarde;
}