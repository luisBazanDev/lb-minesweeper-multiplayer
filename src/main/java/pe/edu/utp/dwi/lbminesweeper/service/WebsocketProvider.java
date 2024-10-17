package pe.edu.utp.dwi.lbminesweeper.service;

import jakarta.websocket.Session;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class WebsocketProvider {
        private static HashMap<String, List<Session>> sockets = new HashMap<>();

    public static void register(String uuid, Session session) {
        List<Session> sessions = sockets.get(uuid);
        if (sessions == null) {
            sessions = new CopyOnWriteArrayList<>();
            sockets.put(uuid, sessions);
        }
        sessions.add(session);
    }

    public static void unregister(String uuid) {
        List<Session> sessions = sockets.get(uuid);
        if (sessions == null) return;
        sessions.remove(sessions.iterator().next());
        sockets.remove(uuid);
    }

    public static void broadcastMessage(String uuid, String message) {
        List<Session> sessions = sockets.get(uuid);

        if (sessions == null) return;
        if (sessions.isEmpty()) {
            unregister(uuid);
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
}
