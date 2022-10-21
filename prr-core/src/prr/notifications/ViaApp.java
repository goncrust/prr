package prr.notifications;

import java.io.Serial;
import java.io.Serializable;

import prr.clients.Client;

public class ViaApp implements DeliveryMethod, Serializable {
    
    @Serial
    private static final long serialVersionUID = 202217101700L;
    
    private final Client owner;

    public ViaApp(Client owner) {
        this.owner = owner;
    }

    @Override
    public void deliver(Notification notification) {
        owner.queueNotification(notification);
    }
}
