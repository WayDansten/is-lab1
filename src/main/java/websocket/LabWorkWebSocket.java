package websocket;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dto.LabWorkDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.jms.Session;
import jakarta.json.Json;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.server.ServerEndpoint;

@ApplicationScoped
@ServerEndpoint("/ws")
public class LabWorkWebSocket {
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    public static void broadcast(List<LabWorkDTO> labWorks) {
        synchronized (sessions) {
            sessions.forEach(session -> {
                
        }
    }
}
