import packet.Packet;

import java.util.HashMap;

interface NetworkInterface {
    void addCustomer(Customer customer);
    void addBank(Bank bank);
    void sendRequest(Packet packet);
    void sendResponse(Packet packet);
}

public class Network implements NetworkInterface {
    private HashMap<String, Customer> idToCustomer;
    private HashMap<String, Bank> idToBank;

    public Network() {
        idToBank = new HashMap<>();
        idToCustomer = new HashMap<>();
    }

    @Override
    public void addCustomer(Customer customer) {
        idToCustomer.put(customer.getId(), customer);
        customer.setNetwork(this);
    }

    @Override
    public void addBank(Bank bank) {
        idToBank.put(bank.getId(), bank);
        bank.setNetwork(this);
    }

    @Override
    public void sendRequest(Packet packet) {
        idToBank.get(packet.getBankId()).enqueueRequest(packet);
    }

    @Override
    public void sendResponse(Packet packet) {
        idToCustomer.get(packet.getCustomerId()).enqueueResponse(packet);
    }

}
