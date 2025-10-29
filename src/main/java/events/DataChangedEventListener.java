package events;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.TransactionPhase;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import websocket.WebSocketNotifier;

@ApplicationScoped
@NoArgsConstructor
public class DataChangedEventListener {
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void onDataChanged(@Observes(during = TransactionPhase.AFTER_SUCCESS) DataChangedEvent event) {
        event.getMessageTypes().forEach(WebSocketNotifier::broadcast);
    }

}
