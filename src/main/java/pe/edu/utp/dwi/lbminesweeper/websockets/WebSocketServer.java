package pe.edu.utp.dwi.lbminesweeper.websockets;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/ws")
public class WebSocketServer {
    public static final CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        session.getRequestParameterMap().get("");
        webSocketSet.add(this);
        System.out.println("Player connected: " + session.getId());
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        System.out.println("Player disconnected: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Message received from player: " + message);
        // Process the move and broadcast to all clients
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
}