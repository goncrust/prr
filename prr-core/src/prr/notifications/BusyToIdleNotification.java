package prr.notifications;

import prr.visitors.Printer;

public class BusyToIdleNotification extends Notification {
    public BusyToIdleNotification(int terminalId) {
        super(terminalId);
    }
    
    @Override
    public void accept(Printer visitor) {
        visitor.visit(this);
    }
}
