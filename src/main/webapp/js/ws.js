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
        console.log(event);
        const data = JSON.parse(event.data);

        if(!data) return console.log("Error un interpret data");

        // Commands
        switch (data.type) {
            case 'SYNC':
                GameSocket.getInstance().syncPackage(data);
                break;
        }
    }

    syncPackage(data) {
        Game.getInstance().sync({cells: data.cells, state: data.state})
    }

    discoverCell(x, y) {
        this.sendMessage({
            type: "DISCOVER",
            x: x,
            y: y
        });
    }

    toggleFlagCell(x, y) {
        this.sendMessage({
            type: "TOGGLE_FLAG",
            x: x,
            y: y
        })
    }

    /**
     * Send a message to server
     * @param {object} message
     */
    sendMessage(message) {
        if(this.readyState !== this.OPEN) return;

        this.ws.send(JSON.stringify(message));
    }

    static getInstance() {
        return new GameSocket();
    }
}

GameSocket.getInstance();