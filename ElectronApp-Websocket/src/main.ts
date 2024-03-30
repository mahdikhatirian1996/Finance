import {app, BrowserWindow, protocol} from "electron";

app.commandLine.appendSwitch('ignore-certificate-errors')
protocol.registerSchemesAsPrivileged([
    { scheme: 'https', privileges: { bypassCSP: true } }
])
function createWindow() {
    const mainWindow = new BrowserWindow({
        height: 600,
        width: 800,
        title: "dexTools App",
    });

    mainWindow.loadURL('https://www.dextools.io/app/en/ether/pool-explorer');
    mainWindow.webContents.session.webRequest.onHeadersReceived({urls: ["*://*/*"]},
        (details, callback) => {
            if (details.responseHeaders['X-Frame-Options']) {
                delete details.responseHeaders['X-Frame-Options'];
            } else if (details.responseHeaders['x-frame-options']) {
                delete details.responseHeaders['x-frame-options'];
            }
            callback({cancel: false, responseHeaders: details.responseHeaders});
        }
    );
    mainWindow.webContents.once('dom-ready', () => {
        mainWindow.webContents.executeJavaScript('const originalSend = WebSocket.prototype.send;window.sockets = [];window.pairs = [];WebSocket.prototype.send = function (...e) {if (window.sockets.indexOf(this) === -1) {window.sockets.push(this);this.addEventListener("message", (event) => {let message = JSON.parse(event.data);let data = message.result.data;let address = data.pair?.info?.address;let exists = window.pairs.some((pair) => pair.address === address);if (address && data.event && !exists) {let pair = data.pair;let info = data.pair?.info; var params = JSON.stringify({contractAddress: info?.address, name: info?.name, symbol: info?.symbol, holders: info?.holders, liquidity: pair?.liquidity, updatedDate: pair?.updatedAt * 1000, createdDate: pair?.createdAtTimestamp * 1000}); fetch(`http://localhost:8080/api/dextools/saveData/${(params)}`)}});}return originalSend.call(this, ...e)};', true)
            .catch(res => {
                console.log(JSON.stringify('Catch Error'));
            })
    });
    mainWindow.minimize();
}

app.on("ready", createWindow)
app.on("window-all-closed", () => {
    if (process.platform !== "darwin") {
        app.quit();
    }
});
app.on("activate", () => {
    if (BrowserWindow.getAllWindows().length === 0) {
        createWindow();
    }
});

