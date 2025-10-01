package websocket;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.Json;
import jakarta.websocket.Session;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.server.ServerEndpoint;

@ApplicationScoped
@ServerEndpoint("/ws")
public class WebSocketNotifier {
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    public static void broadcast(WebSocketMessageType type) {
        String notificationMessage = Json.createObjectBuilder()
                .add("type", type.name())
                .build()
                .toString();
        synchronized (sessions) {
            sessions.forEach(session -> {
                try {
                    session.getBasicRemote().sendText(notificationMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
