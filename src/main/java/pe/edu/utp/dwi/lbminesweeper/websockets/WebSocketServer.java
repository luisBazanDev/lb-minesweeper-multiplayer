package pe.edu.utp.dwi.lbminesweeper.websockets;

import com.google.gson.Gson;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import pe.edu.utp.dwi.lbminesweeper.service.GameProvider;
import pe.edu.utp.dwi.lbminesweeper.service.WebsocketProvider;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/ws")
public class WebSocketServer {
    public static final CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        System.out.println(session.getRequestParameterMap().get("uuid"));
        if(session.getRequestParameterMap().get("uuid") == null) {
            try {
                session.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        String uuid = session.getRequestParameterMap().get("uuid").getFirst();

        if(GameProvider.getGame(uuid) == null) {
            try {
                session.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        WebsocketProvider.register(uuid, session);

        session.getAsyncRemote().sendText(new Gson().toJson(GameProvider.getGame(uuid).getObfuscatedCells()));
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