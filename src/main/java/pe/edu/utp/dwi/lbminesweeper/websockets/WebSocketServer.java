package pe.edu.utp.dwi.lbminesweeper.websockets;

import com.google.gson.Gson;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import pe.edu.utp.dwi.lbminesweeper.command.DiscoverCommand;
import pe.edu.utp.dwi.lbminesweeper.command.GenericCommand;
import pe.edu.utp.dwi.lbminesweeper.command.SyncCommand;
import pe.edu.utp.dwi.lbminesweeper.domain.enums.CommandType;
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

        session.getAsyncRemote().sendText(new SyncCommand(uuid).toString());
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        System.out.println("Player disconnected: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        String uuid = session.getRequestParameterMap().get("uuid").getFirst();

        System.out.println(uuid + ": " + message);

        System.out.println("Message received from player: " + message);
        GenericCommand genericCommand = new Gson().fromJson(message, GenericCommand.class);

        switch (genericCommand.getType()) {
            case CommandType.DISCOVER -> {
                DiscoverCommand discoverCommand = new Gson().fromJson(message, DiscoverCommand.class);
                System.out.printf("Discovered: X: %d Y: %d%n", discoverCommand.getX(), discoverCommand.getY());
                GameProvider.getGame(uuid).processMove(discoverCommand.getX(), discoverCommand.getY(), false);

                WebsocketProvider.broadcastMessage(uuid, new SyncCommand(uuid).toString());
            }
        }

        System.out.println("Test 12");
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
}