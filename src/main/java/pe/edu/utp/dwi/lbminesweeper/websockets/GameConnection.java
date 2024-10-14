package pe.edu.utp.dwi.lbminesweeper.websockets;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/ws/minesweeper")
public class GameConnection {

    private static final Set<Session> players = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        players.add(session);
        System.out.println("New player connected: " + session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        players.remove(session);
        System.out.println("Player disconnected: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Message received from player " + session.getId() + ": " + message);

        // Broadcast the message to all players
        for (Session player : players) {
            if (player.isOpen()) {
                player.getAsyncRemote().sendText(message);
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("Error occurred: " + throwable.getMessage());
    }
}

