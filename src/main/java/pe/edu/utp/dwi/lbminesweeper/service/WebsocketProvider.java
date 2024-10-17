package pe.edu.utp.dwi.lbminesweeper.service;

import jakarta.websocket.Session;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WebsocketProvider {
    private static HashMap<String, Set<Session>> sockets = new HashMap<>();

    public static void sendMessage(String uuid, String message) {
        Set<Session> sessions = sockets.get(uuid);

        if (sessions == null) return;
        if (sessions.isEmpty()) {
            sockets.remove(uuid);
            return;
        }
        for (Session session : sessions) {
            if(!session.isOpen()) {
                sessions.remove(session);
                continue;
            }
            session.getAsyncRemote().sendText(message);
        }
    }

    public static void register(String uuid, Session session) {
        Set<Session> sessions = sockets.get(uuid);
        if (sessions == null) {
            sessions = new HashSet<>();
            sockets.put(uuid, sessions);
        }
        sessions.add(session);
    }

    public static void unregister(String uuid) {
        Set<Session> sessions = sockets.get(uuid);
        if (sessions == null) return;
        sessions.remove(sessions.iterator().next());
        sockets.remove(uuid);
    }
}
