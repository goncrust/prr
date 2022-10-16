package prr;

import java.io.Serializable;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import prr.clients.Client;
import prr.util.NaturalLanguageTextComparator;
import prr.exceptions.UnrecognizedEntryException;
import prr.terminals.FancyTerminal;
import prr.terminals.BasicTerminal;
import prr.terminals.Terminal;
import prr.exceptions.ClientDoesntExistException;
import prr.exceptions.ClientExistsException;
import prr.exceptions.IncorrectTerminalKeyException;
import prr.exceptions.TerminalExistsException;

/**
 * Class Store implements a store.
 */
public class Network implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;

    /** Clients list, sorted by key */
    private final Map<String, Client> clients = new TreeMap<>(new NaturalLanguageTextComparator());

    /** Terminals list, sorted by key */
    private final Map<String, Terminal> terminals = new TreeMap<>(new NaturalLanguageTextComparator());

    /** Communications counter, used for generating communication keys */
    private int communicationKey = 0;

    /**
     * Get and increment the communication key tracker
     */
    public int getCommunicationKey() {
        return communicationKey++;
    }

    /**
     * Register a new client in the network
     * 
     * @param name new client's name
     * @param key  new client's key
     * @param nif  new client's tax id
     * @throws ClientExistsException if the given key is already in use
     */
    public void registerClient(String key, String name, String nif) throws ClientExistsException {
        if (clients.containsKey(key))
            throw new ClientExistsException(key);
        clients.put(key, new Client(name, key, nif));
    }

    /**
     * Get all clients registered in the network
     * 
     * @return A {@link Collection} of clients, sorted by their key
     */
    public Collection<Client> getAllClients() {
        return clients.values();
    }

    /**
     * Get a client
     * 
     * @param key the key that identifies the client
     * @return The {@link Client} with the matching key
     * @throws ClientDoesntExistException if the given key can't be found
     */
    public Client getClient(String key) throws ClientDoesntExistException {
        if (!clients.containsKey(key))
            throw new ClientDoesntExistException(key);
        return clients.get(key);
    }

    /**
     * Get all terminals registered in the network
     * 
     * @return A {@link Collection} of terminals, sorted by their key
     */
    public Collection<Terminal> getAllTerminals() {
        return terminals.values();
    }

    /**
     * Get a terminal
     * 
     * @param key the key that identifies the terminal
     * @return The {@link Terminal} with the matching key
     * @throws ClientDoesntExistException if the given key can't be found
     */
    public Terminal getTerminal(String key) {
        return terminals.get(key);
    }

    /**
     * Register a new client in the network
     * 
     * @param key    new terminal's key
     * @param type   new terminal's type
     * @param client new terminal's owner
     * @throws TerminalExistsException    if the given key is already in use
     * @throws ClientDoenstExistException if the given client doesnt exist
     */
    public void registerTerminal(String key, String type, String client)
            throws TerminalExistsException, IncorrectTerminalKeyException, ClientDoesntExistException {
        if (terminals.containsKey(key))
            throw new TerminalExistsException(key);

        if (!clients.containsKey(client))
            throw new ClientDoesntExistException(client);

        if (!key.matches("[0-9]{6}"))
            throw new IncorrectTerminalKeyException(key);

        Terminal newTerminal;
        if (type.equals("FANCY")) {
            newTerminal = new FancyTerminal(key, clients.get(client));
        } else {
            newTerminal = new BasicTerminal(key, clients.get(client));
        }

        terminals.put(key, newTerminal);
    }

    /**
     * Read text input file and create corresponding domain entities.
     * 
     * @param filename name of the text input file
     * @throws UnrecognizedEntryException if some entry is not correct
     * @throws IOException                if there is an IO error while processing
     *                                    the text file
     */
    void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */ {
        // FIXME implement method
    }
}