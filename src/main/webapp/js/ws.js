const WEBSOCKET_ENDPOINT = window.location.toString().split('/play').shift().replace("http://", "ws://")
 + "/ws";
const UUID = window.location.toString().split("?uuid=").pop();

class GameSocket {
    constructor() {
        if(typeof GameSocket.instance === "object") {
            return GameSocket.instance;
        }

        this.ws = new WebSocket(WEBSOCKET_ENDPOINT+"?uuid="+UUID);
        this.ws.onopen = this.onOpen;
        this.ws.onmessage = this.onMessage;
        this.ws.onerror = this.onError;
        this.ws.onclose = this.onClose;

        GameSocket.instance = this;
        return this;
    }

    onOpen(event) {
        console.log("Connection open event:");
        console.log(event);
    }

    onClose(event) {
        console.log("Connection closed event:");
        console.log(event);
    }

    onError(event) {
        console.log("Connection error event:");
        console.log(event);
    }

    onMessage(event) {
        console.log("Message event:");
        console.log(event);
        GameSocket.getInstance().syncPackage(event.data);
    }

    syncPackage(data) {
        Game.getInstance().sync({cells: JSON.parse(data), state: "PROGRESS"})
    }

    /**
     * Send a message to server
     * @param {object} message
     */
    sendMessage(message) {
        if(this.readyState !== this.OPEN) return;

        const pack = {
            uuid: UUID,
            data: message
        }

        this.send(JSON.stringify(pack));
    }

    static getInstance() {
        return new GameSocket();
    }
}

GameSocket.getInstance();