import {app, BrowserWindow, protocol} from "electron";

app.commandLine.appendSwitch('ignore-certificate-errors')
protocol.registerSchemesAsPrivileged([
    { scheme: 'https', privileges: { bypassCSP: true } }
])
function createWindow() {
    const mainWindow = new BrowserWindow({
        height: 1000,
        width: 1000,
        title: "DexTools Application",
    });

    mainWindow.loadURL('https://www.dextools.io/app/en/solana/pool-explorer');
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
    mainWindow.webContents.openDevTools();
    mainWindow.webContents.once('dom-ready', () => {
        mainWindow.webContents.executeJavaScript('const originalSend = WebSocket.prototype.send;window.sockets = [];window.pairs = [];WebSocket.prototype.send = function (...e) {if (window.sockets.indexOf(this) === -1) {window.sockets.push(this);this.addEventListener("message", (event) => {let message = JSON.parse(event.data);let data = message.result.data;let info = data.pair?.info;let address = info?.address;let createdAt = data.pair?.createdAt;let updatedAt = data.pair?.updatedAt;let liquidity = data.pair?.liquidity;let exists = window.pairs.some((pair) => pair.address === address);if (address && data.event && !exists) {fetch(`http://localhost:8080/api/dextools/postData/solana`, {method: `POST`, headers: {\'Content-Type\': \'application/json\'}, body: JSON.stringify({info: JSON.stringify(info), createdAt: createdAt, updatedAt: updatedAt, liquidity: liquidity})});}});}return originalSend.call(this, ...e)};', true)
            .catch(res => {
                console.log(JSON.stringify('Catch Error : ' + res.toString()));
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

