const originalSend = WebSocket.prototype.send;
window.sockets = [];
window.pairs = [];
WebSocket.prototype.send = function (...e) {
    if (window.sockets.indexOf(this) === -1) {
        window.sockets.push(this);
        this.addEventListener("message", (event) => {
            let message = JSON.parse(event.data);
            let data = message.result.data;
            let info = data.pair?.info;
            let address = info?.address;
            let createdAt = data.pair?.createdAt;
            let updatedAt = data.pair?.updatedAt;
            let liquidity = data.pair?.liquidity;
            let exists = window.pairs.some((pair) => pair.address === address);
            if (address && data.event && !exists) {
                fetch(`http://localhost:8080/api/dextools/postData/solana`, {
                    method: `POST`,
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify({
                        info: JSON.stringify(info),
                        createdAt: createdAt,
                        updatedAt: updatedAt,
                        liquidity: liquidity
                    })
                });
            }
        });
    }
    return originalSend.call(this, ...e)
};