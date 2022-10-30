package prr.communications;

import prr.terminals.Terminal;
import prr.visitors.Printer;

public class VideoCommunication extends InteractiveCommunication {
    public VideoCommunication(int key, Terminal sender, Terminal receiver) {
        super(key, sender, receiver);
    }

    @Override
    public void accept(Printer visitor) {
        visitor.visit(this);
    }

    @Override
    public void updatePrice() {
        setPrice(getSender().getOwner().getPrice(this));
    }
}
