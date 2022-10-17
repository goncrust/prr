package prr.exceptions;

/**
 * Exception thrown when trying to find a terminal with
 * a key that hasn't been registered.
 */
public class TerminalDoesntExistException extends KeyRelatedException {
    
    private static final long serialVersionUID = 202217101700L;
    
    /** @param key the nonexistent key */
    public TerminalDoesntExistException(String key) {
        super(key);
    }
}
