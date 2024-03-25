// --- Beautify Javascript ---
const originalSend = WebSocket.prototype.send;
window.sockets = [];
window.pairs = [];
WebSocket.prototype.send = function (...e) {
    if (window.sockets.indexOf(this) === -1) {
        window.sockets.push(this);
        this.addEventListener("message", (event) => {
            let message = JSON.parse(event.data);
            let data = message.result.data;
            let address = data.pair?.info?.address;
            let exists = window.pairs.some((pair) => pair.address === address);
            if (address && data.event && !exists) {
                let pair = data.pair;
                let info = data.pair?.info;
                var params = JSON.stringify({
                    address: info?.address,
                    name: info?.name,
                    symbol: info?.symbol,
                    holders: info?.holders,
                    liquidity: pair?.liquidity,
                    updatedDate: pair?.updatedAt,
                    createdDate: pair?.createdAtTimestamp
                });
                fetch(`http://localhost:8080/api/dextools/getData/${(params)}`)
            }
        });
    }
    return originalSend.call(this, ...e)
};

// --- Inline Javascript Is Correct With All Param In PathParam ---
// const originalSend = WebSocket.prototype.send;window.sockets = [];window.pairs = [];WebSocket.prototype.send = function (...e) {if (window.sockets.indexOf(this) === -1) {window.sockets.push(this);this.addEventListener("message", (event) => {let message = JSON.parse(event.data);let data = message.result.data;let address = data.pair?.info?.address;let exists = window.pairs.some((pair) => pair.address === address);if (address && data.event && !exists) {let pair = data.pair;let info = data.pair?.info;let address = info?.address;let name = info?.name;let symbol = info?.symbol;let holders = info?.holders;let liquidity = pair?.liquidity;let updatedDate = pair?.updatedAt;let createdDate = pair?.createdAtTimestamp;fetch(`http://localhost:8080/api/dextools/getData/${(address)}/${(name)}/${(symbol)}/${(holders)}/${(liquidity)}/${(updatedDate)}/${(createdDate)}`)}});}return originalSend.call(this, ...e)};

// --- Inline Javascript Is Correct With One Json PathParam ---
//const originalSend = WebSocket.prototype.send;window.sockets = [];window.pairs = [];WebSocket.prototype.send = function (...e) {if (window.sockets.indexOf(this) === -1) {window.sockets.push(this);this.addEventListener("message", (event) => {let message = JSON.parse(event.data);let data = message.result.data;let address = data.pair?.info?.address;let exists = window.pairs.some((pair) => pair.address === address);if (address && data.event && !exists) {let pair = data.pair;let info = data.pair?.info;let address = info?.address;let name = info?.name;let symbol = info?.symbol;let holders = info?.holders;let liquidity = pair?.liquidity;let updatedDate = pair?.updatedAt;let createdDate = pair?.createdAtTimestamp; var params = JSON.stringify({contractAddress : address, name : name, symbol : symbol, holders : holders, liquidity : liquidity, updatedDate : updatedDate, createdDate : createdDate}); fetch(`http://localhost:8080/api/dextools/getData/${(params)}`)}});}return originalSend.call(this, ...e)};

// --- Inline Javascript Is Correct With One Json PathParam Last Test And Minified ---
//const originalSend = WebSocket.prototype.send;window.sockets = [];window.pairs = [];WebSocket.prototype.send = function (...e) {if (window.sockets.indexOf(this) === -1) {window.sockets.push(this);this.addEventListener("message", (event) => {let message = JSON.parse(event.data);let data = message.result.data;let address = data.pair?.info?.address;let exists = window.pairs.some((pair) => pair.address === address);if (address && data.event && !exists) {let pair = data.pair;let info = data.pair?.info; var params = JSON.stringify({address: info?.address, name: info?.name, symbol: info?.symbol, holders: info?.holders, liquidity: pair?.liquidity, updatedDate: pair?.updatedAt, createdDate: pair?.createdAtTimestamp}); fetch(`http://localhost:8080/api/dextools/getData/${(params)}`)}});}return originalSend.call(this, ...e)};

