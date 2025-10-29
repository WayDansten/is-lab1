package events;

import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import websocket.WebSocketMessageType;

@ApplicationScoped
public class EventPublisher {
    
    @Inject
    private Event<DataChangedEvent> event;
    
    public void fireEvent(Set<WebSocketMessageType> messageTypes) {
        event.fire(new DataChangedEvent(messageTypes));
    }
}
