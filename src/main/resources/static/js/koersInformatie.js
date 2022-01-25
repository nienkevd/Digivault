const wsBTC = new WebSocket('wss://stream.binance.com:9443/ws/btceur@trade');
const wsETH = new WebSocket('wss://stream.binance.com:9443/ws/etheur@trade');
const wsBNB = new WebSocket('wss://stream.binance.com:9443/ws/bnbeur@trade');
const wsSOL = new WebSocket('wss://stream.binance.com:9443/ws/soleur@trade');
const wsADA = new WebSocket('wss://stream.binance.com:9443/ws/adaeur@trade');
const wsXRP = new WebSocket('wss://stream.binance.com:9443/ws/xrpeur@trade');
const wsDOT = new WebSocket('wss://stream.binance.com:9443/ws/doteur@trade');
const wsDOGE = new WebSocket('wss://stream.binance.com:9443/ws/dogeeur@trade');
const wsAVAX = new WebSocket('wss://stream.binance.com:9443/ws/avaxeur@trade');
const wsLUNA = new WebSocket('wss://stream.binance.com:9443/ws/lunaeur@trade');
const wsLTC = new WebSocket('wss://stream.binance.com:9443/ws/ltceur@trade');
const wsMATIC = new WebSocket('wss://stream.binance.com:9443/ws/maticeur@trade');
const wsALGO = new WebSocket('wss://stream.binance.com:9443/ws/algoeur@trade');
const wsBCH = new WebSocket('wss://stream.binance.com:9443/ws/bcheur@trade');
const wsXLM = new WebSocket('wss://stream.binance.com:9443/ws/xlmeur@trade');
const wsVET = new WebSocket('wss://stream.binance.com:9443/ws/veteur@trade');
const wsICP = new WebSocket('wss://stream.binance.com:9443/ws/icpeur@trade');
const wsHEX = new WebSocket('wss://dex.binance.org/api/ws/BNB_BTCB-1DE@marketDepth');
const wsEGLD = new WebSocket('wss://stream.binance.com:9443/ws/egldeur@trade');
const wsSHIB = new WebSocket('wss://stream.binance.com:9443/ws/shibeur@trade');

const koersBTC = document.getElementById('koersBTC');
const koersETH = document.getElementById('koersETH');
const koersBNB = document.getElementById('koersBNB');
const koersSOL = document.getElementById('koersSOL');
const koersADA = document.getElementById('koersADA');
const koersXRP = document.getElementById('koersXRP');
const koersDOT = document.getElementById('koersDOT');
const koersDOGE = document.getElementById('koersDOGE');
const koersAVAX = document.getElementById('koersAVAX');
const koersLUNA = document.getElementById('koersLUNA');
const koersLTC = document.getElementById('koersLTC');
const koersMATIC = document.getElementById('koersMATIC');
const koersALGO = document.getElementById('koersALGO');
const koersBCH = document.getElementById('koersBCH');
const koersXLM = document.getElementById('koersXLM');
const koersVET = document.getElementById('koersVET');
const koersICP = document.getElementById('koersICP');
const koersHEX = document.getElementById('koersHEX');
const koersEGLD = document.getElementById('koersEGLD');
const koersSHIB = document.getElementById('koersSHIB');

let laatsteWaarde = null;
const divaGroen = '#44c26e';
const divaRood = '#E34232';
const divaGoud = '#ffc95f';

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

// wsHEX.onmessage = (event) => {
//     let stockObject = JSON.parse(event.data)
//     let waarde = parseFloat(stockObject.p).toFixed(2);
//     koersHEX.innerText = waarde;
//     koersHEX.style.color = !laatsteWaarde || laatsteWaarde === waarde ? divaGoud : waarde > laatsteWaarde ? divaGroen : divaRood;
//     laatsteWaarde = waarde;
// }

wsEGLD.onmessage = (event) => {
    let stockObject = JSON.parse(event.data)
    let waarde = parseFloat(stockObject.p).toFixed(2);
    koersEGLD.innerText = waarde;
    koersEGLD.style.color = !laatsteWaarde || laatsteWaarde === waarde ? divaGoud : waarde > laatsteWaarde ? divaGroen : divaRood;
    laatsteWaarde = waarde;
}

wsSHIB.onmessage = (event) => {
    let stockObject = JSON.parse(event.data)
    let waarde = parseFloat(stockObject.p).toFixed(2);
    koersSHIB.innerText = waarde;
    koersSHIB.style.color = !laatsteWaarde || laatsteWaarde === waarde ? divaGoud : waarde > laatsteWaarde ? divaGroen : divaRood;
    laatsteWaarde = waarde;
}